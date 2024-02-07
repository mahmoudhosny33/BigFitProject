package com.example.bigfitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UsersDBHelper userDB = new UsersDBHelper(getApplicationContext());

        Button login = (Button) findViewById(R.id.button);
        Button signup = (Button) findViewById(R.id.button2);

        final EditText usernametxt = (EditText) findViewById(R.id.name);
        final EditText passwordtxt = (EditText) findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usern, pass;
                usern = usernametxt.getText().toString();
                pass = passwordtxt.getText().toString();

                if(usern.equals("") || pass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Fill All Fields", Toast.LENGTH_LONG).show();
                }

                else if(!userDB.userExists(usern)) {
                    Toast.makeText(getApplicationContext(), "Username doesn't Exist", Toast.LENGTH_LONG).show();
                }
                else if(!userDB.correctPassword(usern, pass)) {
                    Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_LONG).show();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
                /*Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);*/
            }
        });
    }

}