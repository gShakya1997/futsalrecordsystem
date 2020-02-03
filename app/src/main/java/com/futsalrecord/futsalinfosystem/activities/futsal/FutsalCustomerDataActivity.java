package com.futsalrecord.futsalinfosystem.activities.futsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.FocusFinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.FutsalDashboard;
import com.futsalrecord.futsalinfosystem.adapter.CustomersAdapter;
import com.futsalrecord.futsalinfosystem.api.FutsalAPI;
import com.futsalrecord.futsalinfosystem.model.Customers;
import com.futsalrecord.futsalinfosystem.model.CustomersUD;
import com.futsalrecord.futsalinfosystem.strictMode.StrictModeClass;
import com.futsalrecord.futsalinfosystem.url.Url;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FutsalCustomerDataActivity extends AppCompatActivity {
    private RecyclerView customerRecyclerView;
    private Button btnAddCustomer;
    private ImageButton imgBtnRefresh, imgBtnGoTo;
    private EditText etSearchCustomer;

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
        imgBtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
        imgBtnGoTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FutsalCustomerDataActivity.this, CustomerSearchActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void loadCustomerData() {
        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        Call<List<CustomersUD>> customerCall = futsalAPI.getCustomersDetails(Url.token);

        customerCall.enqueue(new Callback<List<CustomersUD>>() {
            @Override
            public void onResponse(Call<List<CustomersUD>> call, Response<List<CustomersUD>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(FutsalCustomerDataActivity.this, "Code " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                List<CustomersUD> customersList = response.body();
                CustomersAdapter customersAdapter = new CustomersAdapter(FutsalCustomerDataActivity.this,
                        customersList);
                customersAdapter.notifyDataSetChanged();
                customerRecyclerView.setAdapter(customersAdapter);
                customerRecyclerView.setLayoutManager(new LinearLayoutManager(FutsalCustomerDataActivity.this));
            }

            @Override
            public void onFailure(Call<List<CustomersUD>> call, Throwable t) {
                Toast.makeText(FutsalCustomerDataActivity.this, "Error " + t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, FutsalDashboard.class);
        startActivity(intent);
        super.onBackPressed();
    }

    private void initialize() {
        customerRecyclerView = findViewById(R.id.customerRecyclerView);
        btnAddCustomer = findViewById(R.id.btnAddCustomer);
        imgBtnRefresh = findViewById(R.id.imgBtnRefresh);
        imgBtnGoTo = findViewById(R.id.imgBtnGoTo);
        etSearchCustomer = findViewById(R.id.etSearchCustomer);
    }

}
