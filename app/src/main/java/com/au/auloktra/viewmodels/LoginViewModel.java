package com.au.auloktra.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.au.auloktra.api.Resource;
import com.au.auloktra.api.response.LoginResponse;

/**
 * Login view model
 */
public class LoginViewModel extends ViewModel {

    public LiveData<Resource<LoginResponse>> loginUser(@NonNull String email, @NonNull String password) {
        return null;
    }
}
