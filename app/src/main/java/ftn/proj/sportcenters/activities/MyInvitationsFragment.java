package ftn.proj.sportcenters.activities;

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
import ftn.proj.sportcenters.adapters.MyInvitationsAdapter;
import ftn.proj.sportcenters.model.Invitation;

public class MyInvitationsFragment extends Fragment {
    private ArrayList<Invitation> myInvitations = new ArrayList<Invitation>();
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_invitations_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        mListView=view.findViewById(R.id.MyInvitationsList);
        loadItems(myInvitations);

        MyInvitationsAdapter adapter = new MyInvitationsAdapter(this.getContext(), myInvitations);
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
