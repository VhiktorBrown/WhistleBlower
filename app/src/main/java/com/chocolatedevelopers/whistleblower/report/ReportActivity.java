package com.chocolatedevelopers.whistleblower.report;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.chocolatedevelopers.whistleblower.R;
import com.chocolatedevelopers.whistleblower.databinding.ActivityReportsBinding;
import com.chocolatedevelopers.whistleblower.model.ReportDetails;
import com.chocolatedevelopers.whistleblower.model.TransactionDetails;
import com.chocolatedevelopers.whistleblower.transaction.TransactionAdapter;
import com.chocolatedevelopers.whistleblower.utils.BottomNavigationUtils;
import com.chocolatedevelopers.whistleblower.utils.Tools;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 3;
    private BottomNavigationView navigation;
    ActivityReportsBinding binding;
    ArrayList<ReportDetails> reportDetailsArrayList;
    ReportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initToolbar();
        initComponent();
    }

    private void initComponent(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);

        reportDetailsArrayList = new ArrayList<>();

        reportDetailsArrayList.add(new ReportDetails("January", "1,500,000", "900,000", "252,690,000", "17"));
        reportDetailsArrayList.add(new ReportDetails("February", "1,500,000", "900,000", "252,690,000", "17"));
        reportDetailsArrayList.add(new ReportDetails("March", "1,500,000", "900,000", "252,690,000", "17"));
        reportDetailsArrayList.add(new ReportDetails("April", "1,500,000", "900,000", "252,690,000", "17"));

        reportDetailsArrayList.add(new ReportDetails("May", "1,500,000", "900,000", "252,690,000", "17"));
        reportDetailsArrayList.add(new ReportDetails("June", "1,500,000", "900,000", "252,690,000", "17"));
        reportDetailsArrayList.add(new ReportDetails("July", "1,500,000", "900,000", "252,690,000", "17"));
        reportDetailsArrayList.add(new ReportDetails("August", "1,500,000", "900,000", "252,690,000", "17"));

        adapter = new ReportAdapter(this, reportDetailsArrayList);
        binding.recyclerView.setAdapter(adapter);

        navigation = findViewById(R.id.navigation);
        BottomNavigationUtils.enableBottomNavigation(this, navigation);
        Menu menu = navigation.getMenu();
        MenuItem item = menu.getItem(ACTIVITY_NUM);
        item.setChecked(true);
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
}