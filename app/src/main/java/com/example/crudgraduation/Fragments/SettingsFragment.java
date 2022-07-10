package com.example.crudgraduation.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.crudgraduation.Activity.AddMerchantActivity;
import com.example.crudgraduation.Activity.MainActivity;
import com.example.crudgraduation.Activity.PolicyActivity;
import com.example.crudgraduation.R;

import java.util.Locale;
import java.util.Objects;

public class SettingsFragment extends PreferenceFragmentCompat {
    Boolean languageChanged = false;
    public final static String TAG;

    static {
        TAG = SettingsFragment.class.getCanonicalName();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        PackageInfo pInfo = null;
        try {
            pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Version name: "+pInfo.versionName);
        Preference myPref = (Preference) findPreference("version");
        assert myPref != null;
        myPref.setSummary(pInfo.versionName);
        ListPreference listPreference = (ListPreference) findPreference("language");
        if(listPreference.getValue()==null) {
            // to ensure we don't get a null value
            // set first value by default
            listPreference.setValueIndex(0);

        }
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                System.out.println(newValue.toString());
                if (newValue.toString().equals("Англиски") || newValue.toString().equals("English") ){
                    setLocale("en");
                    listPreference.setValueIndex(0);
                    checkLanguage(languageChanged);
                }else{
                    setLocale("mk");
                    listPreference.setValueIndex(1);
                    checkLanguage(languageChanged);
                }

                return true;
            }
        });
        setUp();
    }
    public void setUp(){
        loadLocale();
        privacyPolicy();
//        supportCall();
        supportEmail();
    }
    public void privacyPolicy(){
        Preference myPref = (Preference) findPreference("privacy_policy");
        myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(getActivity(), PolicyActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }
//    public void supportCall(){
//        Preference myPref = (Preference) findPreference("call");
//        myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            public boolean onPreferenceClick(Preference preference) {
//
//                Intent callIntent = new Intent(Intent.ACTION_DIAL);
//                callIntent.setData(Uri.parse("tel:+38978808845"));
//                startActivity(callIntent);
//
//                return true;
//            }
//        });
//    }
    public void supportEmail(){
        Preference myPref = (Preference) findPreference("email");
        myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setPackage("com.google.android.gm");
                String to = "walletlessapp@gmail.com";
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});

                //need this to prompts email client only
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
                return true;
            }
        });
    }

    private void checkLanguage(Boolean languageChanged) {
        if (languageChanged == true){
            //go to mainActivity refresh
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getContext().getResources().updateConfiguration(config,getContext().getResources().getDisplayMetrics());
        //save data to shared preferences
        SharedPreferences.Editor editor = Objects.requireNonNull(getActivity()).getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_lang", lang);
        editor.apply();
        languageChanged = true;

    }

    //load language saved in shared preferences
    public void loadLocale(){
        SharedPreferences prefs = Objects.requireNonNull(getActivity()).getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("My_lang","");
        setLocale(language);
    }
}