package com.example.roomdbexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;

import java.util.List;

import adapter.RegAdapter;
import db.DaoFile;
import db.DatabaseFile;
import db.EntityFile;

public class Details extends AppCompatActivity {

    RecyclerView recyclerView;

  //  EditText txtname,txtaddress,txtphone,txtemail,txtusername,txtpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        recyclerView = findViewById(R.id.list_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListOfDetails listOfDetails = new ListOfDetails();
        listOfDetails.execute();
    }

//    RecyclerView recyclerView = findViewById(R.id.recycler_view);
//    recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    MyAdapter adapter = new MyAdapter(myDataList);
//    recyclerView.setAdapter(adapter);

    class ListOfDetails extends AsyncTask<Void,Void, List<EntityFile>>{

        @Override
        protected List<EntityFile> doInBackground(Void... voids) {
            List<EntityFile> list = DatabaseFile.getDatabase(Details.this).dao().getList();
            return list;
        }

        @Override
        protected void onPostExecute(List<EntityFile> entityFiles) {
            super.onPostExecute(entityFiles);
            RegAdapter regAdapter = new RegAdapter(Details.this,entityFiles);
            recyclerView.setAdapter(regAdapter);
        }
    }

}