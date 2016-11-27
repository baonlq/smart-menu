package com.galaxydesigners.bean.smartmenu;

import android.app.Application;
import android.util.Log;

import com.neumob.api.Neumob;
import com.parse.Parse;
import com.parse.ParseInstallation;

public class ApplicationInitialize extends Application {

    private static final String TAG = "SF_Log";

    @Override
    public void onCreate() {
        super.onCreate();

        Neumob.initialize(getApplicationContext(),getResources().getString(R.string.NEUMOB_CLIENT_KEY), new Runnable() {
            @Override
            public void run() {
                if (Neumob.isInitialized()) {
                    // Neumob is ON.
                    boolean isAccelerated = Neumob.isAccelerated();
                    Log.d(TAG, "Neumob is ON.");
                } else {
                    // Neumob is OFF. Change log settings for more details.
                    Log.d(TAG, "Neumob is OFF.");
                }
            }
        });

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getResources().getString(R.string.PARSE_APPLICATION_ID))
                .clientKey(getResources().getString(R.string.PARSE_CLIENT_KEY))
                .server("https://parseapi.back4app.com")
                .build()
        );

        // save InstallationID to parse server
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.saveInBackground();
    }
}
