package com.example.alber.pocketapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private Fragment_index fragment_index;
    private Fragment_contact fragment_contact;
    private Fragment_about fragment_about;

    private BottomNavigationView.OnNavigationItemSelectedListener OnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.index:
                    fragment_index = new Fragment_index();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_index,"fragment_index").commitAllowingStateLoss();
                    return true;
                case R.id.contact:
                    fragment_contact = new Fragment_contact();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_contact,"fragment_contact").commitAllowingStateLoss();
                    return true;
                case R.id.about:
                    fragment_about = new Fragment_about();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_about,"fragment_about").commitAllowingStateLoss();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener);
        fragment_index = new Fragment_index();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_index,"fragment_index").commitAllowingStateLoss();

    }
}
