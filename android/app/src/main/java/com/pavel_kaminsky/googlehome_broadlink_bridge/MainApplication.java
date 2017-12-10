package com.pavel_kaminsky.googlehome_broadlink_bridge;

import android.app.Application;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pavel_kaminsky.googlehome_broadlink_bridge.firebase.Command;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child("queue").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<Command> commandsList = new ArrayList<>();

                for (DataSnapshot commandsSnapshot : dataSnapshot.getChildren()) {
                    commandsList.add(commandsSnapshot.getValue(Command.class));
                }

                if (!isEmpty(commandsList))
                    EventBus.getDefault().post(commandsList.get(commandsList.size() - 1));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
