package com.example.android_lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Animal> target;

    private SimpleCursorAdapter adapter;

    private MySQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        target = new ArrayList<>();
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

        listView.setOnItemClickListener((AdapterView.OnItemClickListener) (parent, view, position, id) -> {
            TextView name = (TextView)
                    view.findViewById(android.R.id.text1);
            Animal zwierz = db.pobierz(Integer.parseInt
                    (name.getText().toString()));
            Intent intencja = new
                    Intent(getApplicationContext(),
                    DodajWpis.class);
            intencja.putExtra("element", zwierz);
            startActivityForResult(intencja, 2);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            db.usun(String.valueOf(id));
            adapter.changeCursor(db.lista());
            adapter.notifyDataSetChanged();
            return true;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void nowyWpis(MenuItem mi) {
        Intent intencja = new Intent(this,
                DodajWpis.class);
        startActivityForResult(intencja, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Animal nowy = (Animal) extras.getSerializable("nowy");
            target.add(nowy);
            this.db.dodaj(nowy);
            adapter.changeCursor(db.lista());
            adapter.notifyDataSetChanged();
        }

    }
}