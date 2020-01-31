package com.futsalrecord.futsalinfosystem.activities.futsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.api.FutsalAPI;
import com.futsalrecord.futsalinfosystem.model.Customers;
import com.futsalrecord.futsalinfosystem.model.CustomersUD;
import com.futsalrecord.futsalinfosystem.model.Futsal;
import com.futsalrecord.futsalinfosystem.url.Url;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerDetailActivity extends AppCompatActivity {
    private TextInputEditText edCustomerFullname, edCustomerEmail,
            edCustomerPhoneNo, edCustomerAddress;
    private TextView edcustomerID;
    private RadioGroup customerGender;
    private Button btnUpdateC;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        initialize();
        int selectGender = customerGender.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectGender);
        gender = radioButton.getText().toString().trim();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            edcustomerID.setText(bundle.getString("tvCustomerId"));
            edCustomerFullname.setText(bundle.getString("tvCustomerFullName"));
            edCustomerEmail.setText(bundle.getString("tvCustomerEmail"));
            edCustomerPhoneNo.setText(bundle.getString("tvCustomerPhoneNo"));
            edCustomerAddress.setText(bundle.getString("tvCustomerAddress"));
        }
        btnUpdateC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCustomerData();
            }
        });
    }

    private void updateCustomerData() {
        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        CustomersUD customersUD = new CustomersUD(
                edcustomerID.getText().toString(),
                edCustomerFullname.getText().toString(),
                edCustomerEmail.getText().toString(),
                edCustomerPhoneNo.getText().toString(),
                gender,
                edCustomerAddress.getText().toString()
        );
        Call<Void> callCustomer = futsalAPI.updateCustomerDetail(Url.token,
                edcustomerID.getText().toString(),
                customersUD);

        callCustomer.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(CustomerDetailActivity.this, "Code " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(CustomerDetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CustomerDetailActivity.this, "Error " + t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,FutsalCustomerDataActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    private void initialize() {
        edcustomerID = findViewById(R.id.customerID);
        edCustomerFullname = findViewById(R.id.customerFullname);
        edCustomerEmail = findViewById(R.id.customerEmail);
        edCustomerPhoneNo = findViewById(R.id.customerPhoneNo);
        edCustomerAddress = findViewById(R.id.customerAddress);
        customerGender = findViewById(R.id.customerGender);
        btnUpdateC = findViewById(R.id.btnUpdateC);
    }
}
