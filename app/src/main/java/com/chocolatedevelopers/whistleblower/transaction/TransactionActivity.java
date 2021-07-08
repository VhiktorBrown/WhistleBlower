package com.chocolatedevelopers.whistleblower.transaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.chocolatedevelopers.whistleblower.R;
import com.chocolatedevelopers.whistleblower.databinding.ActivityTransactionBinding;
import com.chocolatedevelopers.whistleblower.model.TransactionDetails;
import com.chocolatedevelopers.whistleblower.utils.Tools;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {
    ActivityTransactionBinding binding;
    TransactionAdapter adapter;
    ArrayList<TransactionDetails> transactionDetailsArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initToolbar();
        initComponents();
    }

    private void initComponents(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);

        transactionDetailsArrayList = new ArrayList<>();

        transactionDetailsArrayList.add(new TransactionDetails("8 packs of A4 paper", "45", "Williams", "3400", "05, July 2021", "09:23am", false));
        transactionDetailsArrayList.add(new TransactionDetails("Expenses for Salt lake conference",  "45", "Tina", "20000", "22, June 2021", "02:10pm",true));
        transactionDetailsArrayList.add(new TransactionDetails("15 Office chairs for the main lounge",  "45", "Richard", "23000", "02, June 2021", "01:02pm",false));
        transactionDetailsArrayList.add(new TransactionDetails("Workmanship for the Electrician to fix the meter",  "45", "James", "2300", "05, May 2021", "10:17am", true));

        transactionDetailsArrayList.add(new TransactionDetails("8 packs of A4 paper",  "45", "Williams", "3400", "05, July 2021", "09:23am", true));
        transactionDetailsArrayList.add(new TransactionDetails("Expenses for Salt lake conference",  "45", "Tina", "20000", "22, June 2021", "02:10pm", false));
        transactionDetailsArrayList.add(new TransactionDetails("15 Office chairs for the main lounge",  "45", "Richard", "23000", "02, June 2021", "01:02pm", true));
        transactionDetailsArrayList.add(new TransactionDetails("Workmanship for the Electrician to fix the meter",  "45", "James", "2300", "05, May 2021", "10:17am", false));

        adapter = new TransactionAdapter(this, transactionDetailsArrayList);
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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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