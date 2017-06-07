package com.example.frth3176.grubgrab;

/**
 * Created by frth3176 on 4/29/17.
 */

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Calendar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class DateUtil {

    public static Date addDays(Date date, int days)

    {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();

    }

}



