package com.example.frth3176.grubgrab;

import android.app.ListActivity;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DayActivity extends ListActivity {

    String thisDate = "placeholder";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    List freeFood = new ArrayList<>();

    List listBody = new ArrayList<>();
    List listFrom = new ArrayList<>();
    List listLoCoord1 = new ArrayList<>();
    List listLoCoord2 = new ArrayList<>();
    List listSubject = new ArrayList<>();
    List listTime = new ArrayList<>();
    List listLocation = new ArrayList<>();

    ArrayAdapter<Food> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        thisDate = i.getStringExtra("thisDay");

        ListView freeFoodList = getListView();
//        ArrayAdapter<Food> listAdapter;


        // for firebase:

//        ListView foodList = (ListView) findViewById(R.id.listView);
//        listAdapter = new ArrayAdapter<Food>(this, android.R.layout.simple_list_item_1, freeFood);
//        foodList.setAdapter(listAdapter);


        listAdapter = new ArrayAdapter<Food>(this, android.R.layout.simple_list_item_1,freeFood);
        freeFoodList.setAdapter(listAdapter);

        ValueEventListener firebaseListener = new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                freeFood.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Food newFoodEvent = snapshot.getValue(Food.class);
                    Log.d("data_time",newFoodEvent.getTime());
                    Log.d("the_time",thisDate);

                    if (newFoodEvent.getTime().contains(thisDate)==true){
                        freeFood.add(newFoodEvent.getSubject());

                        listBody.add(newFoodEvent.getBody());
                        listFrom.add(newFoodEvent.getFrom());
                        listLoCoord1.add(newFoodEvent.getLoCoord1());
                        listLoCoord2.add(newFoodEvent.getLoCoord2());
                        listSubject.add(newFoodEvent.getSubject());
                        listTime.add(newFoodEvent.getTime());
                        listLocation.add(newFoodEvent.getLocation());

                    }

                    Log.d("data", "Day Value: " + newFoodEvent.getFrom() +" "+ newFoodEvent.getTime());
                }
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("oncreate", "Failed to read value from Firebase!", error.toException());
            }
        };


        ref.addValueEventListener(firebaseListener);

//
//        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
//            public void onItemClick(AdapterView<?> listView, View view, int position, long id){
//                Log.d("data","roger click!");
//                Food foodTapped = (Food) freeFood.get(position);
//                String foodTime = foodTapped.getTime();
//                String foodLocation = foodTapped.getLocation();
//                String foodFrom = foodTapped.getFrom();
//                Double foodLC1 = foodTapped.getLoCoord1();
//                Double foodLC2 = foodTapped.getLoCoord2();
//
//                Intent intent = new Intent(DayActivity.this, EventActivity.class);
//                intent.putExtra("theTime",foodTime);
//                intent.putExtra("theLocation",foodLocation);
//                intent.putExtra("theFrom",foodFrom);
//                intent.putExtra("theLC1",foodLC1);
//                intent.putExtra("theLC2",foodLC2);
//                startActivity(intent);
//            }
//        };

    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id){

        Log.d("data","roger click!");
        String lookfor = String.valueOf(freeFood.get(position));
        int indexnum = listSubject.indexOf(lookfor);

//        Food foodTapped = (Food) freeFood.get(position);

        String[] arrayTime = new String[listTime.size()];
        listTime.toArray(arrayTime);

        String[] arrayLocation = new String[listLocation.size()];
        listLocation.toArray(arrayLocation);

        String[] arrayFrom = new String[listFrom.size()];
        listFrom.toArray(arrayFrom);

        Double[] arrayLoCoord1 = new Double[listLoCoord1.size()];
        listLoCoord1.toArray(arrayLoCoord1);

        Double[] arrayLoCoord2 = new Double[listLoCoord2.size()];
        listLoCoord2.toArray(arrayLoCoord2);

        String[] arrayBody = new String[listBody.size()];
        listBody.toArray(arrayBody);

        String foodTime = arrayTime[indexnum];
        String foodLocation = arrayLocation[indexnum];
        String foodFrom = arrayFrom[indexnum];
        Double foodLC1 = arrayLoCoord1[indexnum];
        Double foodLC2 = arrayLoCoord2[indexnum];
        String foodSubject = lookfor;
        String foodBody = arrayBody[indexnum];

        Intent intent = new Intent(DayActivity.this, EventActivity.class);
        intent.putExtra("theTime",foodTime);
        intent.putExtra("theLocation",foodLocation);
        intent.putExtra("theFrom",foodFrom);
        intent.putExtra("theLC1",foodLC1);
        intent.putExtra("theLC2",foodLC2);
        intent.putExtra("theSubject",foodSubject);
        intent.putExtra("theBody",foodBody);
        startActivity(intent);

    }

}


