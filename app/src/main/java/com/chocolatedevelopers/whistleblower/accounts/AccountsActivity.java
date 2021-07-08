package com.chocolatedevelopers.whistleblower.accounts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.chocolatedevelopers.whistleblower.R;
import com.chocolatedevelopers.whistleblower.utils.BottomNavigationUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccountsActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 1;
    private BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

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