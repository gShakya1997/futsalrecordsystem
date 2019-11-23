package com.futsalrecord.futsalinfosystem.ui.staffData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.adapter.StaffsAdapter;
import com.futsalrecord.futsalinfosystem.model.Staffs;

import java.util.ArrayList;
import java.util.List;

public class StaffDataFragment extends Fragment {
    private RecyclerView staffRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_staff_data, container, false);
        staffRecyclerView = root.findViewById(R.id.staffRecyclerView);

        List<Staffs> staffsList = new ArrayList<>();
        staffsList.add(new Staffs("Gunjan Shakya","gunjan.shakya3@gmail.com","9860915753","Male","Kathmandu",R.drawable.noimage));
        staffsList.add(new Staffs("Yunish Shakya","yunish.shakya3@gmail.com","9818086908","Male","Patan",R.drawable.noimage));
        StaffsAdapter staffsAdapter = new StaffsAdapter(getActivity(),staffsList);
        staffRecyclerView.setAdapter(staffsAdapter);
        staffRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }
}