package ftn.proj.sportcenters.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ftn.proj.sportcenters.R;
import ftn.proj.sportcenters.model.SportCenter;

public class SportCenterActivity extends AppCompatActivity {

    private SportCenter sportCenter = new SportCenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_center);
        sportCenter = (SportCenter) getIntent().getSerializableExtra("sportCenter");
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
    }

}
