package com.example.cst_438_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class WeightTracker extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series1;

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

        //dummy data
        series1.appendData(new DataPoint(0,170), true, 100);
        series1.appendData(new DataPoint(1,173), true, 100);
        series1.appendData(new DataPoint(2,169), true, 100);
        series1.appendData(new DataPoint(3,166), true, 100);
        series1.appendData(new DataPoint(4,167), true, 100);

        graph.addSeries(series1);
    }
}
