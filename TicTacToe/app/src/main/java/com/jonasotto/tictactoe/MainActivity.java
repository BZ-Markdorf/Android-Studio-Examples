package com.jonasotto.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void spielStarten(View view)
    {
        Intent spielStart = new Intent(MainActivity.this, SpielActivity.class); //Neuer Intent um SpielActivity zu starten
        startActivity(spielStart);   //Intent ausführen, neue Activity SpielActivity starten
        finish();   //Aktuelle Activity beenden
    }
}
