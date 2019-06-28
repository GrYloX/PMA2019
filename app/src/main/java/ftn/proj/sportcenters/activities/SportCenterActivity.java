package ftn.proj.sportcenters.activities;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ftn.proj.sportcenters.MainActivity;
import ftn.proj.sportcenters.R;
import ftn.proj.sportcenters.adapters.DrawerListAdapter;
import ftn.proj.sportcenters.database.DBContentProvider;
import ftn.proj.sportcenters.database.SportCenterSQLiteHelper;
import ftn.proj.sportcenters.model.SportCenter;

public class SportCenterActivity extends AppCompatActivity {

    private SportCenter sportCenter = new SportCenter();
    private ArrayList<String> mNavItems = new ArrayList<String>();
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mDrawerPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_center);
        Uri todoUri = getIntent().getExtras().getParcelable("sportCenter");
        sportCenter = loadSportCenter(todoUri);// (SportCenter) getIntent().getSerializableExtra("sportCenter");
//        sportCenter = (SportCenter) getIntent().getExtras().get("sportCenter");
        ImageView imageView = (ImageView) findViewById(R.id.Image);
        imageView.setImageResource(sportCenter.getImage());

        TextView nameView = (TextView) findViewById(R.id.Name);
        nameView.setText(sportCenter.getName());

        TextView workingHoursView = (TextView) findViewById(R.id.WorkingHours);
        StringBuilder sb = new StringBuilder();
        sb.append("Working hours: \n");
        for(String wh : sportCenter.getWorkingHours()) {
            sb.append(wh);
            sb.append("\n");
        }
        workingHoursView.setText(sb.toString());

        TextView addressView = (TextView) findViewById(R.id.Address);
        addressView.setText(sportCenter.getAddress());

        Button mComment = findViewById(R.id.Comment);
        mComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SportCenterActivity.this, CommentActivity.class);
                intent.putExtra("sportCenter", sportCenter);
                startActivity(intent);
                finish();
            }
        });

        Button mReservation = findViewById(R.id.Reserve);
        mReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SportCenterActivity.this, ReservationActivity.class);
                intent.putExtra("sportCenter", sportCenter);
                startActivity(intent);
                finish();
            }
        });

        prepareMenu(mNavItems);
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerList = (ListView) findViewById(R.id.navList);

        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        DrawerListAdapter navDrawerAdapter = new DrawerListAdapter(this, mNavItems);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerList.setAdapter(navDrawerAdapter);
    }

    private SportCenter loadSportCenter(Uri todoUri) {

        String[] allColumns = { SportCenterSQLiteHelper.COLUMN_ID,
                SportCenterSQLiteHelper.COLUMN_NAME, SportCenterSQLiteHelper.COLUMN_ADDRESS,
                SportCenterSQLiteHelper.COLUMN_IMAGE, SportCenterSQLiteHelper.COLUMN_WORKING_HOURS };

        Cursor cursor = getContentResolver().query(todoUri, allColumns, null, null,
                null);

        cursor.moveToFirst();

        SportCenter sc = new SportCenter();
        sc.setId(cursor.getLong(0));
        sc.setName(cursor.getString(1));
        sc.setAddress(cursor.getString(2));
        sc.setImage(cursor.getInt(3));
        JSONObject json = null;
        try {
            json = new JSONObject(cursor.getString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray items = json.optJSONArray("workingHours");
        ArrayList<String> workingHours = new ArrayList<String>();
        for(int i = 0; i<items.length(); i++){
            try {
                workingHours.add(items.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        sc.setWorkingHours(workingHours);
        return sc;
    }

    private void prepareMenu(ArrayList<String> mNavItems ){
        mNavItems.add("Moj profil");
        String[] column = {
                SportCenterSQLiteHelper.COLUMN_NAME};
        Cursor cursor = getContentResolver().query(
                DBContentProvider.CONTENT_URI_SPORT,column,null,null,
                null);
        while (cursor.moveToNext()) {
            mNavItems.add(cursor.getString(0));
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItemFromDrawer(position);
        }
    }
    private void selectItemFromDrawer(int position) {
        if(position == 0){
            Intent intent = new Intent(SportCenterActivity.this, MyProfileActivity.class);
            startActivity(intent);
            finish();
        }else if(position > 0 && position < mNavItems.size()){
            Intent intent = new Intent(SportCenterActivity.this, MainActivity.class);
            intent.putExtra("sportName", mNavItems.get(position));
            startActivity(intent);
            finish();
        }else{
            Log.e("DRAWER", "Nesto van opsega!");
        }

        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

}
