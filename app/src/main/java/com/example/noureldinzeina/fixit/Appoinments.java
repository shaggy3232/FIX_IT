package com.example.noureldinzeina.fixit;

public class Appoinments {

    String Day;
    int Starthour;
    int StartMin;
    int Endhour;
    int Endmin;


    public void setEndhour(int endhour) {
        Endhour = endhour;
    }

    public void setEndmin(int endmin) {
        Endmin = endmin;
    }

    public void setStarthour(int starthour) {
        Starthour = starthour;
    }

    public void setDay(String day) {
        Day = day;
    }

    public void setStartMin(int startMin) {
        StartMin = startMin;
    }

    public int getStarthour() {
        return Starthour;
    }

    public int getEndmin() {
        return Endmin;
    }

    public String getDay() {
        return Day;
    }

    public int getEndhour() {
        return Endhour;
    }


    public int getStartMin() {
        return StartMin;
    }


}

