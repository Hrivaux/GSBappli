package fr.delcey.formulairedeconnexion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class InscriptionUsers extends AppCompatActivity {

    private EditText etNomUtilisateur;
    private EditText etMotDePasse;
    private EditText etVille;
    private EditText etDepartement;
    private DatePicker datePicker;
    private RadioGroup radioGroup;
    private RadioButton radioFemme;
    private RadioButton radioHomme;
    private Button btnInscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_users);

        etNomUtilisateur = findViewById(R.id.et_nom_utilisateur);
        etMotDePasse = findViewById(R.id.et_mot_de_passe);
        etVille = findViewById(R.id.et_ville);
        etDepartement = findViewById(R.id.et_departement);
        datePicker = findViewById(R.id.date_picker);
        radioGroup = findViewById(R.id.radio_group);
        radioFemme = findViewById(R.id.radio_femme);
        radioHomme = findViewById(R.id.radio_homme);
        btnInscription = findViewById(R.id.btn_inscription);

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomUtilisateur = etNomUtilisateur.getText().toString();
                String motDePasse = etMotDePasse.getText().toString();
                String ville = etVille.getText().toString();
                String departement = etDepartement.getText().toString();
                int jour = datePicker.getDayOfMonth();
                int mois = datePicker.getMonth() + 1;
                int annee = datePicker.getYear();
                String dateNaissance = jour + "/" + mois + "/" + annee;
                String sexe = radioFemme.isChecked() ? "Femme" : "Homme";

                // Faire quelque chose avec les données d'inscription ici
            }
        });
    }
}

