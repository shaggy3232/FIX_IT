package com.example.noureldinzeina.fixit;

public class Availability {
    String Day;
    int starthour = 0;
    int startmin = 0;
    int endhour = 0;
    int endmin = 0;

    public Availability(String Day, int starthour, int startmin, int endhour, int endmin) {
        this.Day = Day;
        this.starthour = starthour;
        this.startmin = startmin;
        this.endhour = endhour;
        this.endmin = endmin;
    }

    public void setDay(String day) {
        this.Day = day;
    }

    public void setStarthour(int starthour) {
        this.starthour = starthour;
    }

    public void setStartmin(int startmin) {
        this.startmin = startmin;
    }

    public void setEndhour(int endhour) {
        this.endhour = endhour;
    }

    public void setEndmin(int endmin) {
        this.endmin = endmin;
    }

    public int getStarthour() {
        return this.starthour;
    }

    public int getStartmin() {
        return this.startmin;
    }

    public int getEndmin() {
        return this.endmin;
    }

    public int getEndhour() {
        return this.endhour;
    }

    public String getDay() {
        return this.Day;
    }
}
