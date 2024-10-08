package com.example.scud;

import android.os.Bundle;

import com.example.scud.model.DataModel;
import com.example.scud.ui.SharedViewModel;
import com.example.scud.ui.auth.AuthViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.scud.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_scan, R.id.navigation_show, R.id.navigation_account)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        Bundle bundle = new Bundle();
        bundle.putString("login", getIntent().getStringExtra("login"));
        bundle.putString("id", getIntent().getStringExtra("id"));
        bundle.putString("firstName", getIntent().getStringExtra("firstName"));
        bundle.putString("lastName", getIntent().getStringExtra("lastName"));
        bundle.putString("middleName", getIntent().getStringExtra("middleName"));
        bundle.putString("email", getIntent().getStringExtra("email"));

        navController.navigate(R.id.navigation_account, bundle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_account);
        return true;

    }

}