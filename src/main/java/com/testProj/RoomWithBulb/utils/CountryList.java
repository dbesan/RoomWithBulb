package com.testProj.RoomWithBulb.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CountryList {
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
}
