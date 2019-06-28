package ftn.proj.sportcenters;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import ftn.proj.sportcenters.activities.MyProfileActivity;
import ftn.proj.sportcenters.activities.SportCenterActivity;
import ftn.proj.sportcenters.adapters.DrawerListAdapter;
import ftn.proj.sportcenters.adapters.MainActivityAdapter;
import ftn.proj.sportcenters.database.DBContentProvider;
import ftn.proj.sportcenters.database.DatabaseTool;
import ftn.proj.sportcenters.database.SportCenterSQLiteHelper;
import ftn.proj.sportcenters.model.SportCenter;

public class MainActivity extends AppCompatActivity {

    private ArrayList<SportCenter> mSportCenters = new ArrayList<SportCenter>();
    private ListView mListView;

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
        setContentView(R.layout.activity_main);
        mListView=findViewById(R.id.SportCenterList);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null){
            if(getIntent().getExtras().containsKey("sportName")){
                loadItemsBySport(getIntent().getExtras().getString("sportName"));
            }
            else{
                loadItems(mSportCenters);
                setNewMainActivityAdapter();
            }
        }
        else{
            loadItems(mSportCenters);
            setNewMainActivityAdapter();
        }


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

    private void setNewMainActivityAdapter() {
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
        Cursor cursor = getContentResolver().query(DBContentProvider.CONTENT_URI_SPORT_CENTER,
                columns, null, null,null);
        if(cursor.getCount()==0) {
            DatabaseTool.initDB(this);
            cursor = getContentResolver().query(DBContentProvider.CONTENT_URI_SPORT_CENTER,
                columns,null, null,null);
        }
        while (cursor.moveToNext()) {
            createSportCenter(cursor);
        }
    }

    private void createSportCenter(Cursor cursor){
        SportCenter sc = new SportCenter();
        sc.setId(cursor.getLong(0));
        sc.setName(cursor.getString(1));
        sc.setAddress(cursor.getString(2));
        sc.setImage(cursor.getInt(3));
        mSportCenters.add(sc);
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
            Intent intent = new Intent(MainActivity.this, MyProfileActivity.class);
            startActivity(intent);
            finish();
        }else if(position > 0 && position < mNavItems.size()){
            loadItemsBySport(mNavItems.get(position));
        }else{
            Log.e("DRAWER", "Nesto van opsega!");
        }

        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

    private void loadItemsBySport(String sportName) {
        mSportCenters= new ArrayList<SportCenter>();
        String[] columns1 = {SportCenterSQLiteHelper.COLUMN_ID};
        Cursor cursor1 = getContentResolver().query(DBContentProvider.CONTENT_URI_SPORT,
                columns1, SportCenterSQLiteHelper.COLUMN_NAME + " like ?", new String[] {sportName+"%"},null);
        cursor1.moveToFirst();
        String[] columns2 = {SportCenterSQLiteHelper.COLUMN_ID, SportCenterSQLiteHelper.COLUMN_NAME,
                SportCenterSQLiteHelper.COLUMN_ADDRESS, SportCenterSQLiteHelper.COLUMN_IMAGE};
        Cursor cursor2 = getContentResolver().query(DBContentProvider.CONTENT_URI_SPORT_CENTER,
                columns2, SportCenterSQLiteHelper.COLUMN_SPORTS + " like ?", new String[] {"%"+cursor1.getInt(0)+"%"},null);

        while (cursor2.moveToNext()) {
            createSportCenter(cursor2);
        }
        setNewMainActivityAdapter();
    }
}
