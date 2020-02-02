package com.futsalrecord.futsalinfosystem.adapter;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.futsal.CustomerDetailActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalCustomerDataActivity;
import com.futsalrecord.futsalinfosystem.api.FutsalAPI;
import com.futsalrecord.futsalinfosystem.createChannel.CreateNotificationChannel;
import com.futsalrecord.futsalinfosystem.model.Customers;
import com.futsalrecord.futsalinfosystem.model.CustomersUD;
import com.futsalrecord.futsalinfosystem.url.Url;

import java.util.List;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.CustomersViewHolder> {
    private Context context;
    private List<CustomersUD> customersList;
    private NotificationManagerCompat notificationManagerCompat;

    public CustomersAdapter(Context context, List<CustomersUD> customersList) {
        this.context = context;
        this.customersList = customersList;
    }

    @NonNull
    @Override
    public CustomersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerview, parent, false);
        return new CustomersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomersViewHolder holder, int position) {
        notificationManagerCompat = NotificationManagerCompat.from(context);
        CreateNotificationChannel createNotificationChannel = new CreateNotificationChannel(context);
        createNotificationChannel.createChannel();


        final CustomersUD customers = customersList.get(position);
        holder.tvCustomerId.setText(customers.get_id());
        holder.tvCustomerFullName.setText(customers.getCustomerFullname());
        holder.tvCustomerEmail.setText(customers.getCustomerEmail());
        holder.tvCustomerPhoneNo.setText(customers.getCustomerPhoneNo());
        holder.tvCustomerGender.setText(customers.getCustomerGender());
        holder.tvCustomerAddress.setText(customers.getCustomerAddress());


        holder.imgBtnCustomerEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CustomerDetailActivity.class);
                intent.putExtra("tvCustomerId", customers.get_id());
                intent.putExtra("tvCustomerFullName", customers.getCustomerFullname());
                intent.putExtra("tvCustomerEmail", customers.getCustomerEmail());
                intent.putExtra("tvCustomerPhoneNo", customers.getCustomerPhoneNo());
                intent.putExtra("tvCustomerGender", customers.getCustomerGender());
                intent.putExtra("tvCustomerAddress", customers.getCustomerAddress());
                context.startActivity(intent);
            }
        });
        holder.imgBtnCustomerDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
                Call<Void> customerCall = futsalAPI.deleteCustomerDetail(
                        Url.token,
                        customers.get_id()
                );

                customerCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(context, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        displayNotification();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(context, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return customersList.size();
    }

    private void displayNotification() {
        Notification notification = new NotificationCompat.Builder
                (context, CreateNotificationChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_insert_comment_black_24dp)
                .setContentTitle("Customer data deleted")
                .setContentText("Click refresh bottom on top right")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    public class CustomersViewHolder extends RecyclerView.ViewHolder {

        TextView tvCustomerFullName, tvCustomerEmail, tvCustomerPhoneNo, tvCustomerGender,
                tvCustomerAddress, tvCustomerId;
        ImageButton imgBtnCustomerDelete, imgBtnCustomerEdit;

        public CustomersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustomerId = itemView.findViewById(R.id.tvCustomerId);
            tvCustomerFullName = itemView.findViewById(R.id.tvCustomerFullName);
            tvCustomerEmail = itemView.findViewById(R.id.tvCustomerEmail);
            tvCustomerPhoneNo = itemView.findViewById(R.id.tvCustomerPhoneNo);
            tvCustomerGender = itemView.findViewById(R.id.tvCustomerGender);
            tvCustomerAddress = itemView.findViewById(R.id.tvCustomerAddress);
            imgBtnCustomerDelete = itemView.findViewById(R.id.imgBtnCustomerDelete);
            imgBtnCustomerEdit = itemView.findViewById(R.id.imgBtnCustomerEdit);
        }
    }
}
