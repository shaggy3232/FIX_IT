package com.example.noureldinzeina.fixit;

import java.util.ArrayList;

public class Account {


    protected String accountNumber;
    protected int accNumber;
    protected String username;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String password;
    protected String address;
    protected String type;
    protected ArrayList<Account> accounts;



    public Account() {
        accounts = new ArrayList<Account>();
    }

    public String getFName() {
        return firstName;
    }
    public String getLName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getaccNumber() {
        return accountNumber;
    }

    public String getUsername() { return username;}

    public String getType(){return type;};

    public boolean verifyEmail(String email) {
        if (accounts == null) {
            return true;
        }
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getEmail() == email) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyUsername(String username) {
        if (accounts == null) {
            return true;
        }
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername() == username) {
                return false;
            }
        }
        return true;
    }

    public void add(Account account){
        if (verifyEmail(account.getEmail()) && verifyUsername(account.getUsername())){
            accounts.add(account);}
    }

    public void delete(Account account){
        accounts.remove(account);
    }



}




