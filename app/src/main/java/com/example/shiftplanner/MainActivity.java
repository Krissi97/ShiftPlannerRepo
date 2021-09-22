package com.example.shiftplanner;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiftplanner.account.Account;
import com.example.shiftplanner.databinding.ActivityMainBinding;
import com.example.shiftplanner.shift_scripts.Shift;
import com.example.shiftplanner.ui.calendar.CalendarAdapter;
import com.example.shiftplanner.ui.calendar.CalendarUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import java.time.LocalDate;

import static com.example.shiftplanner.ui.calendar.CalendarUtils.daysInMonthArray;
import static com.example.shiftplanner.ui.calendar.CalendarUtils.isBusinessDay;


public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    private ArrayList<LocalDate> privDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarFeed.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_feed);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //findViewById(R.id.action_settings).setOnClickListener(v -> {
         //   logOut();
        //});


        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();

        privDays = daysInMonthArray(CalendarUtils.selectedDate);
    }

    private void logOut() {

        Log.d("debug99", "logout button pressed");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feed, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_feed);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }


    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));

        ArrayList<LocalDate> days = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public void previousMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {

        final LocalDate date = privDays.get(position);

        if (dayText != "" && isBusinessDay(date))
            startActivity(new Intent(this, ShiftActivity.class));
    }
}