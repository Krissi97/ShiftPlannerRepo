package com.example.shiftplanner.shift_scripts;

import com.example.shiftplanner.account.Account;

import java.util.Date;

public class Shift {

    private ShiftState myState;
    private Account connected_User;
    private Date myDate;
    private int shiftDuration;

    public Shift(Date date, ShiftState state, Account account, int duration) {

        myDate = date;
        myState = state;
        connected_User = account;
        shiftDuration = duration;
    }

}
