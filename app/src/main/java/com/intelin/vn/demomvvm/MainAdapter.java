package com.intelin.vn.demomvvm;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 27/03/2019
 * Time: 9:36 AM
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.Holder> {

    private List<String> strings = new ArrayList<>();
    private OnItemClickListener listener;
    private int total;

    public MainAdapter() {
        for (int i = 0; i <= 100; i++) {
            strings.add(String.valueOf(i));
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tvNum.setText(strings.get(position));
        holder.setOnClick(listener, holder);
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public void removeItem(int position) {
        strings.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, strings.size());
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvNum;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.tvNum);
        }

        public void setOnClick(OnItemClickListener onClick, Holder holder) {
            itemView.setOnClickListener(v -> onClick.onClickListener(holder));
        }
    }

    public interface OnItemClickListener {
        void onClickListener(Holder view);
    }

    public int totalSelected() {
        return total;
    }
}
