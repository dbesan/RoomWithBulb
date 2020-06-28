package com.testProj.RoomWithBulb.utils;

import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.testProj.RoomWithBulb.service.RawDBDemoGeoIPLocationService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class LocationUtils {
    public static List<String> getAllCountries(Locale lang) {
        List<String> countries = new ArrayList();
        for (Locale locale : Locale.getAvailableLocales()) {
            if (!(locale.getDisplayCountry(lang) == "")) {
                countries.add(locale.getDisplayCountry(lang));
            }
        }
        Collections.sort(countries);
        return countries;
    }

    public static String getLocation(HttpServletRequest request) throws GeoIp2Exception {
        String location = "";
        String ip = request.getRemoteAddr();
        try {
            location = RawDBDemoGeoIPLocationService.getLocation(ip);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressNotFoundException e) {
            location = "The address: " + ip + " is not in the database.";
        }
        return location;
    }
}
