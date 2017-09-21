package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class InkoebslisteActivity extends AppCompatActivity {

    private Indkoebsliste dummyIndkoebsliste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inkoebsliste);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                this, R.layout.item_indkoebsliste, dummyIndkoebsliste);

        ListView lvIndkoebsListe = (ListView) findViewById(R.id.lvIndkoebsliste);
        lvIndkoebsListe.setAdapter(listAdapter);

    }

    private class IndkoebslisteAdapter extends ArrayAdapter<String> {
        private Context context;
        private String[] vareNavn;

        public IndkoebslisteAdapter(Activity context, String[] vareNavn) {
            super(context,  , vareNavn);
            // TODO Auto-generated constructor stub
            this.vareNavn = vareNavn;
            this.context=context;

        }

        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.mylist, null,true);

            TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

            txtTitle.setText(itemname[position]);
            imageView.setImageResource(imgid[position]);
            extratxt.setText("Description "+itemname[position]);
            return rowView;

        };
    }
}
