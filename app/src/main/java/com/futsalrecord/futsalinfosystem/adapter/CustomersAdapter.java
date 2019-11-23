package com.futsalrecord.futsalinfosystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.model.Customers;

import java.util.List;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.CustomersViewHolder> {
    private Context context;
    private List<Customers> customersList;

    public CustomersAdapter(Context context, List<Customers> customersList) {
        this.context = context;
        this.customersList = customersList;
    }

    @NonNull
    @Override
    public CustomersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerview,parent,false);
        return new CustomersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomersViewHolder holder, int position) {
        Customers customers = customersList.get(position);
        holder.customerProfilePic.setImageResource(customers.getCustomerImageId());
        holder.tvCustomerFullName.setText(customers.getCustomerFullName());
        holder.tvCustomerEmail.setText(customers.getCustomerEmail());
        holder.tvCustomerPhoneNo.setText(customers.getCustomerPhoneNo());
        holder.tvCustomerGender.setText(customers.getCustomerGender());
        holder.tvCustomerAddress.setText(customers.getCustomerAddress());
    }

    @Override
    public int getItemCount() {
        return customersList.size();
    }

    public class CustomersViewHolder extends RecyclerView.ViewHolder {

        CircleImageView customerProfilePic;
        TextView tvCustomerFullName, tvCustomerEmail, tvCustomerPhoneNo, tvCustomerGender, tvCustomerAddress;

        public CustomersViewHolder(@NonNull View itemView) {
            super(itemView);
            customerProfilePic = itemView.findViewById(R.id.customerProfilePic);
            tvCustomerFullName = itemView.findViewById(R.id.tvCustomerFullName);
            tvCustomerEmail = itemView.findViewById(R.id.tvCustomerEmail);
            tvCustomerPhoneNo = itemView.findViewById(R.id.tvCustomerPhoneNo);
            tvCustomerGender = itemView.findViewById(R.id.tvCustomerGender);
            tvCustomerAddress = itemView.findViewById(R.id.tvCustomerAddress);
        }
    }
}
