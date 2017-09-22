package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class InkoebslisteActivity extends AppCompatActivity {

    private Indkoebsliste dummyIndkoebsliste = new Indkoebsliste("Rema 1000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dummyIndkoebsliste.addVareToVareliste("Cola");
        dummyIndkoebsliste.addVareToVareliste("Skinke");
        dummyIndkoebsliste.addVareToVareliste("Makrel");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarIndkoebsliste);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        ListView lvIndkoebsListe = (ListView) findViewById(R.id.lvIndkoebsliste);
        ArrayAdapter<String> listAdapter = new CustomListAdapter(this, dummyIndkoebsliste.getVareliste());
        lvIndkoebsListe.setAdapter(listAdapter);
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_indkoebsliste, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_tilButik:
                return true;

            case R.id.action_mere:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class CustomListAdapter extends ArrayAdapter<String> {
        private ArrayList<String> varer; // TODO type skal ændres til vare
        private final Activity context;

        public CustomListAdapter(Activity context, ArrayList<String> varer) { // TODO type skal ændres til vare
            super(context, R.layout.item_indkoebsliste, varer);
            this.context=context;
            this.varer = varer;
        }

        @Override
        public View getView(int position, final View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.item_indkoebsliste, parent, false);

            TextView tvNavn = (TextView) rowView.findViewById(R.id.tvVare);
            TextView tvPris = (TextView) rowView.findViewById(R.id.tvPris);
            CheckBox cbErKoebt = (CheckBox) rowView.findViewById(R.id.cbErKoebt);

            tvNavn.setText(varer.get(position));
            tvPris.setText(10 + " ,-"); // TODO pris skal sættes
            cbErKoebt.setSelected(false); // TODO

            rowView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final PopupMenu popup = new PopupMenu(rowView.getContext(), v);
                    popup.getMenuInflater().inflate(R.menu.popup_indkoebsliste, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            return true;
                        }
                    });
                    popup.show();
                    return true;
                }
            });

            return rowView;
        };
    }
}

