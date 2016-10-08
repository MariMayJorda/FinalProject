package com.example.marimayjorda.finalproject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by MariMayJorda on 10/5/2016.
 */
public class MainActivity extends Activity {
    ImageView imageLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText t1 = (EditText) findViewById(R.id.editText);
        final EditText t2 = (EditText) findViewById(R.id.editText2);
        final EditText t3 = (EditText) findViewById(R.id.editText3);
        final EditText t4 = (EditText) findViewById(R.id.editText4);
        final EditText t5 = (EditText) findViewById(R.id.editText5);
        t1.setKeyListener(null);
        t2.setKeyListener(null);
        t3.setKeyListener(null);
        t4.setKeyListener(null);
        t5.setKeyListener(null);

        imageLogo = (ImageView) findViewById(R.id.imageView);
        imageLogo.setOnTouchListener(new View.OnTouchListener() {

            float x,y,x1,y1;

            @Override
            public boolean onTouch(View view, MotionEvent e) {

                String motionX = "";
                String motionY = "";
                String quadrant = "";

                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = e.getX();
                        y = e.getY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        float X = imageLogo.getRight()/2;
                        float Y = imageLogo.getBottom()/2;

                        x1=e.getX();
                        y1=e.getY();

                        motionX = "";
                        motionY = "";
                        quadrant = "";

                        if (x<x1){
                            motionX = "Swiped right ";
                        }
                        if (x>x1){
                            motionX = "Swiped left. ";
                        }
                        if (y<y1){
                            motionY = "Swiped down. ";
                        }
                        if (y>y1)
                        {
                            motionY = "Swiped up. ";
                        }

                        if(x1>X && y1>Y){
                            quadrant = "Quadrant 4";
                        }
                        if(x1<X && y1>Y){
                            quadrant = "Quadrant 3";
                        }
                        if(x1<X && y1<Y){
                            quadrant = "Quadrant 2";
                        }
                        if(x1>X && y1<Y){
                            quadrant = "Quadrant 1";
                        }

                        t1.setText(x + ", " + y);
                        t2.setText(x1 + ", " + y1);
                        t3.setText(  (Math.abs(x1-x))+", "+ (Math.abs(y1-y)) );

                        t5.setText(motionX + motionY);
                        t4.setText(quadrant);

                }
                return  false;
            }

        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("GoSpyRecipe");
        alertDialogBuilder
                .setMessage("Do you want to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }





}

