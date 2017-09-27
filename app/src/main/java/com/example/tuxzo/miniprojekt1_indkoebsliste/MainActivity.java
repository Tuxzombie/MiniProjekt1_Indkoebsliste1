package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    public static Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = new Storage(getApplicationContext());

        Storage.createButikker();
        Storage.createVarer();
        Storage.insertVarerIIndkoebsliste();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarIndkoebsliste);
        setSupportActionBar(toolbar);

        Cursor vCursor = storage.getVarerIListe();

        ArrayList<Vare> vArrayList = new ArrayList<Vare>();
        for(vCursor.moveToFirst(); !vCursor.isAfterLast(); vCursor.moveToNext()) {
            Vare tempVare = storage.getVare(vCursor.getColumnIndex("VARE_ID"));
            vArrayList.add(tempVare);
        }

        ListView lvIndkoebsListe = (ListView) findViewById(R.id.lvIndkoebsliste);
        ArrayAdapter<Vare> listAdapter = new CustomListAdapter(this, vArrayList);
        lvIndkoebsListe.setAdapter(listAdapter);

        // Udregning af totalpris
        double totalPris = 0;
        for (int i = 0; i < vArrayList.size(); i ++) {
            totalPris += vArrayList.get(i).getNormalPris();
        }

        TextView tvTotalPris = (TextView) findViewById(R.id.tvTotalPris);
        tvTotalPris.setText(totalPris + " kr.");

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
                Intent intent = new Intent(this, ButikActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    public class CustomListAdapter extends ArrayAdapter<Vare> {
        private ArrayList<Vare> varer;
        private final Activity context;

        public CustomListAdapter(Activity context, ArrayList<Vare> varer) {
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

            tvNavn.setText(varer.get(position).getName());
            tvPris.setText(varer.get(position).getNormalPris() + "");
            cbErKoebt.setSelected(false);

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
