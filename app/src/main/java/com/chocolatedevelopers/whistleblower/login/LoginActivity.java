package com.chocolatedevelopers.whistleblower.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chocolatedevelopers.whistleblower.MainActivity;
import com.chocolatedevelopers.whistleblower.R;
import com.chocolatedevelopers.whistleblower.data.local.SharedPref;
import com.chocolatedevelopers.whistleblower.data.local.SqlConnector;
import com.chocolatedevelopers.whistleblower.data.model.Card;
import com.chocolatedevelopers.whistleblower.data.model.Levels;
import com.chocolatedevelopers.whistleblower.data.model.TransactionDetails;
import com.chocolatedevelopers.whistleblower.data.model.User;
import com.chocolatedevelopers.whistleblower.databinding.ActivityLoginBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    Button login;
    ArrayList<User> userArrayList;
    ArrayList<Levels> levelsArrayList;
    ArrayList<Card> cardArrayList;
    ArrayList<TransactionDetails> transactionDetails;
    ActivityLoginBinding binding;
    private static final String TAG = "LoginActivity";

    //TODO Let's not forget to add a progress dialog that would display when user is trying to log in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        login = findViewById(R.id.login);

        userArrayList = new ArrayList<>();
        levelsArrayList = new ArrayList<>();
        cardArrayList = new ArrayList<>();
        transactionDetails = new ArrayList<>();

        //Let's query the database for users. If there isn't any, we would add it.
        userArrayList = SqlConnector.getInstance(this).getAllUsers();
        levelsArrayList = SqlConnector.getInstance(this).getAllLevels();

        if(userArrayList.size() == 0 && levelsArrayList.size() == 0){
            addDummyData();
            addDummyTransactions();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.username.getText().toString();
                String password = binding.password.getText().toString();
                if (username.isEmpty() && password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    User user = SqlConnector.getInstance(LoginActivity.this).getUser(username, password);
                    if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                        SharedPref.getInstance().saveCurrentlySignedUser(user);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        displayToast("Username and Password incorrect. Please try again");
                    }
                }
            }
        });
    }

    private void displayToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Every time user logs in, we'll run a check in the database to see if
     * there's already dummy data. If there isn't, we'll just add the dummy data.
     */
    private void addDummyData(){

        //add Dummy users
        userArrayList.add(new User(5, "Richard", "richie100"));
        userArrayList.add(new User(4, "Rita", "rita100"));
        userArrayList.add(new User(3, "Thomas", "tommy100"));
        userArrayList.add(new User(2, "Angelica", "angel100"));
        userArrayList.add(new User(1, "Great", "great100"));

        userArrayList.add(new User(5, "James", "jamie100"));
        userArrayList.add(new User(4, "Stephanie", "steph100"));
        userArrayList.add(new User(3, "Emeka", "emmy100"));
        userArrayList.add(new User(2, "Janet", "janie100"));
        userArrayList.add(new User(1, "Chinedu", "nedu100"));

        userArrayList.add(new User(5, "Tomiwa", "tommie100"));
        userArrayList.add(new User(4, "Mmesoma", "mme100"));
        userArrayList.add(new User(3, "Emmanuel", "emmanuel100"));
        userArrayList.add(new User(2, "Gabriella", "gabby100"));
        userArrayList.add(new User(1, "Sopuru", "sopuru100"));



        //add Dummy levels
        levelsArrayList.add(new Levels(1, 1, "1500250", "1230000", "200000", "120000","100000"));
        levelsArrayList.add(new Levels(2, 2, "2200100", "2100000", "400000", "200000","250000"));
        levelsArrayList.add(new Levels(3, 3, "2750000", "2000100", "550000", "295000","470000"));
        levelsArrayList.add(new Levels(4, 4, "3500000", "3330350", "700000", "325000","650000"));
        levelsArrayList.add(new Levels(5, 5, "5000050", "4225725", "995000", "488000","930950"));

        //add Dummy Cards
        cardArrayList.add(new Card(1, "2435 8547 9485 8463", "Sales Dept", "546", "09/25"));
        cardArrayList.add(new Card(2, "5354 0968 3837 4342", "Human Res.", "321", "09/25"));
        cardArrayList.add(new Card(3, "7547 8746 9768 5242", "Creative Dept", "214", "09/25"));
        cardArrayList.add(new Card(4, "5354 8665 3621 3532", "Research Dept", "756", "09/25"));
        cardArrayList.add(new Card(5, "6334 9473 3273 3732", "Executive Dept", "546", "09/25"));

        SqlConnector.getInstance(this).insertUserList(userArrayList);
        SqlConnector.getInstance(this).insertLevelList(levelsArrayList);
        SqlConnector.getInstance(this).insertCardList(cardArrayList);


    }

    /** Since the transactions that would be shown
     * based on Levels, we'll decide what transactions should be show based on levels
     *
     */

    private void addDummyTransactions(){
        transactionDetails.add(new TransactionDetails(1, "packs of water", "1000", "Great", "900250", "Great tried to exceed the transaction limit.", "Friday 20 June, 2021", "09:09", 1));
        transactionDetails.add(new TransactionDetails(1, "desktop HP system", "4", "Chinedu", "99400", "Transaction successful", "Monday 23 June, 2021", "19:09", 0));
        transactionDetails.add(new TransactionDetails(1, "Printers", "4", "Sopuru", "90250", "Transaction was successful.", "Friday 25 June, 2021", "10:09", 0));
        transactionDetails.add(new TransactionDetails(1, "Printers", "5", "Sopuru", "80600", "Transaction was successful.", "Saturday 25 June, 2021", "10:09", 0));


        transactionDetails.add(new TransactionDetails(2, "packs of water", "1000", "Gabriella", "99400", "Transaction was successful", "Friday 20 June, 2021", "09:09", 0));
        transactionDetails.add(new TransactionDetails(2, "desktop HP system", "4", "Janet", "700", "Transaction successful", "Monday 23 June, 2021", "19:09", 0));

        transactionDetails.add(new TransactionDetails(3, "packs of water", "1000", "Emmanuel", "50000", "Transaction was successful", "Friday 20 June, 2021", "09:09", 0));
        transactionDetails.add(new TransactionDetails(3, "desktop HP system", "4", "Emeka", "45820", "Transaction successful", "Monday 23 June, 2021", "19:09", 0));
        transactionDetails.add(new TransactionDetails(3, "Printers", "4", "Thomas", "73830", "Transaction was successful.", "Friday 25 June, 2021", "10:09", 0));

        SqlConnector.getInstance(this).insertTransactionList(transactionDetails);
    }
}