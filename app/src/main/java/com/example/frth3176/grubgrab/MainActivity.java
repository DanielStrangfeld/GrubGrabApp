package com.example.frth3176.grubgrab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.GridLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


//Calender view
public class MainActivity extends Activity {

    RelativeLayout rl;
    CalendarView cal;
    String thisDate = "placeholder";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    @SuppressLint({ "NewApi","NewApi" })

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl = (RelativeLayout) findViewById(R.id.rl);

        cal = new CalendarView(MainActivity.this);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                ((int) LayoutParams.MATCH_PARENT, (int) LayoutParams.MATCH_PARENT);

        params.topMargin = 100;
        cal.setLayoutParams(params);
        rl.addView(cal);

        //What type of view the calender has
        cal.setOnDateChangeListener(new OnDateChangeListener() {

            @Override

            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                Toast.makeText(getBaseContext(),"Viewing the Week After\n\n"
                                +dayOfMonth+" : "+(month+1)+" : "+year ,

                        Toast.LENGTH_LONG).show();

                month = month+1; // to correct for java starting months at zero

                thisDate = year + "-" + month + "-" + dayOfMonth;

                Log.d("year: ",  Integer.toString(year));
                Log.d("month: ",  Integer.toString(month));
                Log.d("day: ",  Integer.toString(dayOfMonth));
                Log.d("thisDate: ", thisDate);

                //This allows you to go to the week view (use this concept for the map)
                Intent intent = new Intent(MainActivity.this, WeekActivity.class);

                intent.putExtra("year",year);
                intent.putExtra("month",month);
                intent.putExtra("dayOfMonth",dayOfMonth);
                intent.putExtra("thisDate",thisDate);

                startActivity(intent);

                //startActivity(new Intent(MainActivity.this, WeekActivity.class));

            }

        });

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

}

