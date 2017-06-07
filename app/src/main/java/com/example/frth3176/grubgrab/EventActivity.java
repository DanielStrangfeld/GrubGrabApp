package com.example.frth3176.grubgrab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class EventActivity extends Activity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Intent i = getIntent();
        //   thisDate = i.getStringExtra("thisDay");

        //   int foodnum = (Integer) getIntent().getExtras().get("eventId");
        final String thisLocation = i.getStringExtra("theLocation");
        final String thisTime = i.getStringExtra("theTime");
        String thisFrom = i.getStringExtra("theFrom");
        final Double thisLC1 = i.getDoubleExtra("theLC1",0.0);
        final Double thisLC2 = i.getDoubleExtra("theLC2",0.0);

        Log.d("data1",thisTime);
        Log.d("data2",thisFrom);

        Food food;

        TextView foodName = (TextView) findViewById(R.id.food_name);
        foodName.setText(thisFrom);

        TextView foodLoc = (TextView) findViewById(R.id.food_location);
        foodLoc.setText(thisLocation);

        TextView foodTime = (TextView) findViewById(R.id.food_time);
        foodTime.setText(thisTime);

        //Switches to the maps activity
        final Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(EventActivity.this, MapsActivity.class);

                intent.putExtra("theLC1",thisLC1);
                intent.putExtra("theLC2",thisLC2);
                intent.putExtra("theLocation",thisLocation);
                intent.putExtra("theTime",thisTime);

                startActivity(intent);
            }
        });

    }
}

