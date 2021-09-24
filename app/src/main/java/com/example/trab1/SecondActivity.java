package com.example.trab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    Button btnGoBack;
    List<FormModel> entries;
    DAO dao = new DAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnGoBack = findViewById(R.id.btnGoBack);
        RecyclerView formList = (RecyclerView) findViewById(R.id.formList);

        entries = dao.getAll();
        FormEntryAdapter adapter = new FormEntryAdapter(entries, this);
        formList.setAdapter(adapter);
        formList.setLayoutManager(new LinearLayoutManager(this));

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(SecondActivity.this, MainScreen.class));
            }
        });
    }

    public void resetIntent(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}