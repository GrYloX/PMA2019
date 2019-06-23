package ftn.proj.sportcenters;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ftn.proj.sportcenters.activities.RegisterActivity;
import ftn.proj.sportcenters.activities.SportCenterActivity;
import ftn.proj.sportcenters.adapters.MainActivityAdapter;
import ftn.proj.sportcenters.database.DBContentProvider;
import ftn.proj.sportcenters.database.SportCenterSQLiteHelper;
import ftn.proj.sportcenters.model.SportCenter;

public class MainActivity extends AppCompatActivity {

    private ArrayList<SportCenter> mSportCenters = new ArrayList<SportCenter>();
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView=findViewById(R.id.SportCenterList);
        loadItems(mSportCenters);

        MainActivityAdapter adapter = new MainActivityAdapter(this,mSportCenters);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view,
                                     int position, long id) {
                 Uri todoUri = Uri.parse(DBContentProvider.CONTENT_URI_SPORT_CENTER + "/" + id);
                 Intent intent = new Intent(MainActivity.this, SportCenterActivity.class);
                 intent.putExtra("sportCenter", todoUri);
                 startActivity(intent);
                 finish();
                 }
             }
        );
    }

    private void loadItems(ArrayList<SportCenter> mSportCenters) {
        String[] columns = {SportCenterSQLiteHelper.COLUMN_ID, SportCenterSQLiteHelper.COLUMN_NAME,
                SportCenterSQLiteHelper.COLUMN_ADDRESS, SportCenterSQLiteHelper.COLUMN_IMAGE};
        Cursor cursor = getContentResolver().query(DBContentProvider.CONTENT_URI_SPORT_CENTER, columns, null, null,
                null);
        if(cursor.getCount()==0) {
            initDB();
            cursor = getContentResolver().query(DBContentProvider.CONTENT_URI_SPORT_CENTER, columns, null, null,
                    null);
        }
        while (cursor.moveToNext()) {
            createSportCenter(cursor);
        }
    }

    private void initDB() {
        ArrayList<String> workingHours = new ArrayList<String>();
        workingHours.add("Mon 8:00-22:00");
        workingHours.add("Tue 8:00-22:00");
        workingHours.add("Wed 8:00-22:00");
        workingHours.add("Thu 8:00-22:00");

        JSONObject json = new JSONObject();
        try {
            json.put("workingHours", new JSONArray(workingHours));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String workingHoursString = json.toString();

        ContentValues entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "Albatros");
        entry.put(SportCenterSQLiteHelper.COLUMN_ADDRESS, "Novi Sad");
        entry.put(SportCenterSQLiteHelper.COLUMN_IMAGE, R.drawable.albatros);
        entry.put(SportCenterSQLiteHelper.COLUMN_WORKING_HOURS, workingHoursString);

        getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT_CENTER, entry);

        entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "Meridiana");
        entry.put(SportCenterSQLiteHelper.COLUMN_ADDRESS, "Novi Sad");
        entry.put(SportCenterSQLiteHelper.COLUMN_IMAGE, R.drawable.meridiana);
        entry.put(SportCenterSQLiteHelper.COLUMN_WORKING_HOURS, workingHoursString);

        getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT_CENTER, entry);

        entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "DUGA");
        entry.put(SportCenterSQLiteHelper.COLUMN_ADDRESS, "Beograd");
        entry.put(SportCenterSQLiteHelper.COLUMN_IMAGE, R.drawable.albatros);
        entry.put(SportCenterSQLiteHelper.COLUMN_WORKING_HOURS, workingHoursString);

        getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT_CENTER, entry);

        entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "As");
        entry.put(SportCenterSQLiteHelper.COLUMN_ADDRESS, "Novi Sad");
        entry.put(SportCenterSQLiteHelper.COLUMN_IMAGE, R.drawable.meridiana);
        entry.put(SportCenterSQLiteHelper.COLUMN_WORKING_HOURS, workingHoursString);

        getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT_CENTER, entry);

        entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "Hattrick");
        entry.put(SportCenterSQLiteHelper.COLUMN_ADDRESS, "Niš");
        entry.put(SportCenterSQLiteHelper.COLUMN_IMAGE, R.drawable.albatros);
        entry.put(SportCenterSQLiteHelper.COLUMN_WORKING_HOURS, workingHoursString);

        getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT_CENTER, entry);

        entry = new ContentValues();
        entry.put(SportCenterSQLiteHelper.COLUMN_NAME, "Maxbet");
        entry.put(SportCenterSQLiteHelper.COLUMN_ADDRESS, "Vršac");
        entry.put(SportCenterSQLiteHelper.COLUMN_IMAGE, R.drawable.albatros);
        entry.put(SportCenterSQLiteHelper.COLUMN_WORKING_HOURS, workingHoursString);

        getContentResolver().insert(DBContentProvider.CONTENT_URI_SPORT_CENTER, entry);
    }

    private void createSportCenter(Cursor cursor){
        SportCenter sc = new SportCenter();
        sc.setId(cursor.getInt(0));
        sc.setName(cursor.getString(1));
        sc.setAddress(cursor.getString(2));
        sc.setImage(cursor.getInt(3));
        mSportCenters.add(sc);
    }
}
