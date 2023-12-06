package com.example.roomdbexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Dao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import db.DaoFile;
import db.DatabaseFile;
import db.EntityFile;

public class UpdateDetails extends AppCompatActivity {

    EditText name,username,password,email,address,phoneno;
    Button update,delete;
    EntityFile entity;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
        name = findViewById(R.id.upname);
        username = findViewById(R.id.upusername);
        password = findViewById(R.id.uppassword);
        email = findViewById(R.id.upemail);
        address = findViewById(R.id.upaddress);
        phoneno = findViewById(R.id.upphone);

        update = findViewById(R.id.upupdatebtn);
        delete = findViewById(R.id.updeletebtn);

        entity = (EntityFile) getIntent().getSerializableExtra("Details");

    }

    @Override
    protected void onResume() {
        super.onResume();
        name.setText(entity.getName());
        username.setText(entity.getUsername());
        password.setText(entity.getPassword());
        email.setText(entity.getEmail());
        address.setText(entity.getAddress());
        phoneno.setText(entity.getPhonenumber());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdationDetails updateDetails = new UpdationDetails();
                updateDetails.execute();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteRecord deleteRecord = new DeleteRecord();
                deleteRecord.execute();
            }
        });
    }
    class UpdationDetails extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            entity.setName(name.getText().toString());
            entity.setEmail(email.getText().toString());
            entity.setAddress(address.getText().toString());
            entity.setUsername(username.getText().toString());
            entity.setPassword(password.getText().toString());
            entity.setPhonenumber(phoneno.getText().toString());

            DaoFile daoFile = DatabaseFile.getDatabase(UpdateDetails.this).dao();
            daoFile.updateRecord(entity);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(UpdateDetails.this, "Updated", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(UpdateDetails.this,Details.class));
        }
    }

    class DeleteRecord extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            DaoFile daoFile = DatabaseFile.getDatabase(UpdateDetails.this).dao();
            daoFile.deleteRecord(entity);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(UpdateDetails.this, "Deleted", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(UpdateDetails.this,FirstPage.class));

        }
    }

}