package com.example.shiftplanner.ui.calendar;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shiftplanner.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.shiftplanner.ui.calendar.CalendarUtils.isBusinessDay;


public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    private final ArrayList<LocalDate> days;

    private final OnItemListener onItemListener;


    public CalendarAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener) {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener, days);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        //TODO: App stürzt ab -> Code so, dass nur Schicht Tage markiert werden
        //Date tempDate = new Date(daysOfMonth.get(position));

        // Calendar tempCalendar = Calendar.getInstance();
        //tempCalendar.setTime(tempDate);

        //if (tempCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY || tempCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
        // Shift tempShift = new Shift(tempDate, ShiftState.FREE, new Account("test", "testemail@test"), 3);
        //   holder.parentView.setBackgroundColor(Color.LTGRAY);
        //}

        final LocalDate date = days.get(position);

        if (date == null) {
            holder.dayOfMonth.setText("");
        }
        else
        {

            Log.d("debug99", "date not null");


            if (isBusinessDay(date))
                holder.parentView.setBackgroundColor(Color.LTGRAY);
            else
                Log.d("debug99", "date is holiday/weekend");

            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            Log.d("debug99", "date text set");
        }
    }


    @Override
    public int getItemCount() {
        return days.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText);
    }

}


