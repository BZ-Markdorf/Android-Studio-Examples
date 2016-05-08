package com.jonasotto.tictactoe;

import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SpielEndeActivity extends AppCompatActivity {

    int gewinner = -1;
    TextView textViewGewinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel_ende);

        Bundle optionen = getIntent().getExtras();  //Optionen laden
        if(optionen != null)    //Wenn optionen vorhanden sind...
            gewinner = optionen.getInt("gewinner"); //Setze Gewinner aus Optionen
        textViewGewinner = (TextView) findViewById(R.id.textViewGewinner);
        switch (gewinner){  //Text und Farbe je nach Gewinner anpassen
            case -1:
                textViewGewinner.setText("Unentschieden");
                break;
            case 0:
                textViewGewinner.setText("X hat gewonnen!");
                textViewGewinner.setTextColor(ContextCompat.getColor(this, R.color.spieler1));
                break;
            case 1:
                textViewGewinner.setText("O hat gewonnen!");
                textViewGewinner.setTextColor(ContextCompat.getColor(this, R.color.spieler2));
                break;
        }
    }
}
