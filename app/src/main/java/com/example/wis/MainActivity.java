package com.example.wis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.transition.Transition;

import com.example.wis.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Activity binding for BottomNavigation
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navVieW = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_calendar, R.id.navigation_Week, R.id.navigation_list)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        Toolbar toolbar = findViewById(R.id.topBar);
        setSupportActionBar(toolbar);

        /*
        Bundle extras = getIntent().getExtras();
        String username = null;
        if (extras != null) {
            username = extras.getString("username");

        }
         */
        ImageButton btn_logout = (ImageButton) findViewById(R.id.imageButton2);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPref.saveSharedSetting(MainActivity.this, "UserID", Integer.toString(-1));
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public String getUsername() {
        Bundle extras = getIntent().getExtras();
        String username = null;
        if (extras != null) {
            username = extras.getString("username");

        }
        return username;
    }
}
