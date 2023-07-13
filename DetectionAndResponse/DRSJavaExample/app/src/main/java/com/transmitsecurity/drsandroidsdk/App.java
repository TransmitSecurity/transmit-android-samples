package com.transmitsecurity.drsandroidsdk;

import android.app.Application;

import com.transmit.accountprotection.TSAccountProtection;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TSAccountProtection.init(this, "https://collect.riskid.security/device/", "CLIENT_ID");
    }
}
