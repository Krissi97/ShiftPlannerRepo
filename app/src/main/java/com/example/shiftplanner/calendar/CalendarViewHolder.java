package com.example.shiftplanner.calendar;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public final TextView dayOfMonth;
    public final CalendarAdapter.OnItemListener onItemListener;
    public CalendarViewHolder(View itemView, CalendarAdapter.OnItemListener onItemListener, TextView dayOfMonth, TextView dayOfMonth1);


    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cell_day_text);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
