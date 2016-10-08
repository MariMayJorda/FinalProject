package com.example.marimayjorda.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by MariMayJorda on 10/5/2016.
 */
public class MainActivity extends Activity {
    float x1, y1 , x2, y2, a, b;
    String msg1 = "", msg2="";
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText xy1 = (EditText) findViewById(R.id.editText);
        final EditText xy2 = (EditText) findViewById(R.id.editText2);
        final EditText diff = (EditText) findViewById(R.id.editText3);
        final EditText quadrant = (EditText) findViewById(R.id.editText4);
        final EditText motion= (EditText) findViewById(R.id.editText5);
        image = (ImageView) findViewById(R.id.imageView);


        image.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();

                        xy1.setText(x1 + ","+y1);

                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2= event.getY();

                        xy2.setText(x2 +","+y2);

                        a=x1-x2;

                        b=y1-y2;

                        diff.setText(Math.abs(a) +","+Math.abs(b));

                        if (a<0 & b>0){

                            msg2="1ST QUADRANT";
                        }
                        if (a>0 & b>0 ){

                            msg2="2ND QUADRANT";

                        }
                        if (a>0 & b<0){

                            msg2="3RD QUADRANT";
                        }
                        if (a<0 & b<0){

                            msg2="4TH QUADRANT";
                        }


                        if (y1 < y2 ) {
                            msg1 +=" SWIPED DOWN";
                        }
                        if(y1 > y2 ){

                            msg1 +=" SWIPED UP";
                        }
                        if (x1 > x2 ){

                            msg1 +=" SWIPED LEFT";
                        }
                        if (x1 < x2 ) {

                            msg1 +=" SWIPED RIGHT";
                        }

                        quadrant.setText(msg1);
                        msg1="";

                        motion.setText(msg2);
                        msg2="";
                }
                return true;
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}

