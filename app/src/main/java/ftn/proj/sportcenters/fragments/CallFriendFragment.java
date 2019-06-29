package ftn.proj.sportcenters.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ftn.proj.sportcenters.R;
import ftn.proj.sportcenters.adapters.CallFriendAdapter;
import ftn.proj.sportcenters.model.Reservation;
import ftn.proj.sportcenters.model.User;

public class CallFriendFragment extends Fragment {

    private Reservation reservation;

    private ArrayList<User> myFriends = new ArrayList<User>();
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.call_friend_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        reservation = (Reservation) args.getSerializable("reservation");
        mListView=view.findViewById(R.id.MyFriendsList);
        loadItems(myFriends);

        CallFriendAdapter adapter = new CallFriendAdapter(this.getContext(), myFriends);
        mListView.setAdapter(adapter);
    }

    private void loadItems(ArrayList<User> myFriends) {
        myFriends.add(new User(1, "tamara"));
        myFriends.add(new User(2, "marko"));
        myFriends.add(new User(3, "goran"));
        myFriends.add(new User(4, "smiljana"));
    }

}
