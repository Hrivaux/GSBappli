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

public class RedacCr extends AppCompatActivity {

    private EditText etNomMedecin;
    private EditText etDateCr;
    private EditText etEchantillonTest;
    private EditText etMotifVisite;
    private EditText etAvis;
    private EditText etEtat;
    private EditText etNouvelleVisite;
    private EditText etCommentaire;

    private Button btnEnvoyer;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redactcr);

        etNomMedecin = findViewById(R.id.et_nom_medecin);
        etDateCr = findViewById(R.id.et_date_cr);
        etEchantillonTest = findViewById(R.id.et_echantillon_teste);
        etMotifVisite = findViewById(R.id.et_motif_visite);
        etAvis = findViewById(R.id.et_avis);
        etEtat = findViewById(R.id.et_etat);
        etNouvelleVisite = findViewById(R.id.et_nouvelle_visite);
        etCommentaire = findViewById(R.id.et_commentaire);
        btnEnvoyer = findViewById(R.id.btn_envoyer);

        mRequestQueue = Volley.newRequestQueue(this);

        btnEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomMedecin = etNomMedecin.getText().toString();
                String DateCr = etDateCr.getText().toString();
                String EchantillonTest = etEchantillonTest.getText().toString();
                String MotifVisite = etMotifVisite.getText().toString();
                String Avis = etAvis.getText().toString();
                String Etat = etEtat.getText().toString();
                String NouvelleVisite = etNouvelleVisite.getText().toString();
                String Commentaire = etCommentaire.getText().toString();

                String url = "https://hugo-rivaux.fr/API/inscription.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(RedacCr.this, "Inscription réussie!", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RedacCr.this, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("nom_medeci,", nomMedecin);
                        params.put("date_cr", DateCr);
                        params.put("echantillon_test", EchantillonTest);
                        params.put("motif_visite", MotifVisite);
                        params.put("avis", Avis);
                        params.put("etat", Etat);
                        params.put("nouvelle_visite", NouvelleVisite);
                        params.put("commentaire", Commentaire);
                        return params;
                    }
                };
                mRequestQueue.add(stringRequest);
            }
        });
    }
}