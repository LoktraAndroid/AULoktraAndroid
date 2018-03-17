package com.au.auloktra.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.au.auloktra.R;
import com.au.auloktra.utils.LogHelper;

/**
 * Base class for common functionality
 */
public abstract class BaseAUActivity extends AppCompatActivity {

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        LogHelper.logInfo(getTag(), "onCreate");
    }


    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        LogHelper.logInfo(getTag(), "onResume");
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
        LogHelper.logInfo(getTag(), "onPause");
    }

    public <T> T bind(Class<T> clazz, @LayoutRes int layoutId) {

        try {
            return clazz.cast(DataBindingUtil.setContentView(this, layoutId));
        } catch (ClassCastException e) {
            throw new RuntimeException("Mismatch with binding class type");
        }
    }

    /**
     * To animate new activities.
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        setDefaultAnimations();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (enableAnimations()) {
            overridePendingTransition(0, useHorizontalAnimations() ? R.anim.slide_out_from_right : R.anim.slide_down);
        }
    }

    protected void setDefaultAnimations() {
        if (enableAnimations()) {
            overridePendingTransition(useHorizontalAnimations() ? R.anim.slide_in_from_right : R.anim.slide_up, R.anim.hold);
        }
    }

    /**
     * Change default window transition swipe behaviour in horizontal manner if default
     * animations are enabled.
     */
    protected boolean useHorizontalAnimations() {
        return false;
    }

    /**
     * Sets up default activity transition animation slide up on enter and slide down
     * on exit depending on return value.
     */
    protected boolean enableAnimations() {
        return true;
    }


    public abstract String getTag();

}
