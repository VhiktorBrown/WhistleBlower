package com.chocolatedevelopers.whistleblower.verified;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.chocolatedevelopers.whistleblower.MainActivity;
import com.chocolatedevelopers.whistleblower.R;
import com.chocolatedevelopers.whistleblower.accounts.AccountsActivity;
import com.chocolatedevelopers.whistleblower.alerts.AlertsActivity;
import com.chocolatedevelopers.whistleblower.utils.BottomNavigationUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class VerifiedActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 0;
    private BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified);

        initComponent();
    }

    private void initComponent(){

        navigation = findViewById(R.id.navigation);
        BottomNavigationUtils.enableBottomNavigation(this, navigation);
        Menu menu = navigation.getMenu();
        MenuItem item = menu.getItem(ACTIVITY_NUM);
        item.setChecked(true);
    }
}