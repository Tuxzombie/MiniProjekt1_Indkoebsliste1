package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static Storage storage;

    private class IndkoebslisteVare {
        private Vare vare;
        private int isChecked;
        private int id;

        private IndkoebslisteVare(Vare vare, int isChecked, int id) {
            this.vare = vare;
            this.isChecked = isChecked;
            this.id = id;
        }

        public Vare getVare() {
            return vare;
        }

        public int getId() { return id; }

        public boolean isChecked() {
            if(this.isChecked == 0) return false;
            else return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = Storage.getInstance(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarIndkoebsliste);
        setSupportActionBar(toolbar);

        Cursor vCursor = storage.getVarerIListe();

        ArrayList<IndkoebslisteVare> vArrayList = new ArrayList<IndkoebslisteVare>();
        for(vCursor.moveToFirst(); !vCursor.isAfterLast(); vCursor.moveToNext()) {
            Log.d("ischecked", vCursor.getColumnIndex("ISCHECKED") + "");
            IndkoebslisteVare tempVare = new IndkoebslisteVare(
                    storage.getVare(vCursor.getInt(vCursor.getColumnIndex("VARE_ID"))),
                    vCursor.getInt(vCursor.getColumnIndex("ISCHECKED")),
                    vCursor.getInt(vCursor.getColumnIndex("_id")));
            vArrayList.add(tempVare);
        }

        ListView lvIndkoebsListe = (ListView) findViewById(R.id.lvIndkoebsliste);
        ArrayAdapter<IndkoebslisteVare> listAdapter = new CustomListAdapter(this, vArrayList);
        lvIndkoebsListe.setAdapter(listAdapter);

        // Udregning af totalpris
        double totalPris = 0;
        for (int i = 0; i < vArrayList.size(); i ++) {
            totalPris += vArrayList.get(i).getVare().getNormalPris();
        }

        TextView tvTotalPris = (TextView) findViewById(R.id.tvTotalPris);
        tvTotalPris.setText(totalPris + " kr.");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        storage.destroy();
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

    public class CustomListAdapter extends ArrayAdapter<IndkoebslisteVare> {
        private ArrayList<IndkoebslisteVare> varer;
        private final Activity context;

        public CustomListAdapter(Activity context, ArrayList<IndkoebslisteVare> varer) {
            super(context, R.layout.item_indkoebsliste, varer);
            this.context=context;
            this.varer = varer;
        }

        @Override
        public View getView(final int position, final View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.item_indkoebsliste, parent, false);

            TextView tvNavn = (TextView) rowView.findViewById(R.id.tvVare);
            TextView tvPris = (TextView) rowView.findViewById(R.id.tvPris);
            final CheckBox cbErKoebt = (CheckBox) rowView.findViewById(R.id.cbErKoebt);

            tvNavn.setText(varer.get(position).getVare().getName());
            tvPris.setText(varer.get(position).getVare().getNormalPris() + "");
            cbErKoebt.setChecked(varer.get(position).isChecked());

            cbErKoebt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            storage.setIsCheckedOfIndkoebsliste(varer.get(position).getId(), b);
                        }
                    });

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
