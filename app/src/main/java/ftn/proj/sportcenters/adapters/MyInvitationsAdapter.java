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
import ftn.proj.sportcenters.model.Invitation;

public class MyInvitationsAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Invitation> mInvitations;

    public MyInvitationsAdapter(Context mContext, ArrayList<Invitation> mInvitations) {
        this.mContext = mContext;
        this.mInvitations = mInvitations;
    }

    @Override
    public int getCount() {
        return mInvitations.size();
    }

    @Override
    public Object getItem(int position) {
        return mInvitations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mInvitations.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.my_invitation_item, null);
        }
        else {
            view = convertView;
        }

        TextView nameView = (TextView) view.findViewById(R.id.SportCenterName);
        TextView friendView = (TextView) view.findViewById(R.id.FriendName);
        TextView dateView = (TextView) view.findViewById(R.id.ReservationDate);
        TextView periodView = (TextView) view.findViewById(R.id.Period);

        nameView.setText( mInvitations.get(position).getReservation().getSportCenter().getName() );
        friendView.setText( mInvitations.get(position).getUser().getUsername() );
        dateView.setText( mInvitations.get(position).getReservation().getDate().toString() );
        periodView.setText( mInvitations.get(position).getReservation().getPeriod() );

        return view;
    }
}
