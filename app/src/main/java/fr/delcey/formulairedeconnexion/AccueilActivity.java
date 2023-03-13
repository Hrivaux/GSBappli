package fr.delcey.formulairedeconnexion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccueilActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_accueil);
        setContentView(R.layout.activity_redact_cr);
        Button backButton = findViewById(R.id.back_button);
        Button buttonGoToForm = findViewById(R.id.buttonGoToForm);


        TextView TextView = findViewById(R.id.TextView);
        String username = getIntent().getStringExtra("username");
        TextView.setText("Bienvenue, " + username + " !");


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonGoToForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccueilActivity.this, RedactCr.class));
            }
        });
    }

}
