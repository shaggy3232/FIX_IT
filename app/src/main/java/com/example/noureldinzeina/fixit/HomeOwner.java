package com.example.noureldinzeina.fixit;

public class HomeOwner extends Account {

    public HomeOwner(){}

    public HomeOwner(int Number, String fName, String lName, String userName, String passWord,String email) {
        accNumber = Number;
        firstName = fName;
        this.email=email;
        lastName = lName;
        username = userName;
        password = passWord;
        //accNumber++;
        accountNumber = Integer.toString(accNumber);
        type="Home Owner";
    }


}
