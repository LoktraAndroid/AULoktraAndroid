package com.au.auloktra.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.au.auloktra.R;
import com.au.auloktra.adapters.CrossLeadAdapter;
import com.au.auloktra.base.BaseAUActivity;
import com.au.auloktra.base.BaseAdapter;
import com.au.auloktra.databinding.ActivityCrossLeadsBinding;
import com.au.auloktra.models.CrossLeadItem;
import com.au.auloktra.utils.AuAppUtils;

/**
 * Cross sell leads list activity
 */
public class CrossLeadsActivity extends BaseAUActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private ActivityCrossLeadsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        initAdapter();
    }

    private void initBinding() {
        binding = bind(ActivityCrossLeadsBinding.class, R.layout.activity_cross_leads);
    }

    private void initAdapter() {
        CrossLeadAdapter adapter = new CrossLeadAdapter(new BaseAdapter.OnItemClickListener<CrossLeadItem>() {
            @Override
            public void onItemClick(CrossLeadItem item) {
                AuAppUtils.showToast(CrossLeadsActivity.this, "Item clicked");
            }
        }, AuAppUtils.getDummyLeadsList());

        binding.leadsList.setLayoutManager(new LinearLayoutManager(this));
        binding.leadsList.setAdapter(adapter);
    }

    @Override
    public String getTag() {
        return TAG;
    }
}
