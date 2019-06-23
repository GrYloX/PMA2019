package ftn.proj.sportcenters.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ftn.proj.sportcenters.R;
import ftn.proj.sportcenters.model.User;

public class CallFriendAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<User> myFriends;

    public CallFriendAdapter(Context mContext, ArrayList<User> myFriends) {
        this.mContext = mContext;
        this.myFriends = myFriends;
    }

    @Override
    public int getCount() {
        return myFriends.size();
    }

    @Override
    public Object getItem(int position) {
        return myFriends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myFriends.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.my_friend_item, null);
        }
        else {
            view = convertView;
        }

        TextView nameView = (TextView) view.findViewById(R.id.FriendUsername);
        nameView.setText(myFriends.get(position).getUsername());

        return view;
    }
}
