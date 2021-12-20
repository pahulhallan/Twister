package com.example.twister;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random rand = new Random();
    int max = 4;
    int min = 1;
    boolean run = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //final EditText delay = (EditText) findViewById(R.id.delay);
        //final ImageView limbPic = (ImageView) findViewById(R.id.limb);
       // int delayInt = Integer.parseInt(delay.getText().toString());
    }

    public void onStart(View v) {
        run = true;
        twisterLoop();
    }
    public void onStop(View v) {
        run = false;
        //setColor(Color.BLUE);
    }
    private void twisterLoop() {
       // while(run){

           chooseColor();
           chooseLimb();
           // Thread.sleep(delayInt);
        }


    private void chooseLimb() {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        TextView limb = findViewById(R.id.limb);
        ImageView limbImage = findViewById(R.id.imageLimb);
        switch (randomNum) {
            case 1:
                limb.setText("LEFT FOOT");
                limbImage.setImageResource(R.mipmap.leftfoot_foreground);
                break;
            case 2:
                limb.setText("LEFT HAND");
                limbImage.setImageResource(R.mipmap.lefthand_foreground);
                break;
            case 3:
                limb.setText("RIGHT FOOT");
                limbImage.setImageResource(R.mipmap.rightfoot_foreground);
                break;
            case 4:
                limb.setText("RIGHT HAND");
                limbImage.setImageResource(R.mipmap.righthand_foreground);
                break;}
    }



    public void chooseColor() {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        switch (randomNum) {
            case 1:
                setColor(Color.rgb(255,102,102));
                break;
            case 2:
                setColor(Color.rgb(175, 225, 175));
                break;
            case 3:
                setColor(Color.rgb(193,242,254));
                break;
            case 4:
                setColor(Color.rgb(255,255,153));
                break;
        }
    }
    public void setColor(int color){
        View background = this.getWindow().getDecorView();
        background.setBackgroundColor(color);
    }

}