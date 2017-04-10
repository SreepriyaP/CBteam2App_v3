package com.cbteam2app.android;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by asad on 26/03/17.
 */

public class BackendHandler {
    private static final String TABLE_EVENTS = "Events";
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public BackendHandler(){}


    public void addEvent(Events event){
        final DatabaseReference myRef = database.getReference("Events");
        // Create a new child with a auto-generated ID.
        DatabaseReference childRef = myRef.push();

        childRef.child("event_id").setValue(event.eventId);
        childRef.child("event_name").setValue(event.eventName);
        childRef.child("event_organizer").setValue(event.organizer);
        childRef.child("event_time").setValue(event.time);

    }


}
