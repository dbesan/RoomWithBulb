package com.testProj.RoomWithBulb.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class RawDBGeoIPLocationService {

    public static String getLocation(String ip) throws IOException, GeoIp2Exception {
        File database = new File("src/main/resources/GeoLite/GeoLite2-Country.mmdb");
        DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
        CountryResponse response = dbReader.country(InetAddress.getByName(ip));
        String countryName = response.getCountry().getName();
        return countryName;
    }


}