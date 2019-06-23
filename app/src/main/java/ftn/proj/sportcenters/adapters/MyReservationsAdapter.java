package ftn.proj.sportcenters.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ftn.proj.sportcenters.R;
import ftn.proj.sportcenters.activities.CallFriendActivity;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
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

        nameView.setText( mReservations.get(position).getSportCenter().getName() );
        priceView.setText( String.valueOf(mReservations.get(position).getPrice()) );
        dateView.setText( mReservations.get(position).getDate().toString() );
        periodView.setText( mReservations.get(position).getPeriod() );
        idView.setText( String.valueOf(mReservations.get(position).getId()) );

        Button mPozovi = view.findViewById(R.id.btnPozovi);
        mPozovi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CallFriendActivity.class);
                intent.putExtra("reservation", mReservations.get(position));
                mContext.startActivity(intent);
                //mContext.finish();
            }
        });

        return view;
    }
}
