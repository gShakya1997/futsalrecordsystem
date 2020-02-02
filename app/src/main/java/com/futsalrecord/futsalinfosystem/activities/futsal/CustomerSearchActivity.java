package com.futsalrecord.futsalinfosystem.activities.futsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.adapter.CustomersAdapter;
import com.futsalrecord.futsalinfosystem.api.FutsalAPI;
import com.futsalrecord.futsalinfosystem.model.CustomersUD;
import com.futsalrecord.futsalinfosystem.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerSearchActivity extends AppCompatActivity {
    private EditText etSearchCustomer;
    private ImageButton imgBtnSearch;
    private RecyclerView customerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search);
        initialize();
        imgBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCustomerByName();
            }
        });
    }

    private void loadCustomerByName() {
        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        String customerFullname = etSearchCustomer.getText().toString();
        Call<List<CustomersUD>> customerCall = futsalAPI.getCustomerByName(Url.token, customerFullname);
        customerCall.enqueue(new Callback<List<CustomersUD>>() {
            @Override
            public void onResponse(Call<List<CustomersUD>> call, Response<List<CustomersUD>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(CustomerSearchActivity.this, "Code " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                List<CustomersUD> customersUDList = response.body();
                CustomersAdapter customersAdapter = new CustomersAdapter(CustomerSearchActivity.this,
                        customersUDList);
                customerRecyclerView.setAdapter(customersAdapter);
                customerRecyclerView.setLayoutManager(new LinearLayoutManager(CustomerSearchActivity.this));
            }

            @Override
            public void onFailure(Call<List<CustomersUD>> call, Throwable t) {
                Toast.makeText(CustomerSearchActivity.this, "Error " + t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initialize() {
        etSearchCustomer = findViewById(R.id.etSearchCustomer);
        imgBtnSearch = findViewById(R.id.imgBtnSearch);
        customerRecyclerView = findViewById(R.id.customerRecyclerView);
    }
}
