package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SimpleCursorAdapter listAdapter;
    private ListView listView;
    public static Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = new Storage(getApplicationContext());
        storage.createButikker();

        getSupportActionBar().setTitle("Liste Over Butikker");

        listView = (ListView) findViewById(R.id.listButikker);

        listAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                storage.getButikker(),
                new String[]{"NAME", "ADRESSE", "HOMEPAGE"},
                new int[]{android.R.id.text1},
                0);

        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),
                        VarerActivity.class)
                        .putExtra(VarerActivity.EXTRA_BUTIK, storage.getButik((int)id))
                        .putExtra(VarerActivity.EXTRA_BUTIK_ID, id + ""));
//              Toast.makeText(parent.getContext(), Storage.getButik((int)id).toString(), Toast.LENGTH_SHORT).show();
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
                Storage.removeButik((int)info.id);
                listAdapter.changeCursor(Storage.getButikker());
//                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_edit:
                createEditAlertDialog(Storage.getButik((int)info.id)).show();
                listAdapter.changeCursor(Storage.getButikker());
//                Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private AlertDialog createEditAlertDialog(Butik butik)
    {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        alertBuilder.setView(inflater.inflate(R.layout.dialog_redigerbutik, null));


        EditText butikName = (EditText) findViewById(R.id.butikName);
        EditText butikAdresse = (EditText) findViewById(R.id.butikAdresse);
        EditText butikHomepage = (EditText) findViewById(R.id.butikHomepage);

//        Log.d("AlerDialogFields", butikName.toString());
//        butikName.setText(butik.getName());
//        butikAdresse.setText(butik.getAdresse());
//        butikHomepage.setText(butik.getHomepage());

        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        alertBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        alertBuilder.setMessage("Rediger Butik");
        AlertDialog dialog = alertBuilder.create();

        return dialog;
    }
}
