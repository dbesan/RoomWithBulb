package com.testProj.RoomWithBulb.service;

import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RawDBGeoIPLocationServiceTest {

    @Test
    public void shouldReturnAValidaLocation() throws IOException, GeoIp2Exception {
        String ip = "151.106.8.135";
        String expected = "France";
        String countryName = new RawDBGeoIPLocationService().getLocation(ip);

        assertEquals(countryName, expected);
    }

    @Test(expected = AddressNotFoundException.class)
    public void shouldReturnExeption() throws IOException, GeoIp2Exception {
        String ip = "127.0.0.1";
        String countryName = new RawDBGeoIPLocationService().getLocation(ip);

    }
}
