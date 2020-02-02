package com.futsalrecord.futsalinfosystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.model.Futsal;
import com.futsalrecord.futsalinfosystem.model.FutsalDetails;

import java.util.List;

public class FutsalAdapter extends RecyclerView.Adapter<FutsalAdapter.FutsalViewHolder> {
    private Context context;
    private List<FutsalDetails> futsalList;

    public FutsalAdapter(Context context, List<FutsalDetails> futsalList) {
        this.context = context;
        this.futsalList = futsalList;
    }

    @NonNull
    @Override
    public FutsalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.futsalview,parent,false);
        return new FutsalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FutsalViewHolder holder, int position) {
        final FutsalDetails futsal = futsalList.get(position);
        holder.tvFutsalName.setText(futsal.getFutsalName());
        holder.tvFutsalAddress.setText(futsal.getFutsalAddress());
        holder.tvFutsalPhone.setText(futsal.getFutsalPhone());
        holder.tvFutsalOpeningTime.setText(futsal.getFutsalOpeningTime());
        holder.tvFutsalClosingTime.setText(futsal.getFutsalClosingTime());
        holder.tvFutsalPrice.setText(futsal.getFutsalPrice());
    }

    @Override
    public int getItemCount() {
        return futsalList.size();
    }

    public class FutsalViewHolder extends RecyclerView.ViewHolder {
        TextView tvFutsalName, tvFutsalAddress, tvFutsalPhone, tvFutsalOpeningTime, tvFutsalClosingTime,
                tvFutsalPrice;

        public FutsalViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFutsalName = itemView.findViewById(R.id.tvFutsalName);
            tvFutsalAddress = itemView.findViewById(R.id.tvFutsalAddress);
            tvFutsalPhone = itemView.findViewById(R.id.tvFutsalPhone);
            tvFutsalOpeningTime = itemView.findViewById(R.id.tvFutsalOpeningTime);
            tvFutsalClosingTime = itemView.findViewById(R.id.tvFutsalClosingTime);
            tvFutsalPrice = itemView.findViewById(R.id.tvFutsalPrice);
        }
    }
}
