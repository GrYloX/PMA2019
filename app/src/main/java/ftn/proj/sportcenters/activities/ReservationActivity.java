package ftn.proj.sportcenters.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ftn.proj.sportcenters.R;
import ftn.proj.sportcenters.model.SportCenter;

public class ReservationActivity extends AppCompatActivity {

    private SportCenter sportCenter = new SportCenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        sportCenter = (SportCenter) getIntent().getSerializableExtra("sportCenter");

        TextView nameView = (TextView) findViewById(R.id.Name);
        nameView.setText(sportCenter.getName());

    }

}
