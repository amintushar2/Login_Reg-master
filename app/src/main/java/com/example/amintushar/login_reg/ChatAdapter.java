package com.example.amintushar.login_reg;

import android.content.Context;
import android.os.Message;
import android.support.constraint.solver.widgets.Snapshot;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

class  Messages {
    String sender,msg;

    public Messages(String sender, String msg) {
        this.sender = sender;
        this.msg = msg;
    }
}

public class ChatAdapter extends BaseAdapter {


    DatabaseReference ref;
    ArrayList<DataSnapshot> snapshots;
    Context context;
    int resource;
    ChildEventListener cListener =new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            snapshots .add(dataSnapshot);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    public ChatAdapter(Context context,int resource,DatabaseReference ref ) {
        this.ref = ref.child("ChatData");

        this.context = context;
        this.resource= resource;

        this.ref.addChildEventListener(cListener);
        snapshots = new ArrayList<DataSnapshot>();
    }

    @Override
    public int getCount() {
        return snapshots.size();
    }

    @Override
    public MyText getItem(int position) {
        return snapshots.get(position).getValue(MyText.class);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(resource,parent,false);
        TextView sender = row.findViewById(R.id.senderId);
        TextView msg = row.findViewById(R.id.messageId);

        MyText message =  getItem(position);
        sender.setText(message.uEmail);
        msg.setText(message.umsg);



        return row;
    }


}
