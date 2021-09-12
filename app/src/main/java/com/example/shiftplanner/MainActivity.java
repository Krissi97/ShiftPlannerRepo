package com.example.shiftplanner;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shiftplanner.activity_manager.ActivityManager;
import com.example.shiftplanner.ui.login.LoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 1000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
                            if (result.getResultCode() == RC_SIGN_IN) {
                                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                                try {
                                    // Google Sign In was successful, authenticate with Firebase
                                    GoogleSignInAccount account = task.getResult(ApiException.class);
                                    firebaseAuthWithGoogle(account.getIdToken());
                                    switchActivity();
                                } catch (ApiException e) {
                                    // Google Sign In failed, update UI appropriately
                                    switchActivity();
                                }
                            }
                        }
                    }
                });

        setContentView(R.layout.activity_main);

        myAuth = FirebaseAuth.getInstance();

        checkIfLoggedIn();
        createRequest();

        findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }


    public void checkIfLoggedIn() {

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            startActivity(new Intent(this, FeedActivity.class));
            this.finish();
        }
    }

    public void createRequest() {
        GoogleSignInOptions myGSO = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, myGSO);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }



    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        myAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = myAuth.getCurrentUser();
                            switchActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                        }
                    }
                });
    }

    public void switchActivity()
    {
        startActivity(new Intent(this, FeedActivity.class));
    }
}