package com.local.proyectoard;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Macty extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
  DrawerLayout macty;
    NavigationView navView;
    ActionBarDrawerToggle toggle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.macty);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        macty = (DrawerLayout) findViewById(R.id.macty);
        navView = (NavigationView) findViewById(R.id.navView);
toggle= new ActionBarDrawerToggle(this,macty,R.string.navigation_drawer_open,R.string.navigation_drawer_close){};
        macty.setDrawerListener(toggle);

        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        toggle.syncState();
            }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        /*Dejar super hasta abajo para que lo de este bloque tenga efecto*/
        if (macty.isDrawerOpen(GravityCompat.START)) {
            macty.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //fin clase
}
