package com.example.marimayjorda.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText username, password;
    TextView signup;
    Button show;
    DatabaseAdapter LoginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.btn_login);
        username = (EditText) findViewById(R.id.emailadd);
        password = (EditText) findViewById(R.id.pw);
        signup = (TextView) findViewById(R.id.signup);
        show = (Button) findViewById(R.id.show);

        LoginDataBaseAdapter = new DatabaseAdapter(this);
        LoginDataBaseAdapter = LoginDataBaseAdapter.open();


        assert login != null;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = username.getText().toString();
                String pword = password.getText().toString();
                String uname = username.getText().toString();
                String sPword = LoginDataBaseAdapter.getSingleEntry(email);
                String sPword1 = LoginDataBaseAdapter.getUsername(uname);


                if (pword.equals(sPword1) | pword.equals(sPword)) {
                    Toast.makeText(LoginActivity.this, "Logged in.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(LoginActivity.this, "Invalid Email or Password ", Toast.LENGTH_LONG).show();
                }

            }

        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }

        });

        show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int event = motionEvent.getAction();
                final int cursor = password.getSelectionStart();
                switch (event) {
                    case MotionEvent.ACTION_DOWN:
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        password.setSelection(cursor);
                        return true;
                    case MotionEvent.ACTION_UP:
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        password.setSelection(cursor);
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                }
                return false;
            }

        });

    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }

}