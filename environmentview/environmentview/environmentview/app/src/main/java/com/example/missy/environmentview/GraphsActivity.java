package com.example.missy.environmentview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

import java.util.ArrayList;

public class GraphsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        /*
        LineChart chart = (LineChart)findViewById(R.id.PMchar);
        chart.setOnClickListener((View.OnClickListener)this);
        chart.setTouchEnabled(true);

        Intent air_intent = new Intent(this, AirActivity.class);
        startActivity(air_intent);*/
    }

    public void AClick(View v){
        Intent air_intent = new Intent(this, AirActivity.class);
        startActivity(air_intent);
    }

    public void HClick(View v){
        Intent health_intent = new Intent(this, HeartActivity.class);
        startActivity(health_intent);
    }

    public void nowclick(View v){
        Intent now_intent = new Intent(this, SearchActivity.class);
        startActivity(now_intent);
    }

    public void serchclick(View v){
        Intent sintent = new Intent(this, MapActivity.class);
        startActivity(sintent);
    }

    public void Historyclick(View v){
        Intent hintent = new Intent(this, HistoryActivity.class);
        startActivity(hintent);
    }

    public void pmClick(View v){
        Intent pmintent = new Intent(this, PMActivity.class);
        startActivity(pmintent);
    }
}
