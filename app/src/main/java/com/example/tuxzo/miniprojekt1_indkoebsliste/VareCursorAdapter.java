package com.example.tuxzo.miniprojekt1_indkoebsliste;


import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by PWM on 26-09-17.
 */

public class VareCursorAdapter extends SimpleCursorAdapter {
    public VareCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);

    }

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {
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
        final int denCursorViSkalBruge = cursor.getPosition();
        Button tilfoejBtn = (Button) view.findViewById(R.id.btnTilfoej);
        tilfoejBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                cursor.moveToPosition(denCursorViSkalBruge);
                int vareId = cursor.getInt(cursor.getColumnIndex("_id"));

                TextView tv = (TextView) view.findViewById(R.id.textMaengde);
                int antal = Integer.parseInt((String) tv.getText());

                if(antal > 0) {
                    Toast.makeText(context, "Tilføjet: " + antal + " stk " +
                            cursor.getString(cursor.getColumnIndex("NAME")) +
                            " til " + antal * cursor.getDouble(cursor.getColumnIndex("NORMALPRIS")) +
                            " kr", Toast.LENGTH_SHORT).show();

                    MainActivity.storage.addVareTilIndkoebsliste(vareId, antal, 0);
                }
                else          Toast.makeText(context, "Du kan ikke købe 0 af en vare" , Toast.LENGTH_SHORT).show();

            }
        });

        //================================================================================
        // LONGCLICK EVENTHANDLER:
        //================================================================================


    }
}
