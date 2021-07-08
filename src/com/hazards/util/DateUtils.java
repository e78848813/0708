package com.hazards.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    public static Date getDateByString(String string){
        Date parse = null;
        try {
            parse = sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
