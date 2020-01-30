package com.futsalrecord.futsalinfosystem.activities.futsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.adapter.CustomersAdapter;
import com.futsalrecord.futsalinfosystem.api.FutsalAPI;
import com.futsalrecord.futsalinfosystem.model.Customers;
import com.futsalrecord.futsalinfosystem.strictMode.StrictModeClass;
import com.futsalrecord.futsalinfosystem.url.Url;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FutsalCustomerDataActivity extends AppCompatActivity {
    private RecyclerView customerRecyclerView;
    private Button btnAddCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_customer_data);
        initialize();
        loadCustomerData();
        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCustomerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadCustomerData() {
        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        final Call<List<Customers>> customerCall = futsalAPI.getCustomersDetails();
        customerCall.enqueue(new Callback<List<Customers>>() {
            @Override
            public void onResponse(Call<List<Customers>> call, Response<List<Customers>> response) {
                List<Customers> customersList = response.body();
                customerRecyclerView.setAdapter(new CustomersAdapter(getApplicationContext(),customersList));
                customerRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Customers>> call, Throwable t) {

            }
        });
    }

    private void initialize() {
        customerRecyclerView = findViewById(R.id.customerRecyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        btnAddCustomer = findViewById(R.id.btnAddCustomer);
    }

}
