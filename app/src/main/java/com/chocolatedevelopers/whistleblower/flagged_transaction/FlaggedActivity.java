package com.chocolatedevelopers.whistleblower.flagged_transaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.chocolatedevelopers.whistleblower.R;
import com.chocolatedevelopers.whistleblower.databinding.ActivityFlaggedBinding;
import com.chocolatedevelopers.whistleblower.databinding.ActivityNotificationsBinding;
import com.chocolatedevelopers.whistleblower.model.TransactionDetails;
import com.chocolatedevelopers.whistleblower.utils.BottomNavigationUtils;
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

        flaggedArrayList.add(new TransactionDetails("8 packs of A4 paper", "Williams", "3400", "05, July 2021", "09:23am", false));
        flaggedArrayList.add(new TransactionDetails("Expenses for Salt lake conference", "Tina", "20000", "22, June 2021", "02:10pm",false));
        flaggedArrayList.add(new TransactionDetails("15 Office chairs for the main lounge", "Richard", "23000", "02, June 2021", "01:02pm",false));
        flaggedArrayList.add(new TransactionDetails("Workmanship for the Electrician to fix the meter", "James", "2300", "05, May 2021", "10:17am", false));

        flaggedArrayList.add(new TransactionDetails("8 packs of A4 paper", "Williams", "3400", "05, July 2021", "09:23am", false));
        flaggedArrayList.add(new TransactionDetails("Expenses for Salt lake conference", "Tina", "20000", "22, June 2021", "02:10pm", false));
        flaggedArrayList.add(new TransactionDetails("15 Office chairs for the main lounge", "Richard", "23000", "02, June 2021", "01:02pm", false));
        flaggedArrayList.add(new TransactionDetails("Workmanship for the Electrician to fix the meter", "James", "2300", "05, May 2021", "10:17am", false));

        adapter = new FlaggedAdapter(this, flaggedArrayList);
        binding.recyclerView.setAdapter(adapter);
    }
}