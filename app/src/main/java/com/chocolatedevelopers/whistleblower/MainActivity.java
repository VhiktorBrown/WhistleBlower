package com.chocolatedevelopers.whistleblower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.chocolatedevelopers.whistleblower.data.local.SharedPref;
import com.chocolatedevelopers.whistleblower.data.local.SqlConnector;
import com.chocolatedevelopers.whistleblower.data.model.Levels;
import com.chocolatedevelopers.whistleblower.data.model.User;
import com.chocolatedevelopers.whistleblower.databinding.ActivityMainBinding;
import com.chocolatedevelopers.whistleblower.data.model.TransactionDetails;
import com.chocolatedevelopers.whistleblower.databinding.DialogTransactionDetailsBinding;
import com.chocolatedevelopers.whistleblower.databinding.OrderGoodsDialogBinding;
import com.chocolatedevelopers.whistleblower.databinding.TransactionConfirmedDialogBinding;
import com.chocolatedevelopers.whistleblower.databinding.TransactionDeniedDialogBinding;
import com.chocolatedevelopers.whistleblower.login.LoginActivity;
import com.chocolatedevelopers.whistleblower.transaction.TransactionActivity;
import com.chocolatedevelopers.whistleblower.transaction.TransactionAdapter;
import com.chocolatedevelopers.whistleblower.utils.BottomNavigationUtils;
import com.chocolatedevelopers.whistleblower.utils.Tools;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static final int ACTIVITY_NUM = 0;
    private static final int MAX_STEP = 4;
    private TransactionAdapter adapter;
    ArrayList<TransactionDetails> transactionDetailsArrayList;
    User user;
    Levels level;
    String transactionDetails;
    double newBalance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = SharedPref.getInstance().getCurrentlySignedInUser();
        level =  SqlConnector.getInstance(this).getLevel(user.getLevelId());

        initToolbar();
        initComponent();
    }

    private void initComponent(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);

        transactionDetailsArrayList = new ArrayList<>();

        //fetch Transaction List from the database.
        transactionDetailsArrayList = SqlConnector.getInstance(this).getAllTransactions(level.getLevelId());

        //configure adapter
        adapter = new TransactionAdapter(this, transactionDetailsArrayList);

        //set adapter
        binding.recyclerView.setAdapter(adapter);

        //set Level details
        setLevelDetails();

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
                openOrderGoodsDialog();
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

    private void setLevelDetails(){
        binding.budget.setText(level.getBudgetAmount());
        binding.balance.setText(level.getBalance());
        binding.expectedProfit.setText(level.getProfitExpected());
        binding.salary.setText(level.getLevelSalary());
    }

    private void openOrderGoodsDialog(){
            Dialog dialog = new Dialog(this);
            OrderGoodsDialogBinding dialogBinding = OrderGoodsDialogBinding.inflate(dialog.getLayoutInflater());
            dialog.setContentView(dialogBinding.getRoot());
            ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
            InsetDrawable inset = new InsetDrawable(back, 20);
            dialog.getWindow().setBackgroundDrawable(inset);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            //set the username of the person trying to order the goods.
        dialogBinding.username.setText(user.getUsername());

            dialogBinding.order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String info;
                    //Here, we're getting the current date and time for the transaction
                    String dayOfTheWeek = (String) DateFormat.format("EEE", new Date());
                    String day = (String) DateFormat.format("dd", new Date());
                    String monthString = (String) DateFormat.format("MMM", new Date());
                    String year = (String) DateFormat.format("yyyy", new Date());

                    String time = (String) DateFormat.format("hh:mm", new Date());

                    if(dialogBinding.item.getText().length() > 5){
                        if(dialogBinding.itemQuantity.length() > 0 && dialogBinding.itemCost.length() > 0){

                            //TODO We would add that dialog with the piston animation to show that we are running diagnostics

                            double amount = Double.parseDouble(dialogBinding.itemCost.getText().toString());
                            double transactionLimit = Double.parseDouble(level.getTransactionLimit());
                            double balance = Double.parseDouble(level.getBalance());
                            if(balance > amount){
                                /*
                                After checking if the balance left is the greater than the amount the user wants
                                to order, we'll then check to see if  the amount the user wants to order for is
                                greater than the transaction Limit for his or her level.
                                 */
                                if(amount > transactionLimit){
                                    //here, we'll set transactionDetails.
                                    transactionDetails = user.getUsername() + " tried to make an order that exceeded the transaction limit for this level.";

                                    info = "You cannot order for goods greater than $ " + level.getTransactionLimit() + " for your level.";
                                    TransactionDetails details = new TransactionDetails(
                                            level.getLevelId(),
                                            dialogBinding.item.getText().toString(),
                                            dialogBinding.itemQuantity.getText().toString(),
                                            user.getUsername(),
                                            dialogBinding.itemCost.getText().toString(),
                                            transactionDetails,
                                            dayOfTheWeek + " " + day + " " + monthString + "," + year,
                                            time,
                                            0
                                    );
                                    SqlConnector.getInstance(MainActivity.this).insertTransaction(details);
                                    dialog.dismiss();
                                    showTransactionDeniedDialog(info);
                                } else {
                                    /*
                                    Our code would jump to this point if it successfully went through all our checks.
                                    This is where the transaction would become successful.
                                     */

                                    //Here, we would set the transaction details to be successful
                                    transactionDetails = "Transaction was successful";
                                    info = "This transaction has been confirmed fraud free after running our diagnostics.";
                                    TransactionDetails details = new TransactionDetails(
                                            level.getLevelId(),
                                            dialogBinding.item.getText().toString(),
                                            dialogBinding.itemQuantity.getText().toString(),
                                            user.getUsername(),
                                            dialogBinding.itemCost.getText().toString(),
                                            transactionDetails,
                                            dayOfTheWeek + " " + day + " " + monthString + "," + year,
                                            time,
                                            1
                                    );
                                    SqlConnector.getInstance(MainActivity.this).insertTransaction(details);

                                    /** After transaction has been confirmed to be successful.
                                     * we'll then subtract the amount from the balance.
                                     */
                                    newBalance = balance - amount;
                                    level.setBalance(String.valueOf(newBalance));
                                    //Then, we'll edit the balance in the database.
                                    SqlConnector.getInstance(MainActivity.this).editLevel(level);
                                    dialog.dismiss();
                                    showTransactionConfirmedDialog(info);
                                }

                            } else {
                                /*
                                If the balance cannot purchase the goods user wants to buy,
                                this is where we would define what would happen.
                                 */
                                transactionDetails = user.getUsername() + " tried to make transactions after budget for this level had been exhausted";
                                info = "The budget for your level has been exhausted. Therefore, you cannot make an order.";
                                TransactionDetails details = new TransactionDetails(
                                        level.getLevelId(),
                                        dialogBinding.item.getText().toString(),
                                        dialogBinding.itemQuantity.getText().toString(),
                                        user.getUsername(),
                                        dialogBinding.itemCost.getText().toString(),
                                        transactionDetails,
                                        dayOfTheWeek + " " + day + " " + monthString + "," + year,
                                        time,
                                        0
                                );
                                SqlConnector.getInstance(MainActivity.this).insertTransaction(details);
                                dialog.dismiss();
                                showTransactionDeniedDialog(info);
                            }
                        }
                    } else {
                        displayToast("Item to be purchased must contain more than 5 characters. It's too short");
                    }
                }
            });
    }

    private void showTransactionConfirmedDialog(String info) {
        final Dialog dialog = new Dialog(this);
        TransactionConfirmedDialogBinding dialogBinding = TransactionConfirmedDialogBinding.inflate(dialog.getLayoutInflater());
        dialog.setContentView(dialogBinding.getRoot());
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 20);
        dialog.getWindow().setBackgroundDrawable(inset);
        dialog.setCanceledOnTouchOutside(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialogBinding.content.setText(info);

        dialog.show();
        dialog.getWindow().setAttributes(lp);

        dialogBinding.btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showTransactionDeniedDialog(String info) {
        final Dialog dialog = new Dialog(this);
        TransactionDeniedDialogBinding dialogBinding = TransactionDeniedDialogBinding.inflate(dialog.getLayoutInflater());
        dialog.setContentView(dialogBinding.getRoot());
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 20);
        dialog.getWindow().setBackgroundDrawable(inset);
        dialog.setCanceledOnTouchOutside(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialogBinding.content.setText(info);

        dialog.show();
        dialog.getWindow().setAttributes(lp);

        dialogBinding.btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void displayToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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