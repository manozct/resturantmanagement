package rms.manozct.resturantmanagement.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Crawlers on 4/12/2017.
 */

public class Util {

    public static Date convertStringToDate(String startDateString){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = null;
        try {
            startDate = df.parse(startDateString);
            String newDateString = df.format(startDate);
            System.out.println(newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }
}
