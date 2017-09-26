package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import static com.example.tuxzo.miniprojekt1_indkoebsliste.MainActivity.storage;

public class ButikActivity extends AppCompatActivity {

    private SimpleCursorAdapter listAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butik);

        getSupportActionBar().setTitle("Liste Over Butikker");

        listView = (ListView) findViewById(R.id.listButikker);

        listAdapter = new SimpleCursorAdapter(this,
                R.layout.list_butikker_layout,
                storage.getButikker(),
                new String[]{"NAME", "ADRESSE", "HOMEPAGE"},
                new int[]{R.id.butikNameText, R.id.butikAdresseText, R.id.butikHomepageText },
                0);

        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),
                        VarerActivity.class)
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
                createEditAlertDialog((int)info.id, Storage.getButik((int)info.id)).show();
//                Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private AlertDialog createEditAlertDialog(int _id, final Butik butik)
    {
        final int butik_id = _id;
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View editView = inflater.inflate(R.layout.dialog_redigerbutik, null);
        alertBuilder.setView(editView);


        final EditText butikName = (EditText) editView.findViewById(R.id.butikName);
        final EditText butikAdresse = (EditText) editView.findViewById(R.id.butikAdresse);
        final EditText butikHomepage = (EditText) editView.findViewById(R.id.butikHomepage);

        butikName.setText(butik.getName());
        butikAdresse.setText(butik.getAdresse());
        butikHomepage.setText(butik.getHomepage());

        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                butik.setName(butikName.getText().toString());
                butik.setAdresse(butikAdresse.getText().toString());
                butik.setHomepage(butikHomepage.getText().toString());

                Storage.updateButik(butik_id, butik);
                listAdapter.changeCursor(Storage.getButikker());
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
