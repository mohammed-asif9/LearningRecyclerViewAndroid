package com.example.againrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.againrecyclerview.adapter.RecyclerViewAdapter;
import com.example.againrecyclerview.data.MyDbHelper;
import com.example.againrecyclerview.data.Task;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;



    RecyclerViewAdapter adapter;
    List<List<String>> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dataList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);





        dataList.add(addTask("newTask","2"));
        dataList.add(addTask("oldTask","3"));

        MyDbHelper dbHelper = new MyDbHelper(this);
        dbHelper.addTask(new Task("first task","3"));


        Task task = new Task(3,"Boring Task","1");
        dbHelper.updateTask(task);
        dbHelper.deleteTask();
        List<List<String>> fetchRows = dbHelper.fetchAllRows();
        dataList.addAll(fetchRows);
        adapter = new RecyclerViewAdapter(this,dataList);

        //setting recycler view properties
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Networking network = new Networking();
        network.execute("https://www.google.com");
        SharedPreferences shrdpref = PreferenceManager.getDefaultSharedPreferences(this);
        shrdpref.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                if(s.equals("asifCheck"))
                    Log.d("dbAsif","The value got from sharedPreference is "+shrdpref.getBoolean("asifCheck",true));
            }
        });




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener();
    }

    List<String> addTask(String task, String priority){

        List<String> list = new ArrayList<>();

        list.add(task);
        list.add(priority);

        return list;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.settings_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        if(menuId == R.id.settings_menu_item){
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}