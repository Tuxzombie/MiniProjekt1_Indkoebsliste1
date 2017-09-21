package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Liste Over Butikker");

        ListView listView = (ListView) findViewById(R.id.listButikker);
        final ArrayList<Butik> butikker = Storage.createButikker();
        ArrayAdapter<Butik> arrayAdapter = new ArrayAdapter<Butik>(this, android.R.layout.simple_expandable_list_item_1, butikker);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {

                startActivity(new Intent(getApplicationContext(),
                        VarerActivity.class)
                        .putExtra("Butik", Storage.getButik(position)));
      //        Toast.makeText(parent.getContext(), Storage.getButik(position).toString(), Toast.LENGTH_SHORT).show();

            }
        });

        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.item_delete:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_edit:
                Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
