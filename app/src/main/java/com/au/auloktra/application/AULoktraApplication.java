package com.au.auloktra.application;

import android.app.Application;
import com.au.auloktra.utils.LogHelper;


/**
 * Base class for application
 */
public class AULoktraApplication extends Application {

    public static final String TAG = AULoktraApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        LogHelper.logInfo(TAG, "Initializing application");
        initGlobalAppComponents();
    }

    private void initGlobalAppComponents() {
        // Intentionally called so instance is initiated at app startup
        //OkHttpClient.init(this);

    }

}
