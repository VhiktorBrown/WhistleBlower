package com.chocolatedevelopers.whistleblower.flagged_transaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.chocolatedevelopers.whistleblower.R;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFlaggedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initToolbar();
        initComponent();
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

        flaggedArrayList.add(new TransactionDetails(1,"Obi", "8 packs of A4 paper", "45", "3400",
                "05, July 2021", "09:23am", 0));
        flaggedArrayList.add(new TransactionDetails(2,"John", "Expenses for Salt lake conference",
                "45",  "20000", "22, June 2021", "02:10pm",1));
        flaggedArrayList.add(new TransactionDetails(3,"Cletus", "15 Office chairs for the main " +
                "lounge"
                ,  "45", "23000", "02, June 2021", "01:02pm",0));
        flaggedArrayList.add(new TransactionDetails(4,"Sam","Workmanship for the Electrician to " +
                "fix the meter",  "45", "2300", "05, May 2021", "10:17am", 1));

        flaggedArrayList.add(new TransactionDetails(5, "Gabby", "8 packs of A4 paper",  "45",
                "3400", "05, July 2021", "09:23am", 1));
        flaggedArrayList.add(new TransactionDetails(6, "Constance","Expenses for Salt lake " +
                "conference",  "45", "20000", "22, June 2021", "02:10pm", 0));
        flaggedArrayList.add(new TransactionDetails(7, "Peaky", "15 Office chairs for the main " +
                "lounge",  "45", "23000", "02, June 2021", "01:02pm", 1));
        flaggedArrayList.add(new TransactionDetails(8, "Dust", "Workmanship for the Electrician " +
                "to fix" +
                " the meter",  "45", "2300", "05, May 2021", "10:17am", 0));

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