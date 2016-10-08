package com.example.marimayjorda.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MariMayJorda on 10/5/2016.
 */
public class SignUpActivity extends Activity{
    EditText fname,lname,username,emailadd,pw,cp;
    Button create, s1,s2;
    DatabaseAdapter LoginDataBaseAdapter;
    private Toast popToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        username = (EditText) findViewById(R.id.uname);
        emailadd= (EditText) findViewById(R.id.email);
        pw = (EditText) findViewById(R.id.pword);
        cp = (EditText) findViewById(R.id.cpword);
        create = (Button) findViewById(R.id.btn_signup);
        s1 = (Button) findViewById(R.id.btn1);
        s2 = (Button) findViewById(R.id.btn2);


        LoginDataBaseAdapter = new DatabaseAdapter(this);
        LoginDataBaseAdapter = LoginDataBaseAdapter.open();


        s1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int event = motionEvent.getAction();
                final int cursor = pw.getSelectionStart();
                switch (event) {
                    case MotionEvent.ACTION_DOWN:
                        pw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pw.setSelection(cursor);
                        return true;
                    case MotionEvent.ACTION_UP:
                        pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pw.setSelection(cursor);
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                }
                return false;
            }

        });

        s2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int event = motionEvent.getAction();
                final int cursor = cp.getSelectionStart();
                switch (event) {
                    case MotionEvent.ACTION_DOWN:
                        cp.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        cp.setSelection(cursor);
                        return true;
                    case MotionEvent.ACTION_UP:
                        cp.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        cp.setSelection(cursor);
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                }
                return false;
            }

        });



        assert create!= null;
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailadd.getText().toString();
                String uname = username.getText().toString();
                String Lname= lname.getText().toString();
                String Fname= fname.getText().toString();
                String pass= pw.getText().toString();
                String cpass= cp.getText().toString();

                String savePw = LoginDataBaseAdapter.getEmail(email);
                String savePw1 = LoginDataBaseAdapter.getUsernameforsignup(uname);

                if(email.equals("")||uname.equals("")||Lname.equals("")||Fname.equals("")||pass.equals("")||cpass.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill out all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!ValidEmail(emailadd.getText().toString())) {
                    Toast.makeText(SignUpActivity.this,"Invalid Email",Toast.LENGTH_LONG).show();
                }
                else if(!ValidPassword(pw.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Password needs to be at least 8 characters", Toast.LENGTH_LONG).show();
                }
                else if (!pw.getText().toString().equals(cp.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Password does not match", Toast.LENGTH_LONG).show();
                }
                else if(!ValidFname(fname.getText().toString())) {
                    Toast.makeText(SignUpActivity.this,"Invalid First Name",Toast.LENGTH_LONG).show();
                }
                else if(!ValidLname(lname.getText().toString())) {
                    Toast.makeText(SignUpActivity.this,"Invalid Last Name",Toast.LENGTH_LONG).show();
                }
                else if(!ValidLname(lname.getText().toString())) {
                    Toast.makeText(SignUpActivity.this,"Invalid Last Name",Toast.LENGTH_LONG).show();
                }
                else if(uname.equals(savePw1)|email.equals(savePw)){
                    Toast.makeText(SignUpActivity.this,"Username or Email already exists",Toast.LENGTH_LONG).show();
                }
                else {
                    LoginDataBaseAdapter.insertEntry(fname.getText().toString(),lname.getText().toString(),username.getText().toString(),emailadd.getText().toString(),pw.getText().toString());
                    popToast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);
                    popToast.setText("Account Created ");
                    popToast.show();

                    Intent intent = new Intent(SignUpActivity.this,LoginActivity.class );
                    startActivity(intent);
                }
            }
        });
        
    }

    private boolean ValidEmail(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean ValidPassword(String pass) {
        if (pass != null && pass.length() >= 8) {
            return true;
        }
        return false;

    }
    private boolean ValidFname(String fname) {

        String FNAME_PATTERN = "^([A-Za-z] *)+$";
        Pattern pattern = Pattern.compile(FNAME_PATTERN);
        Matcher matcher = pattern.matcher(fname);
        return matcher.matches();
    }

    private boolean ValidLname(String lname) {
        String LNAME_PATTERN = "^([A-Za-z] *)+$";
        Pattern pattern = Pattern.compile(LNAME_PATTERN);
        Matcher matcher = pattern.matcher(lname);
        return matcher.matches();
    }



}
