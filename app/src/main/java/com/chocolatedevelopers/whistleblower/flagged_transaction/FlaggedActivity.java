package com.chocolatedevelopers.whistleblower.flagged_transaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.chocolatedevelopers.whistleblower.R;
import com.chocolatedevelopers.whistleblower.data.local.SharedPref;
import com.chocolatedevelopers.whistleblower.data.local.SqlConnector;
import com.chocolatedevelopers.whistleblower.data.model.Levels;
import com.chocolatedevelopers.whistleblower.data.model.User;
import com.chocolatedevelopers.whistleblower.databinding.ActivityFlaggedBinding;
import com.chocolatedevelopers.whistleblower.data.model.TransactionDetails;
import com.chocolatedevelopers.whistleblower.utils.BottomNavigationUtils;
import com.chocolatedevelopers.whistleblower.utils.Tools;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FlaggedActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 2;
    private BottomNavigationView navigation;
    ActivityFlaggedBinding binding;
    FlaggedAdapter adapter;
    ArrayList<TransactionDetails> flaggedArrayList;
    User user;
    Levels levels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFlaggedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initToolbar();
        initComponent();

        user = SharedPref.getInstance().getCurrentlySignedInUser();
        levels = SqlConnector.getInstance(this).getLevel(user.getLevelId());

    }

    private void initComponent(){
        navigation = findViewById(R.id.navigation);

        BottomNavigationUtils.enableBottomNavigation(this, navigation);
        Menu menu = navigation.getMenu();
        MenuItem item = menu.getItem(ACTIVITY_NUM);
        item.setChecked(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);

        flaggedArrayList = new ArrayList<>();

        flaggedArrayList = SqlConnector.getInstance(this).getVerifiedOrDenied(1, levels.getLevelId());

        adapter = new FlaggedAdapter(this, flaggedArrayList);
        binding.recyclerView.setAdapter(adapter);
    }

    private void initToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.changeNavigateionIconColor(binding.toolbar, getResources().getColor(R.color.colorPrimaryDark));
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}