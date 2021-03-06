package ftn.proj.sportcenters.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ftn.proj.sportcenters.R;

public class DatabaseTool {

    //Pozivamo kad god imamo neki niz da cuvamo u bazi npr working hours za sportske
    // centre da pretvorimo niz stringova u string jedan

    public static String arrayToString(List<?> array, String jsonArrayName){
        JSONObject json = new JSONObject();
        try {
            json.put(jsonArrayName, new JSONArray(array));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    //ovde gurati sve podatke inicijalne

    public static void initDB(Activity activity){

        ArrayList<String> workingHours = new ArrayList<String>();
        workingHours.add("Mon 8:00-22:00");
        workingHours.add("Tue 8:00-22:00");
        workingHours.add("Wed 8:00-22:00");
        workingHours.add("Thu 8:00-22:00");

        String workingHoursString = arrayToString(workingHours,
                "workingHours");

        ContentValues entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "Fudbal");
        Uri uri_sport1 = activity.getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT, entry);

        entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "Kosarka");
        Uri uri_sport2 = activity.getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT, entry);

        entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "Odbojka");
        Uri uri_sport3 = activity.getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT, entry);

        entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "Tenis");
        Uri uri_sport4 = activity.getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT, entry);

        String[] sportIdColumn = { SportCenterSQLiteHelper.COLUMN_ID };
        Cursor cursor1 = activity.getContentResolver().query(Uri.parse(DBContentProvider.CONTENT_URI_BASE.toString() + uri_sport1.toString()), sportIdColumn, null, null,
                null);
        Cursor cursor2 = activity.getContentResolver().query(Uri.parse(DBContentProvider.CONTENT_URI_BASE.toString() + uri_sport2.toString()), sportIdColumn, null, null,
                null);
        Cursor cursor3 = activity.getContentResolver().query(Uri.parse(DBContentProvider.CONTENT_URI_BASE.toString() + uri_sport3.toString()), sportIdColumn, null, null,
                null);
        Cursor cursor4 = activity.getContentResolver().query(Uri.parse(DBContentProvider.CONTENT_URI_BASE.toString() + uri_sport4.toString()), sportIdColumn, null, null,
                null);
        cursor1.moveToFirst();
        cursor2.moveToFirst();
        cursor3.moveToFirst();
        cursor4.moveToFirst();
        ArrayList<Long> sports = new ArrayList<Long>();
        sports.add(cursor1.getLong(0));
        sports.add(cursor2.getLong(0));
        String sportsString = arrayToString(sports,
                "sports");
        entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "Albatros");
        entry.put(SportCenterSQLiteHelper.COLUMN_ADDRESS, "Novi Sad");
        entry.put(SportCenterSQLiteHelper.COLUMN_IMAGE, R.drawable.albatros);
        entry.put(SportCenterSQLiteHelper.COLUMN_WORKING_HOURS, workingHoursString);
        entry.put(SportCenterSQLiteHelper.COLUMN_SPORTS, sportsString);

        activity.getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT_CENTER, entry);


        sports = new ArrayList<Long>();
        sports.add(cursor1.getLong(0));
        sports.add(cursor3.getLong(0));
        sports.add(cursor4.getLong(0));
        sportsString = arrayToString(sports,
                "sports");
        entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "Meridiana");
        entry.put(SportCenterSQLiteHelper.COLUMN_ADDRESS, "Novi Sad");
        entry.put(SportCenterSQLiteHelper.COLUMN_IMAGE, R.drawable.meridiana);
        entry.put(SportCenterSQLiteHelper.COLUMN_WORKING_HOURS, workingHoursString);
        entry.put(SportCenterSQLiteHelper.COLUMN_SPORTS, sportsString);

        activity.getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT_CENTER, entry);


        sports = new ArrayList<Long>();
        sports.add(cursor1.getLong(0));
        sports.add(cursor3.getLong(0));
        sports.add(cursor4.getLong(0));
        sportsString = arrayToString(sports,
                "sports");
        entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "DUGA");
        entry.put(SportCenterSQLiteHelper.COLUMN_ADDRESS, "Beograd");
        entry.put(SportCenterSQLiteHelper.COLUMN_IMAGE, R.drawable.albatros);
        entry.put(SportCenterSQLiteHelper.COLUMN_WORKING_HOURS, workingHoursString);
        entry.put(SportCenterSQLiteHelper.COLUMN_SPORTS, sportsString);

        activity.getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT_CENTER, entry);


        sports = new ArrayList<Long>();
        sports.add(cursor2.getLong(0));
        sportsString = arrayToString(sports,
                "sports");
        entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "As");
        entry.put(SportCenterSQLiteHelper.COLUMN_ADDRESS, "Novi Sad");
        entry.put(SportCenterSQLiteHelper.COLUMN_IMAGE, R.drawable.meridiana);
        entry.put(SportCenterSQLiteHelper.COLUMN_WORKING_HOURS, workingHoursString);

        activity.getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT_CENTER, entry);


        sports = new ArrayList<Long>();
        sports.add(cursor1.getLong(0));
        sports.add(cursor2.getLong(0));
        sports.add(cursor3.getLong(0));
        sportsString = arrayToString(sports,
                "sports");
        entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "Hattrick");
        entry.put(SportCenterSQLiteHelper.COLUMN_ADDRESS, "Niš");
        entry.put(SportCenterSQLiteHelper.COLUMN_IMAGE, R.drawable.albatros);
        entry.put(SportCenterSQLiteHelper.COLUMN_WORKING_HOURS, workingHoursString);

        activity.getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT_CENTER, entry);


        sports = new ArrayList<Long>();
        sports.add(cursor2.getLong(0));
        sports.add(cursor3.getLong(0));
        sports.add(cursor4.getLong(0));
        sportsString = arrayToString(sports,
                "sports");
        entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "Maxbet");
        entry.put(SportCenterSQLiteHelper.COLUMN_ADDRESS, "Vršac");
        entry.put(SportCenterSQLiteHelper.COLUMN_IMAGE, R.drawable.albatros);
        entry.put(SportCenterSQLiteHelper.COLUMN_WORKING_HOURS, workingHoursString);

        activity.getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT_CENTER, entry);

    }
}
