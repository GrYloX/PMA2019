package ftn.proj.sportcenters.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ftn.proj.sportcenters.R;
import ftn.proj.sportcenters.database.DBContentProvider;
import ftn.proj.sportcenters.database.SportCenterSQLiteHelper;
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

        String[] column = {
                SportCenterSQLiteHelper.COLUMN_SPORT_CENTER_ID, SportCenterSQLiteHelper.COLUMN_DATE,
        SportCenterSQLiteHelper.COLUMN_PERIOD};
        String[] column2 = {
                SportCenterSQLiteHelper.COLUMN_NAME};
        String[] column3 = {
                SportCenterSQLiteHelper.COLUMN_USERNAME};
        //sport_center_id of reservation
        Cursor cursor1 = mContext.getContentResolver().query(
                Uri.parse(DBContentProvider.CONTENT_URI_RESERVATION + "/" +
                mInvitations.get(position).getReservationId()),column,null,null,
                null);
        cursor1.moveToFirst();
        //sport_center_name of sport_center
        Cursor cursor2 = mContext.getContentResolver().query(
                Uri.parse(DBContentProvider.CONTENT_URI_SPORT_CENTER + "/" +
                        cursor1.getLong(0)),column2,null,null,
                null);
        nameView.setText( cursor2.getString(0));

        Cursor cursor3 = mContext.getContentResolver().query(
                Uri.parse(DBContentProvider.CONTENT_URI_USER + "/" +
                        mInvitations.get(position).getUserId()),column3,null,null,
                null);

        cursor2.moveToFirst();
        cursor3.moveToFirst();
        friendView.setText( cursor3.getString(0) );
        dateView.setText( cursor1.getString(1) );
        periodView.setText( cursor1.getString(2) );

        return view;
    }
}
