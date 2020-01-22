package com.futsalrecord.futsalinfosystem.activities.futsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.adapter.CustomersAdapter;
import com.futsalrecord.futsalinfosystem.adapter.StaffsAdapter;
import com.futsalrecord.futsalinfosystem.model.Customers;
import com.futsalrecord.futsalinfosystem.model.Staffs;

import java.util.ArrayList;
import java.util.List;

public class FutsalStaffDataActivity extends AppCompatActivity {
    private RecyclerView staffRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_staff_data);
        binding();
        List<Staffs> staffsList = new ArrayList<>();
        staffsList.add(new Staffs("Gunjan Shakya", "gunjan.shakya3@gmail.com", "9860915753", "Male", "Kathmandu", R.drawable.noimage));
        staffsList.add(new Staffs("Yunish Shakya", "gunjan.shakya3@gmail.com", "9860915753", "Male", "Patan", R.drawable.noimage));
        StaffsAdapter staffsAdapter = new StaffsAdapter(this, staffsList);
        staffRecyclerView.setAdapter(staffsAdapter);
        staffRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void binding() {
        staffRecyclerView = findViewById(R.id.staffRecyclerView);
    }
}
