package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.database.sqlite.SQLiteException;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
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
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);
        // actionBar.setTitle("Varer i " + Storage.getButik(butikId).getName());
        storage.createVarer();


        //================================================================================
        // VARELISTE POPULATE
        //================================================================================

        listView = (ListView) findViewById(R.id.list_varer);

        try {
            listAdapter = new SimpleCursorAdapter(this,
                    R.layout.list_varer_layout,
                    Storage.getVarer(butikId),
                    new String[] {"NAME", "NORMALPRIS"},
                    new int[] {R.id.vareNavnText, R.id.varePrisText}, 0);
            listView.setAdapter(listAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


        registerForContextMenu(listView);


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
//                Intent intent = new Intent(VarerActivity.this, MainActivity.class);
//                intent.putExtra(IndkoebsListe.EXTRA_DRINKID, (int) id);
//                startActivity(intent);
//            }
//        };

        // Assign listener to listview
//        listDrinks.setOnItemClickListener(itemClickListener);
    }

    //================================================================================
    // BUTTON EVENTHANDLERS:
    //================================================================================
    public void incrementEventhandler(View view) {
        TextView txtMaengde = (TextView) findViewById(R.id.textMaengde);
        int currentValue = Integer.parseInt((String) txtMaengde.getText());
        currentValue++;
        txtMaengde.setText(currentValue + "");
    }

    public void decrementEventhandler(View view) {
        TextView txtMaengde = (TextView) findViewById(R.id.textMaengde);
        int currentValue = Integer.parseInt((String) txtMaengde.getText());
        if(currentValue > 0) {
            currentValue--;
            txtMaengde.setText(currentValue + "");
        }
    }

    public void tilfoejEventhandler(View view) {

    }
}
