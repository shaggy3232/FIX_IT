package com.example.noureldinzeina.fixit;

import static org.junit.Assert.*;
import org.junit.Test;

public class ServiceproviderTest {

    @Test
    public void checkServiceProviderAddress() {
        ServiceProvider serviceProvider = new ServiceProvider("tom","tom","tom","tom","tom@t.com", "TomShow","Avenue", "6136478567","showroom", Boolean.TRUE);
        assertEquals("Check the service provider address", "Avenue", serviceProvider.getAddress());
    }

    @Test
    public void checkServiceProviderPhoneNumber() {
        ServiceProvider serviceProvider = new ServiceProvider("tom","tom","tom","tom","tom@t.com", "TomShow","Avenue", "6136478567","showroom", Boolean.TRUE);
        assertEquals("Check the service provider phone number", "6136478567", serviceProvider.getPhoneNumber());
    }

    @Test
    public void checkServiceCompanyName() {
        ServiceProvider serviceProvider = new ServiceProvider("tom","tom","tom","tom","tom@t.com", "TomShow","Avenue", "6136478567","showroom", Boolean.TRUE);
        assertEquals("Check the service provider company name", "TomShow", serviceProvider.getCompanyName());
    }

    @Test
    public void checkServiceProviderDescription() {
        ServiceProvider serviceProvider = new ServiceProvider("tom","tom","tom","tom","tom@t.com", "TomShow","Avenue", "6136478567","showroom", Boolean.TRUE);
        assertEquals("Check the service provider description", "showroom", serviceProvider.getDescription());
    }




}
