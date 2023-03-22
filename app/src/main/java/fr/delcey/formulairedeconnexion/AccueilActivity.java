package fr.delcey.formulairedeconnexion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.app.Activity;


public class AccueilActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Button backButton = (Button) findViewById(R.id.back_button);
        Button buttonGoToForm = (Button) findViewById(R.id.buttonGoToForm);


        TextView textWelc = findViewById(R.id.TextWelcome);
        String username = getIntent().getStringExtra("username");
        if (textWelc != null) {
            textWelc.setText("Bienvenue, " + username + " !");
        }


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(AccueilActivity.this, MainActivity.class);
                startActivity(intentMain);
            }
        });

        buttonGoToForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRed = new Intent(AccueilActivity.this, InscriptionUsers.class);
                startActivity(intentRed);
            }
        });
    }

}
