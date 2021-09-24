package com.example.trab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nameId, numberId;
    Button formAdd;
    Switch switchEmployed;
    DAO dao = new DAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameId = findViewById(R.id.nameId);
        numberId = findViewById(R.id.numberId);
        formAdd = findViewById(R.id.formAdd);
        switchEmployed = findViewById(R.id.switchEmployed);

        formAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FormModel model = new FormModel();
                    model.setName(nameId.getText().toString());
                    model.setNumber(numberId.getText().toString());
                    model.setEmployed(switchEmployed.isChecked());
                    dao.addOne(model);
                    Toast.makeText(MainActivity.this, "Inserted with success.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Error inserting form entry.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}