package com.chocolatedevelopers.whistleblower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.chocolatedevelopers.whistleblower.databinding.ActivityMainBinding;
import com.chocolatedevelopers.whistleblower.model.TransactionDetails;
import com.chocolatedevelopers.whistleblower.transaction.TransactionActivity;
import com.chocolatedevelopers.whistleblower.transaction.TransactionAdapter;
import com.chocolatedevelopers.whistleblower.utils.BottomNavigationUtils;
import com.chocolatedevelopers.whistleblower.utils.Tools;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static final int ACTIVITY_NUM = 0;
    private static final int MAX_STEP = 4;
    private TransactionAdapter adapter;
    ArrayList<TransactionDetails> transactionDetailsArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initToolbar();
        initComponent();
    }

    private void initComponent(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);

        transactionDetailsArrayList = new ArrayList<>();
        transactionDetailsArrayList.add(new TransactionDetails("Bought 8 packs of A4 paper", "Williams", "3400", "05, July 2021", "09:23am", false));
        transactionDetailsArrayList.add(new TransactionDetails("paid for Expenses for Salt lake conference", "Tina", "20000", "22, June 2021", "02:10pm", false));
        transactionDetailsArrayList.add(new TransactionDetails("bought 15 Office chairs for the main lounge", "Richard", "23000", "02, June 2021", "01:02pm", false));
        transactionDetailsArrayList.add(new TransactionDetails("paid for Workmanship for the Electrician to fix the meter", "James", "2300", "05, May 2021", "10:17am", false));

        //configure adapter
        adapter = new TransactionAdapter(this, transactionDetailsArrayList);

        //set adapter
        binding.recyclerView.setAdapter(adapter);

        //Bottom Navigation
        BottomNavigationUtils.enableBottomNavigation(this, binding.navigation);
        Menu menu = binding.navigation.getMenu();
        MenuItem item = menu.getItem(ACTIVITY_NUM);
        item.setChecked(true);

        binding.viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransactionActivity();
            }
        });

        binding.orderGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void openTransactionActivity() {
        Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
        startActivity(intent);
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

    private void showTransactionConfirmedDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.transaction_confirmed_dialog);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), ((AppCompatButton) v).getText().toString() + " Clicked", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void showTransactionDeniedDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.transaction_denied_dialog);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), ((AppCompatButton) v).getText().toString() + " Clicked", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
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