package com.pavel_kaminsky.googlehome_broadlink_bridge;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pavel_kaminsky.googlehome_broadlink_bridge.models.Command;
import com.pavel_kaminsky.googlehome_broadlink_bridge.models.ToastError;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static android.content.ContentValues.TAG;

public class MainApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference queue = mDatabase.child("queue");

        queue.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) {
                    Command command = dataSnapshot.getValue(Command.class);
                    EventBus.getDefault().post(command);
                    queue.setValue(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        EventBus.getDefault().unregister(this);
    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ToastError err) {
        Toast.makeText(getApplicationContext(), err.getError(), Toast.LENGTH_LONG).show();
    }
}
