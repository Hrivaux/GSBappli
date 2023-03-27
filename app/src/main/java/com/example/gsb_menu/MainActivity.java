package com.example.gsb_menu;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open_nav, R.string.Close_nav);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new homeFragment()).commit();
                break;

            case R.id.nav_rdv:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new RdvFragment()).commit();
                break;

            case R.id.nav_visites:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new VisitesFragment()).commit();
                break;

            case R.id.nav_list_compte:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new list_compte_Fragment()).commit();
                break;

            case R.id.nav_list_medecins:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new list_medecins_Fragment()).commit();
                break;

            case R.id.nav_add_medecins:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new add_medecinsFragment()).commit();
                break;

            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new settingsFragment()).commit();
                break;

            case R.id.nav_notifications:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new notificationsFragment()).commit();
                break;

            case R.id.nav_add_compte:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new add_compte_Fragment()).commit();
                break;

            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new homeFragment()).commit();
                break;

            case R.id.nav_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, loginActivity.class);
                startActivity(intent);
                finish(); // optionnel, pour fermer l'activité actuelle
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
// la méthode publique pour charger le Fragment inscriptionR
    public void loadInscriptionFragment(String nom, String prenom, String visiteur, String email, String password, String ville, String adresse, String codePostal) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        inscriptionR fragment = new inscriptionR();
        Bundle args = new Bundle();
        args.putString("nom", nom);
        args.putString("prenom", prenom);
        args.putString("visiteur", visiteur);
        args.putString("email", email);
        args.putString("password", password);
        args.putString("ville", ville);
        args.putString("adresse", adresse);
        args.putString("codePostal", codePostal);
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
    // la méthode publique pour charger le Fragment notificationR

    public void loadNotificationFragment(String objet, String message, String destinataire) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        notificationR fragment = new notificationR();

        Bundle args = new Bundle();
        args.putString("objet", objet);
        args.putString("message", message);
        args.putString("destinataire", destinataire);


        fragment.setArguments(args);

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }








}