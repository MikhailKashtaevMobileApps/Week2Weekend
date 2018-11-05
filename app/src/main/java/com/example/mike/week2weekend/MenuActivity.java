package com.example.mike.week2weekend;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private int MainContentVliew = R.layout.main_wrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_wrapper);

        drawerLayout = findViewById( R.id.dl_DrawerLayout );
        navigationView = findViewById( R.id.nv_navigationDrawer );

        // Setting event listener to menu items
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                switch ( menuItem.getItemId() ){
                    case R.id.item1:
                        // Clicked home button
                        Intent goToHome = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(goToHome);
                        break;
                    case R.id.item2:
                        // Take a picture activity
                        Intent goToPicture = new Intent(getApplicationContext(), MathActivity.class);
                        startActivity( goToPicture );
                        break;
                    case R.id.item3:
                        Intent goToWeather = new Intent( getApplicationContext(), WakawawaActivity.class );
                        startActivity( goToWeather );
                        // Weather app
                        // API KEY ec5cce73f7fbc2fe31e6b9c4e10e39bb
                        break;
                }
                return true;
            }
        });

        // Setting custon action tool bar
        Toolbar toolbar = findViewById( R.id.tb_toolbar );
        setSupportActionBar(toolbar);

        // Setting picture to button
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.toolbar_overflow_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Open drawer when menu button clicked
        switch (item.getItemId()) {

            case R.id.overflowItem1:
                Toast.makeText( this, "Thank you for your feedback!", Toast.LENGTH_SHORT ).show();
                return true;

            case R.id.overflowItem2:
                Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse("tel:9083397651") );
                startActivity(intent);
                return true;

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setInclude(int layoutResID){
        LinearLayout mainLayout = findViewById(R.id.layoutContent);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(layoutResID, null);
        mainLayout.removeAllViews();
        mainLayout.addView(layout);
    }

}
