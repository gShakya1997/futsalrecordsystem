package com.futsalrecord.futsalinfosystem.ui.customerData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.adapter.CustomersAdapter;
import com.futsalrecord.futsalinfosystem.model.Customers;

import java.util.ArrayList;
import java.util.List;

public class CustomerDataFragment extends Fragment {
    RecyclerView customerRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_customer_data, container, false);
        customerRecyclerView = root.findViewById(R.id.customerRecyclerView);
        List<Customers> customersList = new ArrayList<>();
        customersList.add(new Customers("Gunjan Shakya","gunjan.shakya3@gmail.com","9860915753","Male","Kathmandu",R.drawable.noimage));
        customersList.add(new Customers("Yunish Shakya","gunjan.shakya3@gmail.com","9860915753","Male","Patan",R.drawable.noimage));
        CustomersAdapter customersAdapter = new CustomersAdapter(getActivity(),customersList);
        customerRecyclerView.setAdapter(customersAdapter);
        customerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }
}