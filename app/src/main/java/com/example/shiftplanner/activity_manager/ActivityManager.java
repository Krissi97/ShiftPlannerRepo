package com.example.shiftplanner.activity_manager;

import android.app.Activity;
import android.content.Intent;

public class ActivityManager {

    public static ActivityManager Instance;

    public ActivityManager() {
        Instance = this;
    }

    public void switchActivity(Activity from, Activity to) {
        Intent switchActivityIntent = new Intent(from, to.getClass());
        //startActivity(switchActivityIntent);
    }
}
