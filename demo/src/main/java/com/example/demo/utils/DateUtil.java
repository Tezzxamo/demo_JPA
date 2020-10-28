package com.example.demo.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static DateTimeFormatter defaultDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate today(String date, DateTimeFormatter dateTimeFormatter){
        LocalDate today = LocalDate.parse(date,dateTimeFormatter);
        return today;
    }

    public static int getAge(String birthday){
        LocalDate before = today(birthday,defaultDateTimeFormatter);
        LocalDate now = LocalDate.now();
        Period period = Period.between(before,now);
        return period.getYears();
    }

}
