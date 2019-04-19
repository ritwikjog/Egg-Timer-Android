package com.example.demoapp;

import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.lang.*;
import java.util.ArrayList;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {


    SeekBar timer;
    TextView timeLeft;
    boolean buttonIsClicked = false;
    Button button;
    CountDownTimer time;

    public void setTextValue(int progress){
        int hours = (int) progress/60;
        int secs = progress%60;
        String seconds = Integer.toString(secs);
        if(secs<10)
        {
            seconds = "0" + seconds;
        }
        timeLeft.setText(hours + ":" + seconds);
    }

    public void controlTimer(View view){

        if(!buttonIsClicked){
                time = new CountDownTimer(timer.getProgress()*1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    setTextValue((int)millisUntilFinished/1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    setTextValue(30);
                    timer.setProgress(30);
                    button.setText("GO!");
                    buttonIsClicked = false;
                    timer.setEnabled(true);
                }
            }.start();
            button.setText("RESET!");
            timer.setEnabled(false);
            buttonIsClicked = true;
        }

        else{
            button.setText("GO!");
            timer.setEnabled(true);
            buttonIsClicked = false;
            setTextValue(30);
            timer.setProgress(30);
            time.cancel();
        }



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (SeekBar) findViewById(R.id.timerSeekBar);
        timeLeft = (TextView) findViewById(R.id.timerTextView);
        button = (Button) findViewById(R.id.button);
        timer.setMax(600);

        setTextValue(30);

        timer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                setTextValue(seekBar.getProgress());
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
