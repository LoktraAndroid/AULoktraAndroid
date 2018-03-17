package com.au.auloktra.ui;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;

import com.au.auloktra.R;
import com.au.auloktra.base.BaseAUActivity;
import com.au.auloktra.databinding.ActivityHomeBinding;
import com.au.auloktra.utils.AuAppUtils;
import com.au.auloktra.utils.LogHelper;

public class HomeActivity extends BaseAUActivity implements View.OnClickListener {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        setContentTitles();
        initMenu();
        init();
    }

    private void initBinding() {
        binding = bind(ActivityHomeBinding.class, R.layout.activity_home);
    }

    private void initMenu() {
        LogHelper.logInfo(TAG, "Initializing home menu");
        binding.toolbar.homeMenu.setOnClickListener(this);
        binding.menu.logout.setOnClickListener(this);
        binding.menu.profile.setOnClickListener(this);
        binding.menu.sync.setOnClickListener(this);
    }

    private void init() {
        binding.fab.setOnClickListener(this);
        binding.content.crossSellCard.setOnClickListener(this);
        binding.content.barChart.setData();
    }

    private void setContentTitles() {
        binding.content.leadsAssignedCard.title.setText(R.string.leads_assigned);
        binding.content.leaderboardCard.title.setText(R.string.leaderboard_title);
        binding.content.announcementsCard.title.setText(R.string.announcements_title);

        //binding.content.leadsAssignedCard.subtitle.setText(R.string.leads_assigned);
        binding.content.leaderboardCard.subtitle.setText(R.string.leaderboard_sub);
        binding.content.announcementsCard.subtitle.setText(R.string.announcements_sub);

        binding.content.leadsAssignedCard.icon.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.assigned_leads_icon)
        );
        binding.content.leaderboardCard.icon.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.leaderboard_icon)
        );
        binding.content.announcementsCard.icon.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.announcements_icon)
        );

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Rect viewRect = new Rect();
        binding.menu.root.getGlobalVisibleRect(viewRect);

        // Close menu when touch is detected outside the menu view
        if (binding.menu.root.getVisibility() == View.VISIBLE
                && !viewRect.contains((int) ev.getRawX(), (int) ev.getRawY())) {
            binding.menu.root.setVisibility(View.GONE);
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.home_menu:
                if (binding.menu.root.getVisibility() == View.GONE) {
                    LogHelper.logInfo(TAG, "Showing menu");
                    binding.menu.root.setVisibility(View.VISIBLE);
                } else {
                    LogHelper.logInfo(TAG, "Hiding menu");
                    binding.menu.root.setVisibility(View.GONE);
                }
                break;
            case R.id.logout:
                binding.menu.root.setVisibility(View.GONE);
                AuAppUtils.showToast(this, "Logout clicked");
                break;
            case R.id.sync:
                binding.menu.root.setVisibility(View.GONE);
                AuAppUtils.showToast(this, "Sync clicked");
                break;
            case R.id.profile:
                binding.menu.root.setVisibility(View.GONE);
                AuAppUtils.showToast(this, "Profile clicked");
                break;
            case R.id.cross_sell_card:
                navigateToCrossSellLeads();
                break;

        }
    }

    private void navigateToCrossSellLeads() {
        Intent intent = new Intent(this, CrossLeadsActivity.class);
        startActivity(intent);
    }
}
