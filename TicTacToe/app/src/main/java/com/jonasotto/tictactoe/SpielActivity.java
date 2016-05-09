package com.jonasotto.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SpielActivity extends AppCompatActivity {

    int aktiverSpieler = 0; //Aktueller Spieler, X=0, O=1
    TextView textViewSpieler;

    Button button_1_1;  //Alle Felder des Spielfeldes
    Button button_1_2;
    Button button_1_3;
    Button button_2_1;
    Button button_2_2;
    Button button_2_3;
    Button button_3_1;
    Button button_3_2;
    Button button_3_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        button_1_1 = (Button) findViewById(R.id.button_1_1);    //Spielfeldfelder
        button_1_2 = (Button) findViewById(R.id.button_1_2);
        button_1_3 = (Button) findViewById(R.id.button_1_3);
        button_2_1 = (Button) findViewById(R.id.button_2_1);
        button_2_2 = (Button) findViewById(R.id.button_2_2);
        button_2_3 = (Button) findViewById(R.id.button_2_3);
        button_3_1 = (Button) findViewById(R.id.button_3_1);
        button_3_2 = (Button) findViewById(R.id.button_3_2);
        button_3_3 = (Button) findViewById(R.id.button_3_3);

        textViewSpieler = (TextView) findViewById(R.id.textViewSpieler);
    }

    public void game_click(View view) {     //Wird aufgerufen, sobald ein Feld angeklickt wird
        Button button = (Button) view;  //"button" ist der aktuell gedrückte Button

        switch (aktiverSpieler) {
            case 0: //Spieler X/1 am Zug
                if (button.getText().equals("")) {  //Wenn das Feld frei ist...
                    button.setText("X");    //Feld auf X setzen
                    button.setTextColor(ContextCompat.getColor(this, R.color.spieler1));    //Farbe für Spieler 0 aus den Ressourcen laden, auf button anwenden
                }
                break;

            case 1: //Spieler O/2 am Zug
                if (button.getText().equals("")) { //Wenn das Feld frei ist...
                    button.setText("O");    //Feld auf O setzen
                    button.setTextColor(ContextCompat.getColor(this, R.color.spieler2));    //Farbe für Spieler 1 aus den Ressourcen laden, auf button anwenden
                }
                break;
        }

        if (spielEnde()) {
            Intent spielEnde = new Intent(SpielActivity.this, SpielEndeActivity.class); //Neuer Intent um SpielEndeActivity zu starten
            Bundle optionen = new Bundle(); //Neue Optionen
            optionen.putInt("gewinner", aktiverSpieler); //Wert "gewinner" in Optionen auf aktuellen Spieler festlegen
            spielEnde.putExtras(optionen);  //Optionen an Intent anhängen
            startActivity(spielEnde);   //Intent ausführen, neue Activity SpielEndeActivity starten
            finish();   //Aktuelle Activity beenden
        }

        if (spielGleichstand()) {
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


    boolean spielEnde() {
        //Überprüft, ob das Spiel gewonnen ist, jedoch nicht, wer gewonnen hat
        return ((button_1_1.getText().equals(button_1_2.getText()) && button_1_2.getText().equals(button_1_3.getText()) && !button_1_1.getText().equals("")) ||     //Spalte 1 gleich und nicht leer
                (button_2_1.getText().equals(button_2_2.getText()) && button_2_2.getText().equals(button_2_3.getText()) && !button_2_1.getText().equals("")) || //Spalte 2 gleich und nicht leer
                (button_3_1.getText().equals(button_3_2.getText()) && button_3_2.getText().equals(button_3_3.getText()) && !button_3_1.getText().equals("")) || //Spalte 3 gleich und nicht leer

                (button_1_1.getText().equals(button_2_1.getText()) && button_2_1.getText().equals(button_3_1.getText()) && !button_1_1.getText().equals("")) || //Zeile 1 gleich und nicht leer
                (button_1_2.getText().equals(button_2_2.getText()) && button_2_2.getText().equals(button_3_2.getText()) && !button_1_2.getText().equals("")) || //Zeile 2 gleich und nicht leer
                (button_1_3.getText().equals(button_2_3.getText()) && button_2_3.getText().equals(button_3_3.getText()) && !button_1_3.getText().equals("")) || //Zeile 3 gleich und nicht leer

                (button_1_1.getText().equals(button_2_2.getText()) && button_2_2.getText().equals(button_3_3.getText()) && !button_1_1.getText().equals("")) || //Diagonale gleich und nicht leer
                (button_3_1.getText().equals(button_2_2.getText()) && button_2_2.getText().equals(button_1_3.getText()) && !button_3_1.getText().equals(""))); //2. Diagonale gleich und nicht leer
    }

    boolean spielGleichstand() {
        //Überprüft, ob ein Gleichstand vorliegt
        return (!spielEnde()) && ((!button_1_1.getText().equals("")) &&
                (!button_1_2.getText().equals("")) &&
                (!button_1_3.getText().equals("")) &&
                (!button_2_1.getText().equals("")) &&
                (!button_2_2.getText().equals("")) &&
                (!button_2_3.getText().equals("")) &&
                (!button_3_1.getText().equals("")) &&
                (!button_3_2.getText().equals("")) &&
                (!button_3_3.getText().equals("")));
    }
}
