package com.testProj.RoomWithBulb.utils;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.assertEquals;

public class LocationUtilsTest {

    @Test
    public void getAllCountries() {
    }

    @Test
    public void shouldReturnAValidaLocation() throws GeoIp2Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("151.106.8.135");
        String country = LocationUtils.getLocation(request);
        String expected = "France";
        assertEquals(country, expected);
    }

    @Test
    public void shouldReturnExeption() throws GeoIp2Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("127.0.0.1");
        String country = LocationUtils.getLocation(request);
        String expected = "The address: " + request.getRemoteAddr() + " is not in the database.";
        assertEquals(expected, country);
    }
}