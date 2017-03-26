package com.example.user.simplervtest.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.simplervtest.helpers.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25.03.2017
 */

public class RecyclerBindingAdapter<T>
        extends RecyclerView.Adapter<RecyclerBindingAdapter.BindingHolder> {
    private int holderLayout, variableId;
    private List<T> items = new ArrayList<>();
    private OnItemClickListener<T> onItemClickListener;
    private boolean isSelectionOn = false;

    public RecyclerBindingAdapter(int holderLayout, int variableId, List<T> items, boolean isSelectionOn) {
        this.holderLayout = holderLayout;
        this.variableId = variableId;
        this.items = items;
        this.isSelectionOn = isSelectionOn;
    }

    public RecyclerBindingAdapter(int holderLayout, int variableId, List<T> items) {
        this.holderLayout = holderLayout;
        this.variableId = variableId;
        this.items = items;
    }

    @Override
    public RecyclerBindingAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(holderLayout, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerBindingAdapter.BindingHolder holder, int position) {
        final T item = items.get(position);
        if (isSelectionOn) {
            boolean isSelected = RecyclerSelectionController.INSTANCE.isSelected(position);
            holder.getBinding().getRoot().setSelected(isSelected);
        }

        holder.getBinding().getRoot().setOnClickListener(v -> {
            if (isSelectionOn) {
                v.setSelected(!v.isSelected());
                RecyclerSelectionController.INSTANCE.changeItemSelectionState(position);
            }
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(position, item, v);
        });
        holder.getBinding().setVariable(variableId, item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void remove(int position) {
        T item = items.get(position);
        if (items.contains(item)) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(int position, T item, View imageView);
    }

    static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        ViewDataBinding getBinding() {
            return binding;
        }
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String internetUrl) {
        Utils.loadImageIntoView(imageView.getContext(), imageView, internetUrl);
    }
}