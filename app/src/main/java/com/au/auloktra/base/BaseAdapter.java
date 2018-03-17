package com.au.auloktra.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.au.auloktra.BR;

/**
 * Generic recyclerview adapter
 */
public abstract class BaseAdapter<T>
        extends RecyclerView.Adapter<BaseAdapter<T>.MyViewHolder> {

    public BaseAdapter(OnItemClickListener<T> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T item);
    }
    private final OnItemClickListener<T> itemClickListener;

    @NonNull
    public BaseAdapter<T>.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                          int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new BaseAdapter<T>.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BaseAdapter<T>.MyViewHolder holder,
                                 int position) {
        final T item = getItemForPosition(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(item);
            }
        });

        holder.bind(item);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    protected abstract T getItemForPosition(int position);

    @LayoutRes
    protected abstract int getLayoutIdForPosition(int position);

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(T item) {
            binding.setVariable(BR.obj, item);
            binding.executePendingBindings();
        }
    }
}
