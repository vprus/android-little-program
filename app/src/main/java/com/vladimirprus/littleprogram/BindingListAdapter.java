package com.vladimirprus.littleprogram;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BindingListAdapter<T> extends RecyclerView.Adapter<BindingListAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(ViewDataBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding binding;
    }

    public BindingListAdapter(ObservableList<T> model, int itemLayout, int variableId)
    {
        this.model = model;
        this.layoutId = itemLayout;
        this.variableId = variableId;
    }

    void setOnClickListener(ClickListener<T> listener)
    {
        this.onClick = listener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        View v = holder.binding.getRoot();
        holder.binding.setVariable(variableId, model.get(position));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BindingListAdapter.this.onClick.onClick(model.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    private final ObservableList<T> model;
    private int layoutId;
    private int variableId;

    private ClickListener<T> onClick;
}
