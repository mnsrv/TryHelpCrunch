package com.tryhelpcrunch.modules.RNHelpCrunch;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.helpcrunch.library.core.Callback;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.repository.models.user.HCUser;

import org.jetbrains.annotations.NotNull;

public class RNHelpCrunchModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "RNHelpCrunch";
    public static final String TAG = "HelpCrunch";

    RNHelpCrunchModule(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void initWithConfiguration(ReadableMap config, ReadableMap options, Promise promise) {
        try {
            if (options.hasKey("userId") && options.getString("userId").length() > 0) {
                String userId = options.getString("userId");
                ReadableMap customData = options.getMap("customData");

                HCUser user = new HCUser.Builder()
                        .withUserId(userId).build();

                if (options.hasKey("userEmail") && options.getString("userEmail").length() > 0) {
                    String userEmail = options.getString("userEmail");
                    user.setEmail(userEmail);
                    user.setName(userEmail);
                }
                if (customData != null) {
                    user.setCustomData(customData.toHashMap());
                }
                HelpCrunch.updateUser(user);
                Log.i(TAG, "updateUser");
                promise.resolve(null);
            } else {
                Log.e(TAG, "updateUser ERROR You must supply userId");
                promise.reject("You must supply userId");
            }
        } catch (Exception e) {
            Log.e(TAG, "updateUser");
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void logout(Promise promise) {
        HelpCrunch.logout(new Callback<Object>(){
            @Override
            public void onSuccess(Object result) {
                Log.i(TAG, "logout");
                promise.resolve(null);
            }

            @Override
            public void onError(@NotNull String message) {
                Log.e(TAG, message);
                promise.reject(message.toString());
            }
        });
    }

    @ReactMethod
    public void show(Promise promise) {
        try {
            HelpCrunch.showChatScreen(getCurrentActivity());
            promise.resolve(null);
        } catch (Exception e) {
            Log.e(TAG, "HelpCrunch not initialized");
            promise.reject(e.toString());
        }
    }
}
