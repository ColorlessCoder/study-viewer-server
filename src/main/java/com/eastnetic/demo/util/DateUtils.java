package com.eastnetic.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date parseToDate(String dateString) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
    }
}
