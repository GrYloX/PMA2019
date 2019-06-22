package ftn.proj.sportcenters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ftn.proj.sportcenters.activities.RegisterActivity;
import ftn.proj.sportcenters.activities.SportCenterActivity;
import ftn.proj.sportcenters.adapters.MainActivityAdapter;
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
                 SportCenter choosenCenter = mSportCenters.get((int) id - 1);
                 Intent intent = new Intent(MainActivity.this, SportCenterActivity.class);
                 intent.putExtra("sportCenter", choosenCenter);
                 startActivity(intent);
                 finish();
                 }
             }
        );
    }

    // za sada samo pravimo random
    private void loadItems(ArrayList<SportCenter> mSportCenters) {
        ArrayList<String> workingHours = new ArrayList<String>();
        workingHours.add("Mon 8:00-22:00");
        workingHours.add("Tue 8:00-22:00");
        workingHours.add("Wed 8:00-22:00");
        workingHours.add("Thu 8:00-22:00");
        mSportCenters.add(new SportCenter(1, "Albatros", "Novi Sad", R.drawable.albatros, workingHours));
        mSportCenters.add(new SportCenter(2, "Meridiana", "Novi Sad", R.drawable.meridiana, workingHours));
        mSportCenters.add(new SportCenter(3, "DUGA", "Beograd", R.drawable.albatros, workingHours));
        mSportCenters.add(new SportCenter(4, "As", "Novi Sad", R.drawable.meridiana, workingHours));
        mSportCenters.add(new SportCenter(5, "Hattrick", "Niš", R.drawable.albatros, workingHours));
        mSportCenters.add(new SportCenter(6, "Maxbet", "Vršac", R.drawable.albatros, workingHours));
    }
}
