package com.example.myapplication;

import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class TextTime {

    public static String full_words[] = { "", "One", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
            "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen", "Twenty", "Twenty one",
            "Twenty two", "Twenty three", "Twenty four", "Twenty five",
            "Twenty six", "Twenty seven", "Twenty eight", "Twenty nine", "Thirty", "Thirty one",
            "Thirty two", "Thirty three", "Thirty four", "Thirty five",
            "Thirty six", "Thirty seven", "Thirty eight", "Thirty nine", "Forty", "Forty one",
            "Forty two", "Forty three", "Forty four", "Forty five",
            "Forty six", "Forty seven", "Forty eight", "Forty nine", "Fifty", "Fifty one",
            "Fifty two", "Fifty three", "Fifty four", "Fifty five",
            "Fifty six", "Fifty seven", "Fifty eight", "Fifty nine" };

    public static String[] currentTimeInWords() {
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int am_pm = c.get(Calendar.AM_PM);
        int h = c.get(Calendar.HOUR);
        int m = c.get(Calendar.MINUTE);
        String a[] = new String[2];

        if(h == 0){
            h = 12;
        }
        if(c.get(Calendar.AM_PM) == Calendar.AM) {
            if (m == 0) {
                a[0] = "It's " + full_words[h];
                a[1] = "O' clock AM";
            } else if (m < 10) {
                a[0] = "It's " + full_words[h];
                a[1] = "O'" + full_words[m] + " AM";
            } else {
                a[0] = "It's " + full_words[h];
                a[1] = full_words[m] + " AM";
            }
        }else {
            if (m == 0) {
                a[0] = "It's " + full_words[h];
                a[1] = "O' clock PM";
            } else if (m < 10) {
                a[0] = "It's " + full_words[h];
                a[1] = "O'" + full_words[m] + " PM";
            } else {
                a[0] = "It's " + full_words[h];
                a[1] = full_words[m] + " PM";
            }
        }
        return a;
    }
}
