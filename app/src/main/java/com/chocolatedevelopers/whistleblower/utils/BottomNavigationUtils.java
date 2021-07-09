package com.chocolatedevelopers.whistleblower.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.chocolatedevelopers.whistleblower.MainActivity;
import com.chocolatedevelopers.whistleblower.R;
import com.chocolatedevelopers.whistleblower.flagged_transaction.FlaggedActivity;
import com.chocolatedevelopers.whistleblower.notification.NotificationActivity;
import com.chocolatedevelopers.whistleblower.report.ReportActivity;
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
                }else if (itemId == R.id.flagged) {
                    Intent intent = new Intent(context, FlaggedActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    return true;
                }else if (itemId == R.id.notifications) {
                    Intent intent = new Intent(context, NotificationActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    return true;
                }else if (itemId == R.id.reports) {
                    Intent intent = new Intent(context, ReportActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    return true;
                }
                return false;
            }
        });
    }
}
