package com.example.twister;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
        final EditText delay = (EditText) findViewById(R.id.delay);
        //final ImageView limbPic = (ImageView) findViewById(R.id.limb);
        int delayInt = Integer.parseInt(delay.getText().toString());
        final Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run = true;

                try {
                    twisterLoop(delayInt);
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                }
            }
        });

        final Button stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run = false;
            }
        });



    }

    private void twisterLoop(int delayInt) throws InterruptedException {
        while(run){
            chooseColor();
            chooseLimb();
           // Thread.sleep(delayInt);
        }
    }

    private void chooseLimb() {
    }

    public void chooseColor() {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        switch (randomNum) {
            case 1:
                setColor(Color.RED);
                break;
            case 2:
                setColor(Color.BLUE);
                break;
            case 3:
                setColor(Color.GREEN);
                break;
            case 4:
                setColor(Color.YELLOW);
                break;
        }
    }
    public void setColor(int color){
        View background = this.getWindow().getDecorView();
        background.setBackgroundColor(color);
    }

}