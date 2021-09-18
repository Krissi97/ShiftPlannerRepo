package com.example.shiftplanner.data;

import com.example.shiftplanner.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result login(String username, String password) {

        try {
            // TODO: Google Login Authentification

            LoggedInUser tryingUser = new LoggedInUser(
                    java.util.UUID.randomUUID().toString(),
                    "Testing User");

            return new Result.Success<>(tryingUser);

            // TODO: Old Code from LoginActivtiy
            /*LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser); */
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}