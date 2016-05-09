package jonasotto.waehrungsrechner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void umrechnen(View view) {
        EditText eingabe = (EditText) findViewById(R.id.editTextEingabe);                                   //Eingabetextfeld
        TextView ausgabe = (TextView) findViewById(R.id.textViewErgebnis);                                  //Ausgabefeld
        try {
            String eingabeWert = eingabe.getText().toString();                                              //eingegebener Text
            String waehrung = eingabeWert.substring(eingabeWert.indexOf(" ") + 1, eingabeWert.length());    //eingegebene Währung
            float wert = Float.valueOf(eingabeWert.substring(0, eingabeWert.indexOf(" ")));                 //eingegebener Wert
            if (waehrung.equals("EUR")) {                                                                   //Eingabe in EUR
                wert = wert * (float) 1.1269;                                                               //Umrechnung zu USD
                ausgabe.setText(wert + " USD");                                                             //Ausgabe des Wertes in USD
            } else if (waehrung.equals("USD")) {                                                            //Eingabe in USD
                wert = wert * (float) 0.887390185;                                                          //Umrechnung zu EUR
                ausgabe.setText(wert + " EUR");                                                             //Ausgabe des Wertes in EUR
            }
        } catch (Exception e) {                                                                             //Fehler abfangen
            e.printStackTrace();                                                                            // Fehler ausgeben
        }
    }
}