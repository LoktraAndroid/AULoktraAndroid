package com.au.auloktra.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;

import com.au.auloktra.R;
import com.au.auloktra.base.BaseAUActivity;
import com.au.auloktra.databinding.ActivityLoginBinding;
import com.au.auloktra.utils.AuAppUtils;
import com.au.auloktra.utils.LogHelper;

/**
 * Login page
 */
public class LoginActivity extends BaseAUActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private ActivityLoginBinding binding;

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        init();
    }

    private void initBinding() {
        binding = bind(ActivityLoginBinding.class, R.layout.activity_login);
    }

    private void init() {
        binding.passwordShow.setOnClickListener(this);
        binding.login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.password_show:

                String buttonText = binding.passwordShow.getText().toString();

                // Check for button text and swap between show/hide
                if (buttonText.equals(getString(R.string.login_show_button))) {
                    LogHelper.logInfo(TAG, "Showing password");
                    binding.password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    binding.password.setSelection(binding.password.length());
                    binding.passwordShow.setText(R.string.login_hide_button);
                } else {
                    LogHelper.logInfo(TAG, "Hiding password");
                    binding.password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.password.setSelection(binding.password.length());
                    binding.passwordShow.setText(R.string.login_show_button);
                }
                break;

            case R.id.login:
                navigateToHome();
                break;

            case R.id.login_help:
                AuAppUtils.showToast(this, "Help clicked.");
                break;
        }
    }

    private void navigateToHome() {
        LogHelper.logInfo(TAG, "Navigating to home, terminating login activity");
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
