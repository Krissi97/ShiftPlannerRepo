package com.example.shiftplanner.ui.calendar;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shiftplanner.R;
import com.example.shiftplanner.account.Account;
import com.example.shiftplanner.shift_scripts.Shift;
import com.example.shiftplanner.shift_scripts.ShiftState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    private final ArrayList<String> daysOfMonth;
    public ArrayList<Shift> shiftListPerMonth;

    private final OnItemListener onItemListener;


    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        //shiftListPerMonth = new ArrayList<Shift>(daysOfMonth.size());
        // createShiftList();
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener);
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
        if (daysOfMonth.get(position) != "")
            holder.parentView.setBackgroundColor(Color.LTGRAY);
        holder.dayOfMonth.setText(daysOfMonth.get(position));
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText);
    }

}


