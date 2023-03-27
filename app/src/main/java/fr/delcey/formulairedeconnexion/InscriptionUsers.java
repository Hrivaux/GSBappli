package fr.delcey.formulairedeconnexion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class InscriptionUsers extends AppCompatActivity {
    private EditText etNomUtilisateur;
    private EditText etMotDePasse;
    private EditText etVille;
    private EditText etPrenomUtilisateur;
    private EditText etEmailUtilisateur;
    private EditText etAdresseUtilisateur;
    private EditText etCodepostal;

    private Button btnInscription;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_users);

        etNomUtilisateur = findViewById(R.id.et_nom_utilisateur);
        etPrenomUtilisateur = findViewById(R.id.et_prenom_utilisateur);
        etEmailUtilisateur = findViewById(R.id.et_email_utilisateur);
        etMotDePasse = findViewById(R.id.et_mot_de_passe);
        etAdresseUtilisateur = findViewById(R.id.et_adresse_utilisateur);
        etVille = findViewById(R.id.et_ville);
        etCodepostal = findViewById(R.id.et_code_postal);
        btnInscription = findViewById(R.id.btn_inscription);

        mRequestQueue = Volley.newRequestQueue(this);

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomUtilisateur = etNomUtilisateur.getText().toString();
                String prenomUtilisateur = etPrenomUtilisateur.getText().toString();
                String emailUtilisateur = etEmailUtilisateur.getText().toString();
                String motDePasse = etMotDePasse.getText().toString();
                String adresseUtilisateur = etAdresseUtilisateur.getText().toString();
                String ville = etVille.getText().toString();
                String code_postal = etCodepostal.getText().toString();

                String url = "https://hugo-rivaux.fr/API/inscription.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(InscriptionUsers.this, "Inscription réussie!", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InscriptionUsers.this, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("nom_utilisateur", nomUtilisateur);
                        params.put("prenom_utilisateur", prenomUtilisateur);
                        params.put("email_utilisateur", emailUtilisateur);
                        params.put("mot_de_passe", motDePasse);
                        params.put("adresse_utilisateur", adresseUtilisateur);
                        params.put("ville", ville);
                        params.put("code_postal", code_postal);
                        return params;
                    }
                };
                mRequestQueue.add(stringRequest);
            }
        });
    }
}