package ftn.proj.sportcenters.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class DBContentProvider extends ContentProvider {

    private SportCenterSQLiteHelper database;

    private static final int SPORT_CENTER = 10;
    private static final int SPORT_CENTER_ID = 20;

    private static final String AUTHORITY = "ftn.proj.sportcenters";

    private static final String SPORT_CENTER_PATH = "sport_center";

    public static final Uri CONTENT_URI_SPORT_CENTER = Uri.parse("content://" + AUTHORITY + "/" + SPORT_CENTER_PATH);

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, SPORT_CENTER_PATH, SPORT_CENTER);
        sURIMatcher.addURI(AUTHORITY, SPORT_CENTER_PATH + "/#", SPORT_CENTER_ID);
    }


    @Override
    public boolean onCreate() {
        database = new SportCenterSQLiteHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Using SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check if the caller has requested a column which does not exist
        //checkColumns(projection);
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case SPORT_CENTER_ID:
                // Adding the ID to the original query
                queryBuilder.appendWhere(SportCenterSQLiteHelper.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                //$FALL-THROUGH$
            case SPORT_CENTER:
                // Set the table
                queryBuilder.setTables(SportCenterSQLiteHelper.TABLE_SPORT_CENTER);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        // make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri retVal = null;
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case SPORT_CENTER:
                id = sqlDB.insert(SportCenterSQLiteHelper.TABLE_SPORT_CENTER, null, values);
                retVal = Uri.parse(SPORT_CENTER_PATH + "/" + id);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return retVal;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        int rowsDeleted = 0;
        switch (uriType) {
            case SPORT_CENTER:
                rowsDeleted = sqlDB.delete(SportCenterSQLiteHelper.TABLE_SPORT_CENTER,
                        selection,
                        selectionArgs);
                break;
            case SPORT_CENTER_ID:
                String idSportCenter = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(SportCenterSQLiteHelper.TABLE_SPORT_CENTER,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idSportCenter,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(SportCenterSQLiteHelper.TABLE_SPORT_CENTER,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idSportCenter
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        int rowsUpdated = 0;
        switch (uriType) {
            case SPORT_CENTER:
                rowsUpdated = sqlDB.update(SportCenterSQLiteHelper.TABLE_SPORT_CENTER,
                        values,
                        selection,
                        selectionArgs);
                break;
            case SPORT_CENTER_ID:
                String idSportCenter = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(SportCenterSQLiteHelper.TABLE_SPORT_CENTER,
                            values,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idSportCenter,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(SportCenterSQLiteHelper.TABLE_SPORT_CENTER,
                            values,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idSportCenter
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}
