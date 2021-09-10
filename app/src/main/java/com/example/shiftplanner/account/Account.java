package com.example.shiftplanner.account;

import java.util.List;

public class Account {

    private String name;
    private String email;

    private List<Integer> hoursWorkedList;

    public Account(String myName, String myEmail) {

        this.name = myName;
        this.email = myEmail;
    }
}
