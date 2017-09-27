package com.example.tuxzo.miniprojekt1_indkoebsliste;


import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by PWM on 26-09-17.
 */

public class VareCursorAdapter extends SimpleCursorAdapter {
    public VareCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public void bindView(final View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);

        //================================================================================
        // MINUS_BTN EVENTHANDLER:
        //================================================================================
        Button minusBtn = (Button) view.findViewById(R.id.btn_minus);
        minusBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) { // Knappen vi får ind
                TextView txtMaengde = (TextView) view.findViewById(R.id.textMaengde);
                int currentValue = Integer.parseInt((String) txtMaengde.getText());
                if(currentValue > 0) {
                    currentValue--;
                    txtMaengde.setText(currentValue + "");
                }
            }
        });

        //================================================================================
        // PLUS_BTN EVENTHANDLER:
        //================================================================================
        Button plusBtn = (Button) view.findViewById(R.id.btn_plus);
        plusBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtMaengde = (TextView) view.findViewById(R.id.textMaengde);
                int currentValue = Integer.parseInt((String) txtMaengde.getText());
                currentValue++;
                txtMaengde.setText(currentValue + "");
            }
        });

        //================================================================================
        // TILFOEJ_BTN EVENTHANDLER:
        //================================================================================
        Button tilfoejBtn = (Button) view.findViewById(R.id.btnTilfoej);
        tilfoejBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO - Funktionaliteten skal lige fastsættes.
            }
        });

        //================================================================================
        // LONGCLICK EVENTHANDLER:
        //================================================================================


    }
}
