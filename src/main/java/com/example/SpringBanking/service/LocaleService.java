package com.example.SpringBanking.service;

import java.util.Locale;

public class LocaleService {

    private static Locale locale = Locale.US;

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale newLocale) {
        locale = newLocale;
    }
}
