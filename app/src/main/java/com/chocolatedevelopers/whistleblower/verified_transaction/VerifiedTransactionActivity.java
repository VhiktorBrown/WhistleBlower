package com.chocolatedevelopers.whistleblower.verified_transaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.chocolatedevelopers.whistleblower.R;
import com.chocolatedevelopers.whistleblower.databinding.ActivityVerifiedTransactionsBinding;
import com.chocolatedevelopers.whistleblower.data.model.TransactionDetails;
import com.chocolatedevelopers.whistleblower.utils.BottomNavigationUtils;
import com.chocolatedevelopers.whistleblower.utils.Tools;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class VerifiedTransactionActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUM = 1;
    private BottomNavigationView navigation;
    ActivityVerifiedTransactionsBinding binding;
    ArrayList<TransactionDetails> transactionDetailsArrayList;
    VerifiedTransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifiedTransactionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initToolbar();
        initComponent();
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

    private void initComponent(){

        navigation = findViewById(R.id.navigation);

        BottomNavigationUtils.enableBottomNavigation(this, navigation);
        Menu menu = navigation.getMenu();
        MenuItem item = menu.getItem(ACTIVITY_NUM);
        item.setChecked(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);

        transactionDetailsArrayList = new ArrayList<>();

        transactionDetailsArrayList.add(new TransactionDetails(1,
                "Williams", "Bought 8 packs of A4 paper", "45", "3400", "05, July 2021", "09:23am"
                , 1));
        transactionDetailsArrayList.add(new TransactionDetails(2
                , "Tina", "paid for Expenses for Salt lake ", "23", "20000", "22, June 2021", "02" +
                ":10pm", 1));
        transactionDetailsArrayList.add(new TransactionDetails(3, "Richard",
                "bought 15 Office chairs for the ", "56", "23000","02, June 2021", "01:02pm", 1));
        transactionDetailsArrayList.add(new TransactionDetails(4, "James",
                "paid for Workmanship for the Electrician to fix the meter","1", "2300",
                "05, May 2021", "10:17am", 1));

        transactionDetailsArrayList.add(new TransactionDetails(5,
                "Williams", "Bought 8 packs of A4 paper", "45", "3400", "05, July 2021", "09:23am"
                , 1));
        transactionDetailsArrayList.add(new TransactionDetails(6
                , "Tina", "paid for Expenses for Salt lake ", "23", "20000", "22, June 2021", "02" +
                ":10pm", 1));
        transactionDetailsArrayList.add(new TransactionDetails(7, "Richard",
                "bought 15 Office chairs for the ", "56", "23000","02, June 2021", "01:02pm", 1));
        transactionDetailsArrayList.add(new TransactionDetails(8, "James",
                "paid for Workmanship for the Electrician to fix the meter","1", "2300",
                "05, May 2021", "10:17am", 1));
        adapter = new VerifiedTransactionAdapter(this, transactionDetailsArrayList);
        binding.recyclerView.setAdapter(adapter);
    }
}