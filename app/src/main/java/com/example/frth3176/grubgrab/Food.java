package com.example.frth3176.grubgrab;

import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by frth3176 on 4/29/17.
 */
public class Food {

    public String xBody;
    public String xFrom;
    public Double xLoCoord1;
    public Double xLoCoord2;
    public String xLocation;
    public String xSubject;
    public String xTime;

    public Food(){
    }

    public Food(String newbody, String newfrom, Double newLC1, Double newLC2, String newloc, String newsubject, String newtime){
        xBody = newbody;
        xFrom = newfrom;
        xLoCoord1 = newLC1;
        xLoCoord2 = newLC2;
        xLocation = newloc;
        xSubject = newsubject;
        xTime = newtime;
    }

//
//    public static final Food[] TestData = {
//
//            new Food("Body Text", "From Church", 123456.0,9876543.0,"In Space","SUBJECT!!!","Midnight"),
//
//    };

    /*
    public static final Food[] MayOne = {

            new Food("Take Back the Tap", "UMC 201", "12:00pm", R.drawable.tap),
            new Food("Alumni Breakfast", "CU Alumni Center", "4:00pm", R.drawable.alumni),
            new Food("Physics Colloquim", "Duane Physics 100", "5:00pm", R.drawable.physics),

    };

    public static final Food[] MayTwo = {

            new Food("Church Sandwhich Making", "Christan Church", "2:00pm", R.drawable.pbj),

    };

    public static final Food[] MayThree = {

            new Food("Flying Sphaggheti Monster Club Meeting", "Heaven", "11:11am", R.drawable.pasta),
            new Food("ATLAS Expo Night", "Black Box Studio", "5:00pm", R.drawable.expo),

    };

    public static final Food[] MayFour = {

                   new Food("Thirsty Thursdays", "The Hill", "11:00am", R.drawable.popo),
                   new Food("Mobile Makers", "ATLAS 204", "5:00pm", R.drawable.makers),

    };

    public static final Food[] MayFive = {

                   new Food("Engineers without Borders", "ECEN 350", "5:00pm", R.drawable.border),
                   new Food("Drone Club", "ATLAS 100", "5:30pm", R.drawable.drone),

    };

    public static final Food[] NoEvents = {

            new Food("No Events Today!", "n/a", "n/a", R.drawable.sad),

    };
*/


    public String getBody(){
        return xBody;
    }

    public String getFrom(){
        return xFrom;
    }

    public Double getLoCoord1(){
        return xLoCoord1;
    }

    public Double getLoCoord2(){
        return xLoCoord2;
    }

    public String getLocation(){
        return xLocation;
    }

    public String getSubject(){
        return xSubject;
    }

    public String getTime(){
        return xTime;
    }


//    public String toString(){
//        return this.name;
//    }

}



