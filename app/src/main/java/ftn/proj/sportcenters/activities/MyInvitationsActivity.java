package ftn.proj.sportcenters.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import ftn.proj.sportcenters.R;
import ftn.proj.sportcenters.adapters.MyInvitationsAdapter;
import ftn.proj.sportcenters.model.Invitation;
import ftn.proj.sportcenters.model.Reservation;
import ftn.proj.sportcenters.model.SportCenter;
import ftn.proj.sportcenters.model.User;

public class MyInvitationsActivity extends AppCompatActivity {
    private ArrayList<Invitation> myInvitations = new ArrayList<Invitation>();
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_invitations);
        mListView=findViewById(R.id.MyInvitationsList);
        loadItems(myInvitations);

        MyInvitationsAdapter adapter = new MyInvitationsAdapter(this, myInvitations);
        mListView.setAdapter(adapter);
    }

    private void loadItems(ArrayList<Invitation> myInvitations) {
       /* myInvitations.add(new Invitation(
                1,
                new Reservation(
                        1,
                        new SportCenter(1, "Albatros", "Novi Sad", R.drawable.albatros, new ArrayList<String>(), new ArrayList<String>()),
                        "Tennis",
                        1000.0,
                        new Date(),
                        "20:00-21:00"),
                new User(1, "tamara"),
                true));
        myInvitations.add(new Invitation(
                1,
                new Reservation(
                        1,
                        new SportCenter(1, "Albatros", "Novi Sad", R.drawable.albatros, new ArrayList<String>(), new ArrayList<String>()),
                        "Tennis",
                        1000.0,
                        new Date(),
                        "20:00-21:00"),
                new User(1, "marko"),
                true));*/
    }
}
