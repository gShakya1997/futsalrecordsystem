package com.futsalrecord.futsalinfosystem.activities.futsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.adapter.CustomersAdapter;
import com.futsalrecord.futsalinfosystem.model.Customers;

import java.util.ArrayList;
import java.util.List;

public class FutsalCustomerDataActivity extends AppCompatActivity {
    private RecyclerView customerRecyclerView;
    private Button btnAddCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_customer_data);
        initialize();
        List<Customers> customersList = new ArrayList<>();
//        customersList.add(new Customers("Gunjan Shakya", "gunjan.shakya3@gmail.com", "9860915753", "Male", "Kathmandu", ""));
//        customersList.add(new Customers("Yunish Shakya", "gunjan.shakya3@gmail.com", "9860915753", "Male", "Patan", ""));
        CustomersAdapter customersAdapter = new CustomersAdapter(this, customersList);
        customerRecyclerView.setAdapter(customersAdapter);
        customerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddCustomerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initialize() {
        customerRecyclerView = findViewById(R.id.customerRecyclerView);
        btnAddCustomer = findViewById(R.id.btnAddCustomer);
    }

}
