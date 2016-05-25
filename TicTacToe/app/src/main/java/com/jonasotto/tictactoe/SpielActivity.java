package com.jonasotto.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SpielActivity extends AppCompatActivity {

    TextView textViewSpieler;

    int aktiverSpieler = 0; //Aktueller Spieler, X=0, O=1
    int spielfeld[][] = new int[3][3];
    int anzahlSpielzuege = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        initSpielfeld();
        textViewSpieler = (TextView) findViewById(R.id.textViewSpieler);
    }

    public void game_click(View view) {     //Wird aufgerufen, sobald ein Feld angeklickt wird
        Button button = (Button) view;  //"button" ist der aktuell gedrückte Button
        if (button.getText().equals("")) {

            spielfeld[getXCoordinate(button)][getYCoordinate(button)] = aktiverSpieler;

            switch (aktiverSpieler) {
                case 0: //Spieler X/1 am Zug
                    button.setText("X");    //Feld auf X setzen
                    button.setTextColor(ContextCompat.getColor(this, R.color.spieler0));    //Farbe für Spieler 0 aus den Ressourcen laden, auf button anwenden
                    break;
                case 1: //Spieler O/2 am Zug
                    button.setText("O");    //Feld auf O setzen
                    button.setTextColor(ContextCompat.getColor(this, R.color.spieler1));    //Farbe für Spieler 1 aus den Ressourcen laden, auf button anwenden
                    break;
            }

            anzahlSpielzuege++;

            if (spielGewonnen(spielfeld, getXCoordinate(button), getYCoordinate(button))) {
                Intent spielEnde = new Intent(SpielActivity.this, SpielEndeActivity.class); //Neuer Intent um SpielEndeActivity zu starten
                Bundle optionen = new Bundle(); //Neue Optionen
                optionen.putInt("gewinner", aktiverSpieler); //Wert "gewinner" in Optionen auf aktuellen Spieler festlegen
                spielEnde.putExtras(optionen);  //Optionen an Intent anhängen
                startActivity(spielEnde);   //Intent ausführen, neue Activity SpielEndeActivity starten
                finish();   //Aktuelle Activity beenden
            }

            if (anzahlSpielzuege >= 9) {
                Intent spielEnde = new Intent(SpielActivity.this, SpielEndeActivity.class); //Neuer Intent um SpielEndeActivity zu starten
                Bundle optionen = new Bundle(); //Neue Optionen
                optionen.putInt("gewinner", -1); //Wert "gewinner" in Optionen auf -1 (kein Gewinner, Gleichstand)festlegen
                spielEnde.putExtras(optionen);  //Optionen an Intent anhängen
                startActivity(spielEnde);   //Intent ausführen, neue Activity SpielEndeActivity starten
                finish();   //Aktuelle Activity beenden
            }

            aktiverSpieler = (aktiverSpieler + 1) % 2; //Nächster Spieler
            textViewSpieler.setText("Spieler " + aktiverSpieler); //Anzeige für aktuellen Spieler aktualisieren
            if (aktiverSpieler == 0)
                textViewSpieler.setTextColor(ContextCompat.getColor(this, R.color.spieler0));   //Farbe der Anzeige festlegen
            else
                textViewSpieler.setTextColor(ContextCompat.getColor(this, R.color.spieler1));
        }
    }


    private int getXCoordinate(Button button) {
        return Integer.parseInt(button.getText().toString().substring(7, 8));

    }

    private int getYCoordinate(Button button) {
        return Integer.parseInt(button.getText().toString().substring(9, 10));
    }


    boolean spielGewonnen(int[][] spielfeld, int x, int y) {
        for (int i = 0; i < spielfeld.length; i++) {
            if (spielfeld[i][y] != aktiverSpieler)
                break;
            if (i == spielfeld.length - 1)
                return true;
        }
        for (int i = 0; i < spielfeld[0].length; i++) {
            if (spielfeld[x][i] != aktiverSpieler)
                break;
            if (i == spielfeld.length - 1)
                return true;
        }
        if (x == y) {
            for (int i = 0; i < spielfeld.length; i++) {
                if (spielfeld[i][i] != aktiverSpieler)
                    break;
                if (i == spielfeld.length - 1)
                    return true;
            }
        }
        for (int i = 0; i < spielfeld.length; i++) {
            if (spielfeld[i][spielfeld.length - 1 - i] != aktiverSpieler)
                break;
            if (i == spielfeld.length - 1)
                return true;
        }
        return false;
    }

    private void initSpielfeld() {
        for (int i = 0; i < spielfeld.length; i++) {
            for (int b = 0; b < spielfeld[i].length; b++) {
                spielfeld[i][b] = -1;
            }
        }
    }
}
