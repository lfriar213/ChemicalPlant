package com.example.chemicalplant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText name_input, address_input, year_input, chemical_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name_input = findViewById(R.id.name_input);
        address_input = findViewById(R.id.address_input);
        year_input = findViewById(R.id.year_input);
        chemical_input = findViewById(R.id.chemical_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(AddActivity.this);
                myDB.addCompany(name_input.getText().toString().trim(),
                        address_input.getText().toString().trim(),
                        Integer.valueOf(year_input.getText().toString().trim()),
                        chemical_input.getText().toString());



            }
        });
    }
}