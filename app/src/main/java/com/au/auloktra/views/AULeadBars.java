package com.au.auloktra.views;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.au.auloktra.R;
import com.au.auloktra.databinding.AuPercentageBarsBinding;
import com.au.auloktra.utils.LogHelper;

/**
 * Cross sell leads bar graph.
 */
public class AULeadBars extends FrameLayout {

    private static final String TAG = AULeadBars.class.getSimpleName();

    AuPercentageBarsBinding binding;

    public AULeadBars(@NonNull Context context) {
        super(context);
        init();
    }

    public AULeadBars(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AULeadBars(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AULeadBars(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LogHelper.logInfo(TAG, "Inflating percentage bar layout");
        binding = DataBindingUtil.bind(View.inflate(getContext(), R.layout.au_percentage_bars, null));
        addView(binding.getRoot());
    }

    public void setData() {
        post(new Runnable() {
            @Override
            public void run() {

                // TODO : Dummy data
                // Set labels
                String rejectedString = getContext().getString(R.string.bars_rejected, "8", "40");
                binding.rejectedText.setText(rejectedString);
                String notStartedString = getContext().getString(R.string.bars_not_started, "4", "20");
                binding.notStartedText.setText(notStartedString);
                String pendingString = getContext().getString(R.string.bars_pending, "6", "30");
                binding.pendingText.setText(pendingString);
                String completedString = getContext().getString(R.string.bars_completed, "4", "20");
                binding.completedText.setText(completedString);

                int width = binding.element.getWidth();
                int height = binding.completedPercentage.getHeight();
                LogHelper.logInfo(TAG, "Max width for displaying percentage is " + width + " height is " + height);

                binding.filler1.setVisibility(View.VISIBLE);
                binding.filler2.setVisibility(View.VISIBLE);
                binding.filler3.setVisibility(View.VISIBLE);
                binding.filler4.setVisibility(View.VISIBLE);

                // Set percentage as width size
                binding.rejectedPercentage.setLayoutParams(new LinearLayout.LayoutParams((int) (0.4 * width), height));
                binding.notStartedPercentage.setLayoutParams(new LinearLayout.LayoutParams((int) (0.2 * width), height));
                binding.pendingPercentage.setLayoutParams(new LinearLayout.LayoutParams((int) (0.3 * width), height));
                binding.completedPercentage.setLayoutParams(new LinearLayout.LayoutParams((int) (0.4 * width), height));

                String totalString = getContext().getString(R.string.bars_total, "22");
                binding.totalText.setText(totalString);
            }
        });
    }
}
