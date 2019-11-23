package com.futsalrecord.futsalinfosystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.model.Staffs;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StaffsAdapter extends RecyclerView.Adapter<StaffsAdapter.StaffsViewHolder> {
    private Context context;
    private List<Staffs> staffsList;

    public StaffsAdapter(Context context, List<Staffs> staffsList) {
        this.context = context;
        this.staffsList = staffsList;
    }

    @NonNull
    @Override
    public StaffsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staffview, parent, false);
        return new StaffsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffsViewHolder holder, int position) {
        Staffs staffs = staffsList.get(position);
        holder.staffProfilePic.setImageResource(staffs.getStaffImageId());
        holder.tvStaffFullName.setText(staffs.getStaffFullName());
        holder.tvStaffEmail.setText(staffs.getStaffEmail());
        holder.tvStaffPhoneNo.setText(staffs.getStaffPhoneNo());
        holder.tvStaffGender.setText(staffs.getStaffGender());
        holder.tvStaffAddress.setText(staffs.getStaffAddress());
    }

    @Override
    public int getItemCount() {
        return staffsList.size();
    }

    public class StaffsViewHolder extends RecyclerView.ViewHolder {
        CircleImageView staffProfilePic;
        TextView tvStaffFullName, tvStaffEmail, tvStaffPhoneNo, tvStaffGender, tvStaffAddress;

        public StaffsViewHolder(@NonNull View itemView) {
            super(itemView);
            staffProfilePic = itemView.findViewById(R.id.staffProfilePic);
            tvStaffFullName = itemView.findViewById(R.id.tvStaffFullName);
            tvStaffEmail = itemView.findViewById(R.id.tvStaffEmail);
            tvStaffPhoneNo = itemView.findViewById(R.id.tvStaffPhoneNo);
            tvStaffGender = itemView.findViewById(R.id.tvStaffGender);
            tvStaffAddress = itemView.findViewById(R.id.tvStaffAddress);
        }
    }
}
