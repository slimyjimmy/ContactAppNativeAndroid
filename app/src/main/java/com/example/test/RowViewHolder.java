package com.example.test;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private RecyclerViewClickListener mListener;

    RowViewHolder(View view, RecyclerViewClickListener listener) {
        super(view);
        mListener = listener;
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mListener.recyclerViewListClicked(view, getAdapterPosition());
    }
}
