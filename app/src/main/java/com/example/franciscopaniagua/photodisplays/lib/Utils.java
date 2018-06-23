package com.example.franciscopaniagua.photodisplays.lib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {


    public static String formatDate(String date) throws ParseException {
        if(date==null)return "";
        Locale current = new Locale("es", "MX");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.serverDate);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date1 = simpleDateFormat.parse(date);
        simpleDateFormat = new SimpleDateFormat(Constants.date_output,current);
        return simpleDateFormat.format(date1);
    }

    public static String formatDateToServer(long date1) throws ParseException {
        Locale current = new Locale("es", "MX");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.serverDate,current);
        return simpleDateFormat.format(date1);
    }

}
