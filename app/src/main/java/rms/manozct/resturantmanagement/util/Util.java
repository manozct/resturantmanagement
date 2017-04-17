package rms.manozct.resturantmanagement.util;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Crawlers on 4/12/2017.
 */

public class Util {

    public static Date convertStringToDate(String startDateString){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = null;
        try {
            if (startDateString!=null)
            {
                startDate = df.parse(startDateString);
                String newDateString = df.format(startDate);
                System.out.println(newDateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    public static String getRandomId(){
        return UUID.randomUUID().toString().substring(0,7);
    }

    public static String getImagePath(Uri uri, Activity activity){
        Cursor cursor = activity.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();

        cursor = activity.getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }
}
