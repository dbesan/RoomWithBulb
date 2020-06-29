package com.testProj.RoomWithBulb.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class RawDBGeoIPLocationService {
    @Value("classpath:GeoLite/GeoLite2-Country.mmdb")
    Resource resourceFile;
    private static DatabaseReader dbReader;

    public RawDBGeoIPLocationService() throws IOException {
        File database = new File("src/main/resources/GeoLite/GeoLite2-Country.mmdb");
        dbReader = new DatabaseReader.Builder(database).build();
    }


    public static String getLocation(String ip)
            throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);

        CountryResponse response = dbReader.country(InetAddress.getByName(ip));
        String countryName = response.getCountry().getName();
        return countryName;
    }


}