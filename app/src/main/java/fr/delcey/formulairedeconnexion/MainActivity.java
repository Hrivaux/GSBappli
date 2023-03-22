package fr.delcey.formulairedeconnexion;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import fr.delcey.formulairedeconnexion.R;

public class    MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    String userVar, passVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        login = findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userVar = username.getText().toString();
                passVar = password.getText().toString();
                if (userVar.equals("")) {
                    Toast.makeText(getApplicationContext(), "L'identifiant ne peut pas être vide", Toast.LENGTH_SHORT).show();
                } else if (passVar.equals("")) {
                    Toast.makeText(getApplicationContext(), "Le mot de passe ne peut pas être vide", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getApplicationContext(), "login method to proceed", Toast.LENGTH_SHORT).show();
                    Login lg = new Login(MainActivity.this);
                    lg.execute();
                }
            }
        });
    }


    class Login extends AsyncTask<String, Void, Integer> {

        AlertDialog alertDialog;
        Context context;
        ProgressDialog progressDialog;

        Login(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context, "", "Connexion en cours... Veuillez patienter");
        }

        @Override
        protected Integer doInBackground(String... params) {
            String login_url = "https://hugo-rivaux.fr/API/login.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(userVar, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(passVar, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }
                    bufferedReader.close();
                    inputStream.close();

                    JSONObject jsonObject = new JSONObject(response.toString());
                    return jsonObject.getInt("status");
                } else {
                    return HttpURLConnection.HTTP_BAD_REQUEST;
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return HttpURLConnection.HTTP_BAD_REQUEST;
            }
        }

        @Override
        protected void onPostExecute(Integer status) {
            progressDialog.dismiss();

            if (status == HttpURLConnection.HTTP_OK) {
                username.setText("");
                password.setText("");
                Intent intentAccueil = new Intent(MainActivity.this, AccueilActivity.class);

                startActivity(intentAccueil);
            } else if (status == HttpURLConnection.HTTP_BAD_REQUEST) {
                Toast.makeText(context, "Email ou mot de passe incorrect ", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Échec de connexion au serveur. S'il vous plaît, vérifiez votre connexion à internet et réessayez.", Toast.LENGTH_LONG).show();
            }
        }
    }
}