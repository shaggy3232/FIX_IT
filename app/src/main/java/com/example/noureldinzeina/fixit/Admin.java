package com.example.noureldinzeina.fixit;

import java.util.ArrayList;
public class Admin extends Account {

    public ArrayList<Service> services;
    public Admin(String fName, String lName, String userName, String passWord,String email) {
        type="Admin";
        this.email=email;
        firstName = fName;
        lastName = lName;
        username = userName;
        password = passWord;
    }

    public void addService(Service service){
        if (verifyService(service.getName())){
            services.add(service);}

    }

    public void deleteService(Service service){
        services.remove(service);
    }

    public boolean verifyService(String name){
        if (services == null) {
            return true;
        }
        for (int i=0; i<services.size();i++) {
            if(services.get(i).getName()==name) {
                return false;
            }
        }
        return true;
    }


}
