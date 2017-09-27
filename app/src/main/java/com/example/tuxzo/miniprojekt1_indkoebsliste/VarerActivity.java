package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class VarerActivity extends AppCompatActivity {
    public static final String EXTRA_BUTIK_ID = "butikId";

    private SimpleCursorAdapter listAdapter;
    private ListView listView;
    public Storage storage = MainActivity.storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //================================================================================
        // INIT
        //================================================================================
        super.onCreate(savedInstanceState);

        int butikId = Integer.parseInt(getIntent().getStringExtra(EXTRA_BUTIK_ID));
        setContentView(R.layout.activity_varer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarVare);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //================================================================================
        // VARELISTE POPULATE
        //================================================================================

        listView = (ListView) findViewById(R.id.list_varer);
        registerForContextMenu(listView);

        try {
            listAdapter = new VareCursorAdapter(this,
                    R.layout.list_varer_layout,
                    storage.getVarer(butikId),
                    new String[]{"NAME", "NORMALPRIS"},
                    new int[]{R.id.vareNavnText, R.id.varePrisText}, 0);

            listView.setAdapter(listAdapter);

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        registerForContextMenu(listView);
    }
    //================================================================================
    // CONTEXT MENU
    //================================================================================

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_tilIndkoebsliste:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_vare, menu);
        return super.onCreateOptionsMenu(menu);
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
                storage.removeButik((int)info.id);
                listAdapter.changeCursor(storage.getButikker());
//                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_edit:
//                createEditAlertDialog((int)info.id, Storage.getButik((int)info.id)).show();
//                Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
//        listAdapter = new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_1,
//                Storage.getVarer(),
//                new String[]{"NAME"},
//                new int[]{android.R.id.text1},
//                0);
//
//        listView.setAdapter(listAdapter);

        //================================================================================
        //
        //================================================================================

//         Params: Hvilken activity, hvilket layout, arrayet den skal tilføje fra (her er det klassen Drink.drinks


        // Create Listener
        // OnItemClickListener er en public static interface som er i AdapterView
//        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> listDrinks, View itemView, int position, long id) {
//                // pass the drink the user clicks on to DrinkActivity
//                Intent intent = new Intent(VarerActivity.this, InkoebslisteActivity.class);
//                intent.putExtra(IndkoebsListe.EXTRA_DRINKID, (int) id);
//                startActivity(intent);
//            }
//        };

        // Assign listener to listview
//        listDrinks.setOnItemClickListener(itemClickListener);

}
