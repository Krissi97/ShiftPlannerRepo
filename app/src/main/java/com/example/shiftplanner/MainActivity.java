package com.example.shiftplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.shiftplanner.activity_manager.ActivityManager;
import com.example.shiftplanner.ui.login.LoginActivity;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    private Activity LoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        println("Hello");
    }

    public void goToLogin() {
        ActivityManager.Instance.switchActivity(this, LoginActivity);
    }
}
//HuiBuh
//Samu Test
//Samu Test2
//Samu Test3
//Samu Push