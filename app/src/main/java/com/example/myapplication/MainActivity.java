package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView hour, minute;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hour = findViewById(R.id.hour);
        minute = findViewById(R.id.minute);
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final String timeInWords[] = TextTime.currentTimeInWords();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hour.setText(timeInWords[0]);
                        minute.setText(timeInWords[1]);
                    }
                });
            }
        }, 0, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

}