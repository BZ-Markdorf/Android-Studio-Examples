package com.jonasotto.tictactoe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SpielActivity extends AppCompatActivity {

    TextView textViewSpieler;   //Anzeige des aktuellen Spielers

    int aktiverSpieler = 0; //Aktueller Spieler, X=0, O=1
    int spielfeld[][] = new int[3][3];  //Spielfeld
    int anzahlSpielzuege = 0;   //Zähler für Spielzüge

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        initSpielfeld(); //Da ein int in Java nicht den Wert null haben kann, werden zum Start alle Felder auf -1 (leer) gesetzt
        textViewSpieler = (TextView) findViewById(R.id.textViewSpieler);
    }

    @SuppressLint("SetTextI18n")
    public void game_click(View view) {     //Wird aufgerufen, sobald ein Feld angeklickt wird
        Button button = (Button) view;  //"button" ist der aktuell gedrückte Button
        if (button.getText().equals("")) {  //Nur ausführen wenn ein leerer Button angeklickt wurde

            spielfeld[getXCoordinate(button)][getYCoordinate(button)] = aktiverSpieler; //Angeklicktes Feld dem aktuellen Spieler zuweisen

            switch (aktiverSpieler) {
                case 0: //Spieler X/0 am Zug
                    button.setText("X");    //Feld auf X setzen
                    button.setTextColor(ContextCompat.getColor(this, R.color.spieler0));    //Farbe für Spieler 0 aus den Ressourcen laden, auf button anwenden
                    break;
                case 1: //Spieler O/1 am Zug
                    button.setText("O");    //Feld auf O setzen
                    button.setTextColor(ContextCompat.getColor(this, R.color.spieler1));    //Farbe für Spieler 1 aus den Ressourcen laden, auf button anwenden
                    break;
            }

            //Auf Spiel Ende testen

            if (spielGewonnen(spielfeld, getXCoordinate(button), getYCoordinate(button))) { //Testen ob das Spiel gewonnen ist
                Intent spielEnde = new Intent(SpielActivity.this, SpielEndeActivity.class); //Neuer Intent um SpielEndeActivity zu starten
                Bundle optionen = new Bundle(); //Neue Optionen
                optionen.putInt("gewinner", aktiverSpieler); //Wert "gewinner" in Optionen auf aktuellen Spieler festlegen
                spielEnde.putExtras(optionen);  //Optionen an Intent anhängen
                startActivity(spielEnde);   //Intent ausführen, neue Activity SpielEndeActivity starten
                finish();   //Aktuelle Activity beenden
                return;
            }

            if (anzahlSpielzuege >= 8) {    //Spiel wurde mit dem 9. Zug nicht beendet, also ist das Spiel unentschieden
                Intent spielEnde = new Intent(SpielActivity.this, SpielEndeActivity.class); //Neuer Intent um SpielEndeActivity zu starten
                Bundle optionen = new Bundle(); //Neue Optionen
                optionen.putInt("gewinner", -1); //Wert "gewinner" in Optionen auf -1 (kein Gewinner, Gleichstand)festlegen
                spielEnde.putExtras(optionen);  //Optionen an Intent anhängen
                startActivity(spielEnde);   //Intent ausführen, neue Activity SpielEndeActivity starten
                finish();   //Aktuelle Activity beenden
                return;
            }

            aktiverSpieler = (aktiverSpieler + 1) % 2; //Nächster Spieler
            textViewSpieler.setText("Spieler " + aktiverSpieler); //Anzeige für aktuellen Spieler aktualisieren
            if (aktiverSpieler == 0)
                textViewSpieler.setTextColor(ContextCompat.getColor(this, R.color.spieler0));   //Farbe der Anzeige festlegen
            else
                textViewSpieler.setTextColor(ContextCompat.getColor(this, R.color.spieler1));

            anzahlSpielzuege++; //Aktueller Spielzug fertig
        }
    }

    private int getXCoordinate(Button button) {
        String id = button.getResources().getResourceName(button.getId());  //Gibt die id des Buttons im Format "com.jonasotto.tictactoe:id/button_1_1" zurück
        id = id.substring(id.indexOf('/') + 1); //Entfernt den package Namen, id ist jetzt z.B. "button_1_1"
        return Integer.parseInt(id.substring(7, 8));
    }

    private int getYCoordinate(Button button) {
        String id = button.getResources().getResourceName(button.getId());
        id = id.substring(id.indexOf('/') + 1);
        return Integer.parseInt(id.substring(9, 10));
    }


    boolean spielGewonnen(int[][] spielfeld, int x, int y) {
        for (int i = 0; i < spielfeld.length; i++) {    //Alle Felder der aktuellen Zeile
            if (spielfeld[i][y] != aktiverSpieler)      //Abbrechen sobald ein Feld der Reihe nicht dem aktuellen Spieler gehört
                break;
            if (i == spielfeld.length - 1)              //Spiel ist gewonnen da alle Felder geprüft wurden und jedes dem aktuellen Spieler gehören
                return true;
        }
        for (int i = 0; i < spielfeld[0].length; i++) { //Alle Felder der aktuellen Spalte
            if (spielfeld[x][i] != aktiverSpieler)      //Abbrechen sobald ein Feld der Reihe nicht dem aktuellen Spieler gehört
                break;
            if (i == spielfeld.length - 1)              //Spiel ist gewonnen da alle Felder geprüft wurden und jedes dem aktuellen Spieler gehören
                return true;
        }
        if (x == y) {                                   //Auf Diagonalen
            for (int i = 0; i < spielfeld.length; i++) {//Alle Felder der Diagonalen
                if (spielfeld[i][i] != aktiverSpieler)  //Abbrechen sobald ein Feld der Reihe nicht dem aktuellen Spieler gehört
                    break;
                if (i == spielfeld.length - 1)          //Spiel ist gewonnen da alle Felder geprüft wurden und jedes dem aktuellen Spieler gehören
                    return true;
            }
        }
        for (int i = 0; i < spielfeld.length; i++) {    //Alle Felder der 2. Diagonalen
            if (spielfeld[i][spielfeld.length - 1 - i] != aktiverSpieler)   //Abbrechen sobald ein Feld der Reihe nicht dem aktuellen Spieler gehört
                break;
            if (i == spielfeld.length - 1)              //Spiel ist gewonnen da alle Felder geprüft wurden und jedes dem aktuellen Spieler gehören
                return true;
        }
        return false;                                   //Keiner der Tests war erfolgreich, das Spiel ist also nicht gewonnen
    }

    private void initSpielfeld() {
        for (int i = 0; i < spielfeld.length; i++)
            for (int b = 0; b < spielfeld[i].length; b++) {
                spielfeld[i][b] = -1;   //Alle Felder des Arrays werden auf -1 gesetzt
            }
    }
}

