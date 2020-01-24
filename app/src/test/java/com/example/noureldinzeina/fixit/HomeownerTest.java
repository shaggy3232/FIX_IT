
package com.example.noureldinzeina.fixit;

import static org.junit.Assert.*;
import org.junit.Test;

public class HomeownerTest {

    @Test
    public void checkhomeOwnnerAccNumber() {
        HomeOwner homeOwner = new HomeOwner(6, "Dave", "Son", "Dave", "daveson", "dave@son.com");

        assertEquals("Check the homeowner account number"," 6", homeOwner.getaccNumber() );
    }

    @Test
    public void checkhomeOwnnerFirstName() {
        HomeOwner homeOwner = new HomeOwner(6, "Dave", "Son", "Dave", "daveson", "dave@son.com");

        assertEquals("Check the homeowner first name","Dave", homeOwner.getFName() );
    }

    @Test
    public void checkhomeOwnnerLastName() {
        HomeOwner homeOwner = new HomeOwner(6, "Dave", "Son", "Dave", "daveson", "dave@son.com");

        assertEquals("Check the homeowner last name","Son", homeOwner.getLName() );
    }

    @Test
    public void checkhomeOwnnerUsername() {
        HomeOwner homeOwner = new HomeOwner(6, "Dave", "Son", "Dave", "daveson", "dave@son.com");

        assertEquals("Check the homeowner username","Dave", homeOwner.getUsername() );
    }

    @Test
    public void checkhomeOwnnerPassword() {
        HomeOwner homeOwner = new HomeOwner(6, "Dave", "Son", "Dave", "daveson", "dave@son.com");

        assertEquals("Check the homeowner password","daveson", homeOwner.getPassword() );
    }

    @Test
    public void checkhomeOwnnerEmail() {
        HomeOwner homeOwner = new HomeOwner(6, "Dave", "Son", "Dave", "daveson", "dave@son.com");

        assertEquals("Check the homeowner first name","dave@son.com", homeOwner.getEmail() );
    }

    @Test
    public void checkServiceProviderRating() {
        ServiceProvider serviceProvider = new ServiceProvider(6,"tom","tom","tom","tom","tom@t.com", "TomShow","Avenue", "6136478567","showroom", Boolean.TRUE,3 );
        assertEquals("Check the service provider rating", 3 , serviceProvider.getRating());
    }

    @Test
    public void checkAvailabilityDay() {
        Availability availability = new Availability("Monday", 10, 10, 10, 10);
        assertEquals("Check Availability day", "Monday" , availability.getDay());
    }

    @Test
    public void checkAvailabilityStartHour() {
        Availability availability = new Availability("Monday", 10, 10, 10, 10);
        assertEquals("Check Availability StartHour", 10 , availability.getStarthour());
    }

    @Test
    public void checkAvailabilityEndHour() {
        Availability availability = new Availability("Monday", 10, 10, 10, 10);
        assertEquals("Check Availability EndHour", 10 , availability.getEndhour());
    }

    @Test
    public void checkAvailabilityEndMin() {
        Availability availability = new Availability("Monday", 10, 10, 10, 10);
        assertEquals("Check Availability EndMin", 10 , availability.getEndmin());
    }

    @Test
    public void checkAvailabilityStartMin() {
        Availability availability = new Availability("Monday", 10, 10, 10, 10);
        assertEquals("Check Availability StartMin", 10 , availability.getStartmin());
    }

}



