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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DBContentProvider extends ContentProvider {

    private SportCenterSQLiteHelper database;

    private static final int SPORT_CENTER = 10;
    private static final int SPORT_CENTER_ID = 20;
    private static final int USER = 30;
    private static final int USER_ID = 40;
    private static final int SPORT = 50;
    private static final int SPORT_ID = 60;
    private static final int COMMENT = 70;
    private static final int COMMENT_ID = 80;

    private static final String AUTHORITY = "ftn.proj.sportcenters";

    private static final String SPORT_CENTER_PATH = "sport_center";
    private static final String USER_PATH = "user";
    private static final String SPORT_PATH = "sport";
    private static final String COMMENT_PATH = "comment";

    public static final Uri CONTENT_URI_SPORT_CENTER = Uri.parse("content://" + AUTHORITY + "/" + SPORT_CENTER_PATH);
    public static final Uri CONTENT_URI_USER = Uri.parse("content://" + AUTHORITY + "/" + USER_PATH);
    public static final Uri CONTENT_URI_SPORT = Uri.parse("content://" + AUTHORITY + "/" + SPORT_PATH);
    public static final Uri CONTENT_URI_COMMENT = Uri.parse("content://" + AUTHORITY + "/" + COMMENT_PATH);

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, SPORT_CENTER_PATH, SPORT_CENTER);
        sURIMatcher.addURI(AUTHORITY, SPORT_CENTER_PATH + "/#", SPORT_CENTER_ID);
        sURIMatcher.addURI(AUTHORITY, USER_PATH, USER);
        sURIMatcher.addURI(AUTHORITY, USER_PATH + "/#", USER_ID);
        sURIMatcher.addURI(AUTHORITY, SPORT_PATH, SPORT);
        sURIMatcher.addURI(AUTHORITY, SPORT_PATH + "/#", SPORT_ID);
        sURIMatcher.addURI(AUTHORITY, COMMENT_PATH, COMMENT);
        sURIMatcher.addURI(AUTHORITY, COMMENT_PATH + "/#", COMMENT_ID);
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
            case USER_ID:
                // Adding the ID to the original query
                queryBuilder.appendWhere(SportCenterSQLiteHelper.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                //$FALL-THROUGH$
            case USER:
                // Set the table
                queryBuilder.setTables(SportCenterSQLiteHelper.TABLE_SPORT);
                break;
            case SPORT_ID:
                // Adding the ID to the original query
                queryBuilder.appendWhere(SportCenterSQLiteHelper.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                //$FALL-THROUGH$
            case SPORT:
                // Set the table
                queryBuilder.setTables(SportCenterSQLiteHelper.TABLE_SPORT);
                break;
            case COMMENT_ID:
                // Adding the ID to the original query
                queryBuilder.appendWhere(SportCenterSQLiteHelper.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                //$FALL-THROUGH$
            case COMMENT:
                // Set the table
                queryBuilder.setTables(SportCenterSQLiteHelper.TABLE_COMMENT);
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
            case USER:
                id = sqlDB.insert(SportCenterSQLiteHelper.TABLE_USER, null, values);
                retVal = Uri.parse(USER_PATH + "/" + id);
                break;
            case SPORT:
                id = sqlDB.insert(SportCenterSQLiteHelper.TABLE_SPORT, null, values);
                retVal = Uri.parse(SPORT_PATH + "/" + id);
                break;
            case COMMENT:
                id = sqlDB.insert(SportCenterSQLiteHelper.TABLE_COMMENT, null, values);
                retVal = Uri.parse(COMMENT_PATH + "/" + id);
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
            case USER:
                rowsDeleted = sqlDB.delete(SportCenterSQLiteHelper.TABLE_USER,
                        selection,
                        selectionArgs);
                break;
            case USER_ID:
                String idUser = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(SportCenterSQLiteHelper.TABLE_USER,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idUser,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(SportCenterSQLiteHelper.TABLE_USER,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idUser
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            case SPORT:
                rowsDeleted = sqlDB.delete(SportCenterSQLiteHelper.TABLE_SPORT,
                        selection,
                        selectionArgs);
                break;
            case SPORT_ID:
                String idSport = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(SportCenterSQLiteHelper.TABLE_SPORT,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idSport,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(SportCenterSQLiteHelper.TABLE_SPORT,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idSport
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            case COMMENT:
                rowsDeleted = sqlDB.delete(SportCenterSQLiteHelper.TABLE_COMMENT,
                        selection,
                        selectionArgs);
                break;
            case COMMENT_ID:
                String idComment = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(SportCenterSQLiteHelper.TABLE_COMMENT,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idComment,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(SportCenterSQLiteHelper.TABLE_COMMENT,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idComment
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
            case USER:
                rowsUpdated = sqlDB.update(SportCenterSQLiteHelper.TABLE_USER,
                        values,
                        selection,
                        selectionArgs);
                break;
            case USER_ID:
                String idUser = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(SportCenterSQLiteHelper.TABLE_USER,
                            values,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idUser,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(SportCenterSQLiteHelper.TABLE_USER,
                            values,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idUser
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            case SPORT:
                rowsUpdated = sqlDB.update(SportCenterSQLiteHelper.TABLE_SPORT,
                        values,
                        selection,
                        selectionArgs);
                break;
            case SPORT_ID:
                String idSport = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(SportCenterSQLiteHelper.TABLE_SPORT,
                            values,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idSport,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(SportCenterSQLiteHelper.TABLE_SPORT,
                            values,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idSport
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            case COMMENT:
                rowsUpdated = sqlDB.update(SportCenterSQLiteHelper.TABLE_COMMENT,
                        values,
                        selection,
                        selectionArgs);
                break;
            case COMMENT_ID:
                String idComment = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(SportCenterSQLiteHelper.TABLE_COMMENT,
                            values,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idComment,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(SportCenterSQLiteHelper.TABLE_COMMENT,
                            values,
                            SportCenterSQLiteHelper.COLUMN_ID + "=" + idComment
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
