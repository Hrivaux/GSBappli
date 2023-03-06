package fr.delcey.formulairedeconnexion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.delcey.formulairedeconnexion.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username.equals("users") && password.equals("mdp123")) {
                    Intent intent = new Intent(MainActivity.this, AccueilActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Nom d'utilisateur ou mot de passe incorrect.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}