package com.jonasotto.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
                textViewGewinner.setTextColor(ContextCompat.getColor(this, R.color.spieler0));
                break;
            case 1:
                textViewGewinner.setText("O hat gewonnen!");
                textViewGewinner.setTextColor(ContextCompat.getColor(this, R.color.spieler1));
                break;
        }
    }

    public void hauptmenue(View view) {
        Intent spielEnde = new Intent(SpielEndeActivity.this, MainActivity.class); //Neuer Intent um MainActivity zu starten
        startActivity(spielEnde);   //Intent ausführen, neue Activity MainActivity starten
        finish();   //Aktuelle Activity beenden
    }

    public void neuesSpiel(View view) {
        Intent spielEnde = new Intent(SpielEndeActivity.this, SpielActivity.class); //Neuer Intent um SpielActivity zu starten
        startActivity(spielEnde);   //Intent ausführen, neue SpielActivity starten
        finish();   //Aktuelle Activity beenden
    }
}
