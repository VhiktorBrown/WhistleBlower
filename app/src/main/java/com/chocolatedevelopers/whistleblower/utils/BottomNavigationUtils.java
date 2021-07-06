package com.chocolatedevelopers.whistleblower.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.chocolatedevelopers.whistleblower.MainActivity;
import com.chocolatedevelopers.whistleblower.R;
import com.chocolatedevelopers.whistleblower.accounts.AccountsActivity;
import com.chocolatedevelopers.whistleblower.alerts.AlertsActivity;
import com.chocolatedevelopers.whistleblower.verified.VerifiedActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationUtils {

    public static void enableBottomNavigation(Context context, BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    return true;
                }else if (itemId == R.id.accounts) {
                    Intent intent = new Intent(context, AccountsActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    return true;
                }else if (itemId == R.id.alerts) {
                    Intent intent = new Intent(context, AlertsActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    return true;
                }else if (itemId == R.id.verified) {
                    Intent intent = new Intent(context, VerifiedActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    return true;
                }
                return false;
            }
        });
    }
}
