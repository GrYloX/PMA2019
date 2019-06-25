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

public class CommentActivity extends AppCompatActivity {

    private SportCenter sportCenter = new SportCenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        sportCenter = (SportCenter) getIntent().getSerializableExtra("sportCenter");

        TextView nameView = (TextView) findViewById(R.id.Name);
        nameView.setText(sportCenter.getName());

    }

}
