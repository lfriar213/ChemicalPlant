package com.example.chemicalplant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, address_input, year_input,chemical_input;
    Button update_Button, delete_Button;

    String id, name, address, year, chemical;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        address_input = findViewById(R.id.address_input2);
        year_input = findViewById(R.id.year_input2);
        chemical_input = findViewById(R.id.chemical_input2);
        update_Button = findViewById(R.id.update_Button);
        delete_Button = findViewById(R.id.delete_Button);
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }




        update_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                address = address_input.getText().toString().trim();
                year = year_input.getText().toString().trim();
                chemical = chemical_input.getText().toString().trim();
                myDB.updateData(id, name, address, year, chemical);

            }

        });



    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("address") && getIntent().hasExtra("year") &&
                getIntent().hasExtra("chemical")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            address = getIntent().getStringExtra("address");
            year = getIntent().getStringExtra("year");
            chemical = getIntent().getStringExtra("chemical");

            //Setting Intent Data
            name_input.setText(name);
            address_input.setText(address);
            year_input.setText(year);
            chemical_input.setText(chemical);


        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

}