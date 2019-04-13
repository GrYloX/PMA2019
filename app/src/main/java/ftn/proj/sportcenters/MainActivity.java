package ftn.proj.sportcenters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

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
    }

    // za sada samo pravimo random
    private void loadItems(ArrayList<SportCenter> mSportCenters) {
        mSportCenters.add(new SportCenter(1, "Albatros", "Novi Sad", R.drawable.albatros));
        mSportCenters.add(new SportCenter(2, "Meridiana", "Novi Sad", R.drawable.meridiana));
        mSportCenters.add(new SportCenter(3, "DUGA", "Beograd", R.drawable.albatros));
        mSportCenters.add(new SportCenter(4, "As", "Novi Sad", R.drawable.meridiana));
        mSportCenters.add(new SportCenter(5, "Hattrick", "Niš", R.drawable.albatros));
        mSportCenters.add(new SportCenter(5, "Maxbet", "Vršac", R.drawable.albatros));
    }
}
