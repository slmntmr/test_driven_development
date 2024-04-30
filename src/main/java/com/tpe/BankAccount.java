package com.tpe;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private String accountNumber;

    private double balance;

    private double dailyWithdrawnLimit ; // Gunluk para cekim limiti

    private double dailyWithdrawnAmount ; // gunluk cekilen miktar

    private List<String> history = new ArrayList<>();


    // Constructor

    public BankAccount(String accountNumber, double balance, double dailyWithdrawnLimit) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.dailyWithdrawnLimit = dailyWithdrawnLimit;
    }

    // !!! Metodlar

    public void deposit(double amount){
        if(amount > 0){
            this.balance += amount ;
            history.add("Deposit: " + amount);
        }
    }

    public boolean withdraw(double amount){

        if(amount > 0 && this.balance >= amount && (this.dailyWithdrawnAmount + amount) <= dailyWithdrawnLimit){
            this.balance -= amount;
            this.dailyWithdrawnAmount +=amount;
            history.add("Withdraw : " + amount);
            return true;
        }
        return false ;
    }


    // !!! GETTER - SETTER

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getDailyWithdrawnLimit() {
        return dailyWithdrawnLimit;
    }

    public void setDailyWithdrawnLimit(double dailyWithdrawnLimit) {
        this.dailyWithdrawnLimit = dailyWithdrawnLimit;
    }

    public double getDailyWithdrawnAmount() {
        return dailyWithdrawnAmount;
    }

    public void setDailyWithdrawnAmount(double dailyWithdrawnAmount) {
        this.dailyWithdrawnAmount = dailyWithdrawnAmount;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }
}