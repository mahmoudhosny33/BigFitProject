package com.example.bigfitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        ListView myList = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> moviesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        myList.setAdapter(moviesAdapter);
        UsersDBHelper db = new UsersDBHelper(getApplicationContext());

        Button btn = (Button) findViewById(R.id.button3);

        Cursor cursor = db.fetchAllUsers();
        while(!cursor.isAfterLast())
        {
            //moviesAdapter.add(cursos.getString(0));
            moviesAdapter.add(cursor.getString(cursor.getColumnIndex("username")));
            cursor.moveToNext();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, MainActivity2.class);
                startActivity(i);
            }
        });
    }
}