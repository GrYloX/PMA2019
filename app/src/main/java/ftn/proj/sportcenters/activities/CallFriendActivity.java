package ftn.proj.sportcenters.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ftn.proj.sportcenters.R;
import ftn.proj.sportcenters.adapters.CallFriendAdapter;
import ftn.proj.sportcenters.model.Reservation;
import ftn.proj.sportcenters.model.User;

public class CallFriendActivity extends AppCompatActivity {

    private Reservation reservation;

    private ArrayList<User> myFriends = new ArrayList<User>();
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_friend);
        reservation = (Reservation) getIntent().getSerializableExtra("reservation");
        mListView=findViewById(R.id.MyFriendsList);
        loadItems(myFriends);

        CallFriendAdapter adapter = new CallFriendAdapter(this, myFriends);
        mListView.setAdapter(adapter);
    }

    private void loadItems(ArrayList<User> myFriends) {
        myFriends.add(new User(1, "tamara"));
        myFriends.add(new User(2, "marko"));
        myFriends.add(new User(3, "goran"));
        myFriends.add(new User(4, "smiljana"));
    }

}
