package com.example.roomdbexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Dao;

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

public class RegistrationPage extends AppCompatActivity {
    EditText txtname,txtaddress,txtemail,txtphonenumber,txtusername,txtpassword;
    Button registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        txtname = findViewById(R.id.name);
        txtaddress = findViewById(R.id.address);
        txtemail = findViewById(R.id.email);
        txtphonenumber = findViewById(R.id.phone);
        txtusername = findViewById(R.id.username);
        txtpassword = findViewById(R.id.password);
        registerbtn = findViewById(R.id.registerbtn);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveDetails saveDetails = new SaveDetails();
                saveDetails.execute();
//                Intent intent = new Intent(RegistrationPage.this,Details.class);
//                startActivity(intent);
            }
        });
    }

    class SaveDetails extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            EntityFile entity = new EntityFile();
            entity.setName(txtname.getText().toString());
            entity.setAddress(txtaddress.getText().toString());
            entity.setEmail(txtemail.getText().toString());
            entity.setPhonenumber(txtphonenumber.getText().toString());
            entity.setUsername(txtusername.getText().toString());
            entity.setPassword(txtpassword.getText().toString());
            DaoFile dao = DatabaseFile.getDatabase(RegistrationPage.this).dao();
            dao.insertRecord(entity);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            finish();
            Intent intent = new Intent(RegistrationPage.this,LoginPage.class);
            startActivity(intent);
            Toast.makeText(RegistrationPage.this, "Registered", Toast.LENGTH_SHORT).show();
        }
    }
}