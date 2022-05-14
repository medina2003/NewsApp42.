package com.example.newsapp42;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.newsapp42.databinding.ActivityMainBinding;
import com.example.newsapp42.models.Prefs;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;
    public static Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_profile)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        prefs = new Prefs(this);//sharedPref
        if (prefs.isShown())
            navController.navigate(R.id.boardFragment);

        navController.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            ArrayList<Integer> fragments = new ArrayList<>();
            fragments.add(R.id.navigation_home);
            fragments.add(R.id.navigation_profile);
            fragments.add(R.id.navigation_dashboard);
            fragments.add(R.id.navigation_notifications);
            if (fragments.contains(navDestination.getId())) {
                binding.navView.setVisibility(View.VISIBLE);
            } else {
                binding.navView.setVisibility(View.GONE);
            }
            if (navDestination.getId() == R.id.boardFragment)
                Objects.requireNonNull(getSupportActionBar()).hide();
            else Objects.requireNonNull(getSupportActionBar()).show();
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}