package com.example.trab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity {

    Button btnAdd;
    Button btnViewAll;
    DAO dao = new DAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        btnAdd = findViewById(R.id.btnAdd);
        btnViewAll = findViewById(R.id.btnViewAll);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainScreen.this, MainActivity.class));
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainScreen.this, SecondActivity.class));
            }
        });
    }

}