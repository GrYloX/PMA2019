package ftn.proj.sportcenters.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ftn.proj.sportcenters.R;
import ftn.proj.sportcenters.activities.MyProfileActivity;
import ftn.proj.sportcenters.database.DBContentProvider;
import ftn.proj.sportcenters.database.SportCenterSQLiteHelper;
import ftn.proj.sportcenters.fragments.CallFriendFragment;
import ftn.proj.sportcenters.model.Reservation;

public class MyReservationsAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Reservation> mReservations;

    public MyReservationsAdapter(Context mContext, ArrayList<Reservation> mReservations) {
        this.mContext = mContext;
        this.mReservations = mReservations;
    }

    @Override
    public int getCount() {
        return mReservations.size();
    }

    @Override
    public Object getItem(int position) {
        return mReservations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mReservations.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.my_reservation_item, null);
        }
        else {
            view = convertView;
        }

        TextView nameView = (TextView) view.findViewById(R.id.SportCenterName);
        TextView priceView = (TextView) view.findViewById(R.id.Price);
        TextView dateView = (TextView) view.findViewById(R.id.ReservationDate);
        TextView periodView = (TextView) view.findViewById(R.id.Period);
        final TextView idView = (TextView) view.findViewById(R.id.MyReservationId);

        String[] column = {
                SportCenterSQLiteHelper.COLUMN_NAME};
        Cursor cursor = mContext.getContentResolver().query(
                Uri.parse(DBContentProvider.CONTENT_URI_SPORT_CENTER + "/" +
                        mReservations.get(position).getSportCenterId()),column,null,null,
                null);

        cursor.moveToFirst();
        nameView.setText( cursor.getString(0) );
        priceView.setText( String.valueOf(mReservations.get(position).getPrice()) );
        dateView.setText( mReservations.get(position).getDate().toString() );
        periodView.setText( mReservations.get(position).getPeriod() );
        idView.setText( String.valueOf(mReservations.get(position).getId()) );

        Button mPozovi = view.findViewById(R.id.btnPozovi);
        mPozovi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallFriendFragment fragment = new CallFriendFragment();
                Bundle arguments = new Bundle();
                arguments.putSerializable("reservation",mReservations.get(position));
                fragment.setArguments(arguments);
                MyProfileActivity activity = (MyProfileActivity) mContext;
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.MyReservationsFragmentId, fragment).commit();
            }
        });
        return view;
    }
}
