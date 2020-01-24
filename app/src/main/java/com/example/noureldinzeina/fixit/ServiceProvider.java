package com.example.noureldinzeina.fixit;

import java.util.ArrayList;

public class ServiceProvider extends Account {
    public static ArrayList<Service> serviceList;
    public String address;
    public String description;
    public boolean licensed;
    public String companyName, phoneNumber;
    public int ratingCounter;
    private ArrayList<Availability> availabilities;
    public static ArrayList<Service> servicesProvider;
    public int rating;

    public ServiceProvider(int Number, String fName, String lName, String userName, String passWord, String email, String companyName, String address, String phoneNumber, String description, Boolean licensed, int rate) {
        serviceList = new ArrayList<Service>();
        availabilities = new ArrayList<Availability>();
        accNumber = Number;
        firstName = fName;
        lastName = lName;
        username = userName;
        this.email = email;
        password = passWord;
        //accNumber++;
        accountNumber = Integer.toString(accNumber);
        type = "Service Provider";
        servicesProvider = new ArrayList<Service>();
        this.address = address;
        this.description = description;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.licensed = licensed;
        ratingCounter=0;
        rating=rate;
    }

    public ArrayList<Service> getList() {
        return servicesProvider;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String name) {
        this.phoneNumber = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String name) {
        this.address = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public void setLicensed(boolean name) {
        licensed = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyNameName(String name) {
        companyName = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int name) {
        rating = name;
    }

    public void addService(Service service){
        serviceList.add(service);
    }

    public void setAvailability(Availability dayNTime){
        availabilities.add(dayNTime);
    }
}
