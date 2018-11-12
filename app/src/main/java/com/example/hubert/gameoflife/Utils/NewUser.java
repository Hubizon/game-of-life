package com.example.hubert.gameoflife.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hubert.gameoflife.R;

public class NewUser {

    public final void createUser(Context context, String name, int avatarIcon, boolean isMale) {
        SharedPreferences mainSharedPreferences =
                context.getSharedPreferences(context.getString(R.string.shared_preferences_key),
                        Context.MODE_PRIVATE);

        int numberofUsers =
                mainSharedPreferences.getInt(context.getString(R.string.saved_number_of_users),
                        SharedPreferencesDefaultValues.DefaultNumberOfUsers);

        SharedPreferences.Editor mainEditor = mainSharedPreferences.edit();
        mainEditor.putInt(context.getString(R.string.saved_number_of_users),
                numberofUsers + 1);
        mainEditor.putInt(context.getString(R.string.saved_current_user), numberofUsers);
        mainEditor.apply();

        // zaczynamy od user0
        String user_key = context.getString(R.string.shared_preferences_user_key)
                + numberofUsers;
        SharedPreferences userSharedPreferences =
                context.getSharedPreferences(user_key, Context.MODE_PRIVATE);
        SharedPreferences.Editor userEdit = userSharedPreferences.edit();
        userEdit.putString(context.getString(R.string.saved_character_name_key), name);
        userEdit.putInt(context.getString(R.string.saved_character_icon_key), avatarIcon);
        userEdit.putBoolean(context.getString(R.string.saved_sex_key), isMale);
        userEdit.apply();
    }
}
