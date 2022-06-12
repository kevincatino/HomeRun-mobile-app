package com.hci.homerunapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.hci.homerunapp.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("AJAJAJ", "MainActivity: onCreate() recreated");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


            NavHostFragment navHostFragment =
                    (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
            navController = navHostFragment.getNavController();


        BottomNavigationView navView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
//        getForegroundFragment().
//        Log.d("IDUPNAV", String.valueOf());
//        Log.d("IDUPNAV", String.valueOf(navController.getCurrentDestination().getId()));

        if(navController.getCurrentDestination().getId() == R.id.navigation_room)
            showBottomNav();
        return navController.navigateUp();
    }

    public void hideBottomNav() {
        binding.navView.setVisibility(View.GONE);
    }

    public void showBottomNav() {
        binding.navView.setVisibility(View.VISIBLE);
    }

}