package com.example.missy.PAP;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class AirActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air);

        LineChart chart = (LineChart) findViewById(R.id.airGr);

        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();

        valsComp1.add(new Entry(100.0f,0));
        valsComp1.add(new Entry(50.0f,1));
        valsComp1.add(new Entry(75.0f,2));
        valsComp1.add(new Entry(50.0f,3));

        LineDataSet setComp1 = new LineDataSet(valsComp1,"NO2");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp1);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("1");
        xVals.add("2");
        xVals.add("3");
        xVals.add("4");

        LineData data = new LineData(xVals,dataSets);

        chart.setData(data);
        chart.invalidate();
    }
    public void coClick(View v){
        TextView coText = (TextView)findViewById(R.id.co);
        coText.setBackgroundColor(Color.parseColor("#E1F6FA"));

        TextView so2Text = (TextView)findViewById(R.id.so2);
        so2Text.setBackgroundColor(Color.parseColor("#ffffff"));

        TextView no2Text = (TextView)findViewById(R.id.no2);
        no2Text.setBackgroundColor(Color.parseColor("#ffffff"));

        TextView o3Text = (TextView)findViewById(R.id.o3);
        o3Text.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    public void so2Click(View v){
        TextView coText = (TextView)findViewById(R.id.co);
        coText.setBackgroundColor(Color.parseColor("#ffffff"));

        TextView so2Text = (TextView)findViewById(R.id.so2);
        so2Text.setBackgroundColor(Color.parseColor("#E1F6FA"));

        TextView no2Text = (TextView)findViewById(R.id.no2);
        no2Text.setBackgroundColor(Color.parseColor("#ffffff"));

        TextView o3Text = (TextView)findViewById(R.id.o3);
        o3Text.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    public void no2Click(View v){
        TextView coText = (TextView)findViewById(R.id.co);
        coText.setBackgroundColor(Color.parseColor("#ffffff"));

        TextView so2Text = (TextView)findViewById(R.id.so2);
        so2Text.setBackgroundColor(Color.parseColor("#ffffff"));

        TextView no2Text = (TextView)findViewById(R.id.no2);
        no2Text.setBackgroundColor(Color.parseColor("#E1F6FA"));

        TextView o3Text = (TextView)findViewById(R.id.o3);
        o3Text.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    public void o3Click(View v){
        TextView coText = (TextView)findViewById(R.id.co);
        coText.setBackgroundColor(Color.parseColor("#ffffff"));

        TextView so2Text = (TextView)findViewById(R.id.so2);
        so2Text.setBackgroundColor(Color.parseColor("#ffffff"));

        TextView no2Text = (TextView)findViewById(R.id.no2);
        no2Text.setBackgroundColor(Color.parseColor("#ffffff"));

        TextView o3Text = (TextView)findViewById(R.id.o3);
        o3Text.setBackgroundColor(Color.parseColor("#E1F6FA"));

    }

}
