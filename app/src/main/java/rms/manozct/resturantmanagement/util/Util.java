package rms.manozct.resturantmanagement.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Crawlers on 4/12/2017.
 */

public class Util
{

    public static Date convertStringToDate(String startDateString){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = null;
        try {
            if (startDateString!=null)
            {
                startDate = df.parse(startDateString);
                String newDateString = df.format(startDate);
                System.out.println("Date:::"+newDateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    public static boolean isCorrectDate(String dateString)
    {

            if(convertStringToDate(dateString)!=null) {
                return true;
            }
            return false;

    }
    public static String getRandomId(){
        return UUID.randomUUID().toString().substring(0,7);
    }
}
