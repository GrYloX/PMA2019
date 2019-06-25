package ftn.proj.sportcenters.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import ftn.proj.sportcenters.R;
import ftn.proj.sportcenters.adapters.MyReservationsAdapter;
import ftn.proj.sportcenters.model.Reservation;
import ftn.proj.sportcenters.model.SportCenter;

public class MyReservationsActivity extends AppCompatActivity {

    private ArrayList<Reservation> myReservations = new ArrayList<Reservation>();
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);
        mListView=findViewById(R.id.MyReservationsList);
        loadItems(myReservations);

        MyReservationsAdapter adapter = new MyReservationsAdapter(this, myReservations);
        mListView.setAdapter(adapter);
    }

    private void loadItems(ArrayList<Reservation> myReservations) {
        /*myReservations.add(new Reservation(
                1,
                userId, new SportCenter(1, "Albatros", "Novi Sad", R.drawable.albatros, new ArrayList<String>(), new ArrayList<String>()),
                "Tennis",
                1000.0,
                new Date(),
                "20:00-21:00"));
        myReservations.add(new Reservation(
                1,
                userId, new SportCenter(1, "Albatros", "Novi Sad", R.drawable.albatros, new ArrayList<String>(),new ArrayList<String>()),
                "Basketball",
                1500.0,
                new Date(),
                "19:00-21:00"));*/
    }
}
