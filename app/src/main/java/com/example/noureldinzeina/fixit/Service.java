package com.example.noureldinzeina.fixit;
import android.os.Parcel;
import android.os.Parcelable;

public class Service implements Parcelable {

    public String name="";
    public String hourlyRate;
    public String description;

    public Service (String name, String hourlyRate){

        this.name=name;
        this.hourlyRate=hourlyRate;

    }

    protected Service(Parcel in) {
        name = in.readString();
        hourlyRate = in.readString();
        description = in.readString();
    }

    public static final Creator<Service> CREATOR = new Creator<Service>() {
        @Override
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
        }
    };

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getHourlyRate(){
        return hourlyRate;
    }

    public void setHourlyRate(String rate){
        hourlyRate=rate;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String note){
        description=note;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(hourlyRate);
        dest.writeString(description);
    }
}
