package com.example.twister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Random rand = new Random();
    private final int max = 4;
    private final int min = 1;
    private Handler handler;
    private Runnable runnable;
    private String limb;
    private String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStart(View v) {
        final EditText delay = (EditText) findViewById(R.id.delay);
        int delayInt = Integer.parseInt(delay.getText().toString());
        twist(delayInt);
    }

    public void onStop(View v) {
        handler.removeCallbacks(runnable);
    }

    private void twist(int delay) {
        chooseColor();
        chooseLimb();
        speak();
        twisterLoop(delay);
    }

    private void speak() {
        new Speech("Move your " + limb + " to " + color + "", this);

    }


    public void twisterLoop(int delay) {
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                chooseColor();
                chooseLimb();
                speak();
                twisterLoop(delay);
            }
        }, delay * 1000);
    }

    private void chooseLimb() {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        TextView limbText = findViewById(R.id.limb);
        ImageView limbImage = findViewById(R.id.imageLimb);
        switch (randomNum) {
            case 1:
                limbText.setText("LEFT FOOT");
                limbImage.setImageResource(R.mipmap.leftfoot_foreground);
                limb = "left foot";
                break;
            case 2:
                limbText.setText("LEFT HAND");
                limbImage.setImageResource(R.mipmap.lefthand_foreground);
                limb = "left hand";
                break;
            case 3:
                limbText.setText("RIGHT FOOT");
                limbImage.setImageResource(R.mipmap.rightfoot_foreground);
                limb = "right foot";
                break;
            case 4:
                limbText.setText("RIGHT HAND");
                limbImage.setImageResource(R.mipmap.righthand_foreground);
                limb = "right hand";
                break;
        }
    }


    public void chooseColor() {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        switch (randomNum) {
            case 1:
                setColor(Color.rgb(255, 102, 102)); //tomato red
                color = "red";
                break;
            case 2:
                setColor(Color.rgb(175, 225, 175)); //pale green
                color = "green";
                break;
            case 3:
                setColor(Color.rgb(193, 242, 254)); //pale blue
                color = "blue";
                break;
            case 4:
                setColor(Color.rgb(255, 255, 153)); //pale yellow
                color = "yellow";
                break;
        }
    }

    public void setColor(int color) {
        View background = this.getWindow().getDecorView();
        background.setBackgroundColor(color);
    }

}


class Speech implements TextToSpeech.OnInitListener {
    TextToSpeech tts;
    String text;

    public Speech(String text, Context context) {
        tts = new TextToSpeech(context, this);
        this.text = text;
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.ENGLISH);
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            while (tts.isSpeaking()) ;
        }
    }
}