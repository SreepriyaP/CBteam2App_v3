package com.cbteam2app.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EventRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registration);

        final BackendHandler db = new BackendHandler();
        // Assign a listener to detect changes to the child items
        // of the database reference.


        final Button mButton = (Button)findViewById(R.id.create);
        final EditText eventName = (EditText)findViewById(R.id.EventName);
        final EditText eventOrganizer = (EditText)findViewById(R.id.Organizer);
        final EditText eventTime = (EditText)findViewById(R.id.Time);
        final TextView eventCount = (TextView)findViewById(R.id.Count);
        final TextView totalEvents = (TextView) findViewById(R.id.CountResult);
        final ListView eventList = (ListView) findViewById(R.id.EventList);

        // Create a new Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);
        // Assign adapter to ListView
        eventList.setAdapter(adapter);


        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        // Create a new child with a auto-generated ID.

                        Events event = new Events();
                        event.setId(1);
                        event.setEventName(eventName.getText().toString());
                        event.setTime(eventTime.getText().toString());
                        event.setOrganizer(eventOrganizer.getText().toString());

                        db.addEvent(event);

                        //resetting the fields
                        eventName.setText("");
                        eventTime.setText("");
                        eventOrganizer.setText("");

                        eventCount.setText("Success");
                    }
                });


        //Update the view on data change
        Firebase mRef = new Firebase("https://cbteam2app.firebaseio.com/Events");

        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {

                adapter.clear();
                for(com.firebase.client.DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    adapter.add(dataSnapshot1.child("event_name").getValue(String.class) +
                                " by " + dataSnapshot1.child("event_organizer").getValue(String.class) +
                                " on " + dataSnapshot1.child("event_time").getValue(String.class));
                }

                long value = dataSnapshot.getChildrenCount();
                totalEvents.setText("Total events :"+String.valueOf(value));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
