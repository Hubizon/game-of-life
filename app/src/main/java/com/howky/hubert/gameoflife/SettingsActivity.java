package com.howky.hubert.gameoflife;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import androidx.preference.ListPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatActivity {

    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    public static final String DARK_SWITCH_KEY = "dark_mode_switch";
    private static final String NAME_EDIT_KEY = "name_edit";
    private static final String GOOD_NAME_REGEX = "^.{3,10}$";

//    private static final Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
//        @Override
//        public boolean onPreferenceChange(Preference preference, Object value) {
//            String stringValue = value.toString();
//
//            if (preference instanceof EditTextPreference && preference.getKey().equals("name_edit")) {
//
////                Pattern p = Pattern.compile(GOOD_NAME_REGEX);
////                Matcher m = p.matcher(stringValue);
////                if (m.find()) {
////                    SharedPreferences userSharedPref = MyApplication.userSharedPref;
////                    userSharedPref.edit().putString(preference.getContext().getString(R.string.saved_character_name_key), stringValue).apply();
////
////                    preference.setSummary(stringValue);
////                } else {
////                    Toast.makeText(preference.getContext(),
////                            "Your name should have 3-10 characters!",
////                            Toast.LENGTH_LONG).show();
////                }
//            } else {
//                preference.setSummary(stringValue);
//            }
//            return true;
//        }
//    };


    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * see #sBindPreferenceSummaryToValueListener
     */
//    private static void bindPreferenceSummaryToValue(Preference preference) {
//        // Set the listener to watch for value changes.
//        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);
//
//        // Trigger the listener immediately with the preference's
//        // current value.
//        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
//                PreferenceManager
//                        .getDefaultSharedPreferences(preference.getContext())
//                        .getString(preference.getKey(), ""));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new GeneralPreferenceFragment())
                .commit();

        setupActionBar();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    /**
     * This fragment shows general preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    public static class GeneralPreferenceFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle bundle, String s) {
            setPreferencesFromResource(R.xml.pref_general, s);

            // Dark mode preference
            Preference switchPref = findPreference(DARK_SWITCH_KEY);
            switchPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    boolean darkThemeOn = (boolean) o;

                    if (darkThemeOn) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }

                    getActivity().recreate();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
                }
            });

            // Display name preference
            final Preference editNamePref = findPreference(NAME_EDIT_KEY);
            String name = MyApplication.userSharedPref.getString(getString(R.string.saved_character_name_key), getString(R.string.pref_default_display_name));
            editNamePref.setSummary(name);
            editNamePref.setDefaultValue(name);
            //bindPreferenceSummaryToValue(editNamePref);
            editNamePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    String stringValue = o.toString();
                    Pattern p = Pattern.compile(GOOD_NAME_REGEX);
                    Matcher m = p.matcher(stringValue);
                    if (m.find()) {
                        SharedPreferences userSharedPref = MyApplication.userSharedPref;
                        userSharedPref.edit().putString(preference.getContext().getString(R.string.saved_character_name_key), stringValue).apply();

                        preference.setSummary(stringValue);
                    } else {

                        Toast.makeText(preference.getContext(),
                                preference.getContext().getString(R.string.toast_wrong_name),
                                Toast.LENGTH_LONG).show();
                    }
                    return true;
                }
            });

            //Hard Reset preference
            Preference hardReset = findPreference("reset_key");
            hardReset.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    if (o.toString().equals("YES")) {
                        if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
                            ((ActivityManager)getContext().getSystemService(ACTIVITY_SERVICE))
                                    .clearApplicationUserData();
                        }
                        else {
                            try {
                                String packageName = getContext().getPackageName();
                                Runtime runtime = Runtime.getRuntime();
                                runtime.exec("pm clear "+packageName);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return true;
                }
            });

            // Credits preference
            Preference credits = findPreference("creditsButton");
            credits.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent(getContext(), Credits.class);
                    startActivity(intent);
                    return true;
                }
            });

            // Feedback preference
            Preference feedback = findPreference("feedbackButton");
            feedback.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    final Intent _Intent = new Intent(android.content.Intent.ACTION_SEND);
                    _Intent.setType("text/html");
                    _Intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{getString(R.string.mail_feedback_email)});
                    _Intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.mail_feedback_subject));
                    _Intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.mail_feedback_message));
                    startActivity(Intent.createChooser(_Intent, getString(R.string.title_send_feedback)));
                    return true;
                }
            });

            // Version Preference
            Preference version = findPreference((getString(R.string.pref_key_version)));
            version.setSummary(BuildConfig.VERSION_NAME);
        }
    }



}
