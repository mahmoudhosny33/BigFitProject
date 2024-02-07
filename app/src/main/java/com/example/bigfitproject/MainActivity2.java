package com.example.bigfitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final UsersDBHelper userDB = new UsersDBHelper(getApplicationContext());

        final EditText firstname = (EditText) findViewById(R.id.firstName);
        final EditText lastname = (EditText) findViewById(R.id.lastName);
        final EditText username = (EditText) findViewById(R.id.usernameSignUp);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.passwordsignup);
        final EditText confirmPassword = (EditText) findViewById(R.id.passwordsignup2);
        Button join = (Button) findViewById(R.id.button4);
        final Spinner gender = (Spinner) findViewById(R.id.spinner);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userFirstName, userLastName, userUsername, userEmial, userPassword, userGender;
                userFirstName = firstname.getText().toString();
                userLastName =  lastname.getText().toString();
                userUsername = username.getText().toString();
                userEmial = email.getText().toString();
                userPassword = password.getText().toString();
                userGender = gender.getSelectedItem().toString();

                if(userFirstName.equals("") || userLastName.equals("") || userUsername.equals("") || userEmial.equals("") || userPassword.equals("") || confirmPassword.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Fill All Fields", Toast.LENGTH_LONG).show();
                }

                else if (!isEmailValid(userEmial)) {
                    Toast.makeText(getApplicationContext(), "Wrong E-Mail Format", Toast.LENGTH_LONG).show();
                }

                else if(password.getText().toString().length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password Is Too Short", Toast.LENGTH_LONG).show();
                }

                else if(!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Password Doesn't Match", Toast.LENGTH_LONG).show();
                }

                else if(userDB.userExists(userUsername))
                {
                    Toast.makeText(getApplicationContext(), "Usrname isn't available", Toast.LENGTH_LONG).show();
                }

                else {
                    userDB.addUser(userFirstName, userLastName, userUsername, userEmial, userPassword, userGender);
                    //Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
                    firstname.getText().clear();
                    lastname.getText().clear();
                    username.getText().clear();
                    email.getText().clear();
                    password.getText().clear();
                    confirmPassword.getText().clear();
                    Intent i = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });

    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}