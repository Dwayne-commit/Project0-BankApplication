package com.jdbc.util;

public class Account {
    private String accno;
    private String name;
    private String acc_type;
    private long balance;


    public Account(String accno, String name, String acc_type, long balance) {
        super();
        this.accno = accno;
        this.name = name;
        this.acc_type = acc_type;
        this.balance = balance;
    }


    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
