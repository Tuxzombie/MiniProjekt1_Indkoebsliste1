package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VarerActivity extends AppCompatActivity {
    public static final String EXTRA_BUTIK = "butikId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_vare_activity);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Params: Hvilken activity, hvilket layout, arrayet den skal tilføje fra (her er det klassen Drink.drinks
        ArrayAdapter<Vare> listAdapter = new ArrayAdapter<Vare>(
                this, R.layout.list_varer_layout,
                Vare.varer);

        // Gør så vores listview benytter adapteren.
        ListView listDrinks = (ListView) findViewById(R.id.list_varer);
        listDrinks.setAdapter(listAdapter);

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
}
