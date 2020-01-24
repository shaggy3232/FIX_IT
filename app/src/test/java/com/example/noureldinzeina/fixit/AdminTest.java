package com.example.noureldinzeina.fixit;

import static org.junit.Assert.*;
import org.junit.Test;

public class AdminTest {

    @Test
    public void checkAdminFirstName() {
        Admin admin = new Admin("tom", "tom", "tom", "tom","tom@t.com");
        assertEquals("Check the admin first name", "tom", admin.getFName());
    }

    @Test
    public void checkAdminLastName() {
        Admin admin = new Admin("tom", "tom", "tom", "tom","tom@t.com");
        assertEquals("Check the admin last name", "tom", admin.getLName());
    }

    @Test
    public void checkAdminUserName() {
        Admin admin = new Admin("tom", "tom", "tom", "tom","tom@t.com");
        assertEquals("Check the admin username", "tom", admin.getUsername());
    }

    @Test
    public void checkAdminPassword() {
        Admin admin = new Admin("tom", "tom", "tom", "tom","tom@t.com");
        assertEquals("Check the admin password", "tom", admin.getFName());
    }

    @Test
    public void checkAdminEmail() {
        Admin admin = new Admin("tom", "tom", "tom", "tom","tom@t.com");
        assertEquals("Check the admin first name", "tom", admin.getEmail());
    }

}
