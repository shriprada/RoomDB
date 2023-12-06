package com.example.roomdbexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdbexample.RegistrationPage.SaveDetails;

import db.DaoFile;
import db.DatabaseFile;
import db.EntityFile;

public class LoginPage extends AppCompatActivity {
    private DatabaseFile appDatabase;
    EditText username,password ;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username = findViewById(R.id.lpusername);
        password = findViewById(R.id.lppassword);
        login = findViewById(R.id.btnlogin);
        appDatabase = Room.databaseBuilder(getApplicationContext(), DatabaseFile.class, "database-storage").build();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u1 = username.getText().toString();
                String p1= password.getText().toString();
                DaoFile userDao = appDatabase.dao();
                LoginAsyncTask loginAsyncTask=new LoginAsyncTask(appDatabase.dao());
                loginAsyncTask.execute(u1, p1);

                }
        });
    }

    private class LoginAsyncTask extends AsyncTask<String, Void, EntityFile> {
        private DaoFile userDao;
        LoginAsyncTask(DaoFile userDao) {
            this.userDao = userDao;
        }
        @Override
        protected EntityFile doInBackground(String... strings) {
            String user = strings[0];
            String pass = strings[1];
            return userDao.getUser(user,pass);
        }
        @Override
        protected void onPostExecute(EntityFile user) {
            super.onPostExecute(user);
            if (user != null) {
                Intent intent = new Intent(LoginPage.this, Details.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
            else {
                Toast.makeText(LoginPage.this, "Incorrect userId or password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}