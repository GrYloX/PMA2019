package ftn.proj.sportcenters.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SportCenterSQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_SPORT_CENTER = "sport_center";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_WORKING_HOURS = "working_hours";


    private static final String DATABASE_NAME = "sportcenters.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DB_CREATE = "create table "
            + TABLE_SPORT_CENTER + "("
            + COLUMN_ID  + " integer primary key autoincrement , "
            + COLUMN_NAME + " text, "
            + COLUMN_ADDRESS + " text, "
            + COLUMN_IMAGE + " integer, "
            + COLUMN_WORKING_HOURS + " text "
            + ")";

    public SportCenterSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPORT_CENTER);
        onCreate(db);
    }
}
