package com.au.auloktra.adapters;

import android.support.annotation.NonNull;

import com.au.auloktra.R;
import com.au.auloktra.base.BaseAdapter;
import com.au.auloktra.models.CrossLeadItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter responsible for showing the cross lead data.
 */
public class CrossLeadAdapter extends BaseAdapter<CrossLeadItem> {

    private List<CrossLeadItem> list = new ArrayList<>();

    public CrossLeadAdapter(@NonNull OnItemClickListener<CrossLeadItem> itemClickListener) {
        super(itemClickListener);
    }

    public CrossLeadAdapter(@NonNull OnItemClickListener<CrossLeadItem> itemClickListener, @NonNull List<CrossLeadItem> list) {
        super(itemClickListener);
        this.list.addAll(list);
    }

    @Override
    protected CrossLeadItem getItemForPosition(int position) {
        return list.get(position);
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.cross_sell_item;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
