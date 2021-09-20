package com.example.shiftplanner.ui.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shiftplanner.R;

import java.time.LocalDate;
import java.util.ArrayList;


public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>{
    private final ArrayList<LocalDate> daysOfMonth;

    private final OnItemListener onItemListener;


    public CalendarAdapter(ArrayList<LocalDate> daysOfMonth, OnItemListener onItemListener)
        {
            this.daysOfMonth = daysOfMonth;
            this.onItemListener = onItemListener;
        }

        @NonNull
        @Override
        public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.calendar_cell, parent, false);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = (int) (parent.getHeight() * 0.166666666);
            return new CalendarViewHolder(view, onItemListener, daysOfMonth);
        }

        @Override
        public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position)
        {
            final LocalDate date = daysOfMonth.get(position);
            holder.dayOfMonth.setText(date.getDayOfMonth());
        }

        @Override
        public int getItemCount()
        {
            return daysOfMonth.size();
        }

        public interface  OnItemListener
        {
            void onItemClick(int position, LocalDate date);
        }
    }


