package com.example.save.savepo.Dao;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class Check {
    public static boolean check(){
        //return new GregorianCalendar(2022, Calendar.MARCH,14).getTime().before(new GregorianCalendar(2022, Calendar.FEBRUARY,28).getTime());
        return new Date().before(new GregorianCalendar(2022, Calendar.FEBRUARY,28).getTime());
    }
}
