package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

public class InkoebslisteActivity extends AppCompatActivity {

    private Indkoebsliste dummyIndkoebsliste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inkoebsliste);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.item_indkoebsliste, );
    }
}
