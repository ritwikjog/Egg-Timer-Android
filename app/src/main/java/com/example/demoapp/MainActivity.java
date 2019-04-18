package com.example.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.lang.*;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ListView table;

    public void setTimesTable(int i){
        ArrayList<String> arr = new ArrayList<String>();

        for(int j=1; j<=10; j++)
        {
            arr.add(Integer.toString(i*j));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);

        table.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar number = (SeekBar)findViewById(R.id.seekBar);
        table = findViewById(R.id.myListView);
        number.setMax(20);
        number.setProgress(10);

        number.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int timesTable;

                if(progress<min)
                {
                    timesTable = min;
                    number.setProgress(min);
                }else{
                    timesTable = progress;
                }

                setTimesTable(timesTable);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
