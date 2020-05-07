package com.example.cst_438_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cst_438_project3.DB.AppDatabase;
import com.example.cst_438_project3.DB.WeightLogDAO;
import com.example.cst_438_project3.Objects.User;
import com.example.cst_438_project3.Objects.WeightLog;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WeightTracker extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series1;

    private ArrayList<String> mWeightEntries = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_tracker);

        double x, y;
        x = 0;

        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.getGridLabelRenderer().setVerticalAxisTitle("Weight - (lbs)");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Entry - #");
        graph.getViewport().setXAxisBoundsManual(true);

        graph.getViewport().setMinX(0);
        //max = size of the entries list + 1
        graph.getViewport().setMaxX(5);

        //max = max weight + 1
        graph.getViewport().setMaxY(175);

        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);

        series1 = new LineGraphSeries<>();

        //pull all the weight entries that has the User's ID and store them in a list
            //if the list is empty, prompt user to enter a new entry
            //else, append to the graph


        //Get extras from Login
        final AtomicInteger user_id = new AtomicInteger(-1);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                user_id.set(extras.getInt("user_id"));
            }
        } else {
            user_id.set((int) savedInstanceState.getSerializable("user_id"));
        }

        //Get current logged in user
        User user = AppDatabase.getAppDatabase(WeightTracker.this).
                userDAO().getUserByID(user_id.get());

        Integer userID = user_id.get();

        List<WeightLog> weightLogs = AppDatabase.getAppDatabase(WeightTracker.this).
                weightLogDAO().getAllWeightLogs();


        Integer entryNumber = 0;
        for(WeightLog w : weightLogs){
            if(w.getUserID() == userID) {
                series1.appendData(new DataPoint(entryNumber,w.getWeight()), true, 100);
                entryNumber++;

                //max = size of the entries list + 1
                graph.getViewport().setMaxX(entryNumber + 1);
            }
        }
        //iterate the list

        //dummy data
//        series1.appendData(new DataPoint(0,170), true, 100);
//        series1.appendData(new DataPoint(1,173), true, 100);
//        series1.appendData(new DataPoint(2,169), true, 100);
//        series1.appendData(new DataPoint(3,166), true, 100);
//        series1.appendData(new DataPoint(4,167), true, 100);

        graph.addSeries(series1);
    }
}
