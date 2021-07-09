package com.chocolatedevelopers.whistleblower.data.local;

import android.content.SharedPreferences;

import com.chocolatedevelopers.whistleblower.WhistleApplication;
import com.chocolatedevelopers.whistleblower.data.model.User;

import static com.chocolatedevelopers.whistleblower.utils.Constants.LEVEL_ID;
import static com.chocolatedevelopers.whistleblower.utils.Constants.PASSWORD;
import static com.chocolatedevelopers.whistleblower.utils.Constants.USERNAME;
import static com.chocolatedevelopers.whistleblower.utils.Constants.USER_ID;


public class SharedPref {

    private static SharedPref instance;
    SharedPreferences.Editor editor;

    public static SharedPref getInstance() {
        if (instance == null) {
            instance = new SharedPref();
        }
        return instance;
    }

    public void saveCurrentlySignedUser(User user) {
        editor = WhistleApplication.getSharedPref().edit();
        editor.putString(USERNAME, user.getUsername());
        editor.putString(PASSWORD, user.getPassword());
        editor.putInt(LEVEL_ID, user.getLevelId());
        editor.putInt(USER_ID, user.getUserId());
        editor.apply();
    }

    public User getCurrentlySignedInUser() {
        String username = WhistleApplication.getSharedPref().getString(USERNAME, "");
        String password = WhistleApplication.getSharedPref().getString(PASSWORD, "");
        int levelId = WhistleApplication.getSharedPref().getInt(LEVEL_ID, 0);
        int userId = WhistleApplication.getSharedPref().getInt(USER_ID, 0);

        return new User(userId, levelId, username, password);
    }

}
