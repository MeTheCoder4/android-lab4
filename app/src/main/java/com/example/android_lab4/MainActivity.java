package com.example.android_lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> target;

    private SimpleCursorAdapter adapter;

    private MySQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        target = new ArrayList<>();
        target.addAll(Arrays.asList("Pies", "kot", "koń", "gołąb"));
        db = new MySQLite(this);
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                db.lista(),
                new String[]{"_id", "gatunek"},
                new int[]{android.R.id.text1,
                        android.R.id.text2},
                        SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_menu, menu);
            return true;
        }

        public void nowyWpis (MenuItem mi){
            Intent intencja = new Intent(this,
                    DodajWpis.class);
            startActivityForResult(intencja, 1);
        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == 1 && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                String nowy = (String) extras.get("wpis");
                target.add(nowy);
                adapter.notifyDataSetChanged();
            }

        }
    }