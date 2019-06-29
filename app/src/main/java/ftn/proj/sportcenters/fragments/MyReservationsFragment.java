package ftn.proj.sportcenters.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ftn.proj.sportcenters.R;
import ftn.proj.sportcenters.adapters.MyReservationsAdapter;
import ftn.proj.sportcenters.model.Reservation;

public class MyReservationsFragment extends Fragment {

    private ArrayList<Reservation> myReservations = new ArrayList<Reservation>();
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_reservations_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        mListView=view.findViewById(R.id.MyReservationsList);
        loadItems(myReservations);

        MyReservationsAdapter adapter = new MyReservationsAdapter(this.getContext(), myReservations);
        mListView.setAdapter(adapter);

    }

    private void loadItems(ArrayList<Reservation> myReservations) {
        myReservations.add(new Reservation(
                1,
                1, 1,
                1,
                1000.0,
                "Subota bajo moj",
                "20:00-21:00"));

    }

}
