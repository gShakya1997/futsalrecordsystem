package com.futsalrecord.futsalinfosystem.activities.futsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.adapter.CustomersAdapter;
import com.futsalrecord.futsalinfosystem.model.Customers;

import java.util.ArrayList;
import java.util.List;

public class FutsalCustomerDataActivity extends AppCompatActivity {
    private RecyclerView customerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_customer_data);
        binding();
        List<Customers> customersList = new ArrayList<>();
        customersList.add(new Customers("Gunjan Shakya","gunjan.shakya3@gmail.com","9860915753","Male","Kathmandu",R.drawable.noimage));
        customersList.add(new Customers("Yunish Shakya","gunjan.shakya3@gmail.com","9860915753","Male","Patan",R.drawable.noimage));
        CustomersAdapter customersAdapter = new CustomersAdapter(this,customersList);
        customerRecyclerView.setAdapter(customersAdapter);
        customerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void binding() {
        customerRecyclerView= findViewById(R.id.customerRecyclerView);
    }

}
