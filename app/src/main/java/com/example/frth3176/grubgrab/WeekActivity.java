package com.example.frth3176.grubgrab;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class WeekActivity extends ListActivity {

    private String year;
    private String month;
    private String dayOfMonth;
    private Date thisDate;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    List listTime = new ArrayList<>();
    List freeFood = new ArrayList<>();
    ArrayAdapter<Food> listAdapter;

    public static final String[] DaysInWeek = new String[7];
    public static final String[] DaysInWeektoDisplay = new String[7];


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        year = i.getStringExtra("year");
        month = i.getStringExtra("month");
        dayOfMonth = i.getStringExtra("dayOfMonth");

        String thisDate = i.getStringExtra("thisDate");

        String sourceDate = thisDate;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date myDate = null;

        try {

            myDate = format.parse(sourceDate);

        } catch (ParseException e) {

            e.printStackTrace();

        }

        //myDate = DateUtil.addDays(myDate, 1);

        ListView freeFoodTimes = getListView();



        String[] arrayTime = new String[freeFood.size()];
        freeFood.toArray(arrayTime);

        for(int n=0;n<7;n++) {

            String tempString = String.valueOf(DateUtil.addDays(myDate, n));
            String[] tempParts = tempString.split("00:00:00 MDT");
            final String tempMerge = tempParts[0] + tempParts[1];

            DaysInWeek[n] = tempMerge;
            DaysInWeektoDisplay[n] = tempMerge;
            final int index = n;

            ValueEventListener firebaseListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    freeFood.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Food newFoodEvent = snapshot.getValue(Food.class);
                        Log.d("data_time", newFoodEvent.getTime());
                        freeFood.add(newFoodEvent.getTime());
                        Log.d("data", "I DONT UNDERSTNAD WHY THIS DOESNT WORK");

                        if (newFoodEvent.getTime().contains(tempMerge)==true) {

                            Log.d("data", "INSIDE IF STATEMENT");

                            DaysInWeektoDisplay[index] = DaysInWeektoDisplay[index] + " -> FREE FOOD HERE!";

                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w("oncreate", "Failed to read value from Firebase!", error.toException());
                }

            };

//            for(int z=0;z<freeFood.size();z++) {
//
//                Log.d("data_time_1", arrayTime[z]);
//                Log.d("data_time_2", DaysInWeek[n]);
//
//                if (arrayTime[z]==(DaysInWeek[n])) {
//
//                    Log.d("data", "INSIDE IF STATEMENT");
//
//                    DaysInWeektoDisplay[n] = DaysInWeektoDisplay[n] + " -> FREE FOOD HERE!";
//
//                }
//
//            }

        }

        ListView listDaysInWeek = getListView();

        ArrayAdapter<String> itemsAdapter =

                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DaysInWeektoDisplay);

        //       ListView listView = (ListView) findViewById(R.id.lvItems);

        listDaysInWeek.setAdapter(itemsAdapter);

    }


    @Override

    public void onListItemClick(ListView listView, View view, int position, long id) {

        Log.d("data",DaysInWeektoDisplay[position]);

        Intent intent = new Intent(WeekActivity.this, DayActivity.class);
        intent.putExtra("dayId",(int) id);
        intent.putExtra("thisDay", DaysInWeek[position]);

        //intent.putExtra("thisDate",thisDate);

        startActivity(intent);

    }

}

