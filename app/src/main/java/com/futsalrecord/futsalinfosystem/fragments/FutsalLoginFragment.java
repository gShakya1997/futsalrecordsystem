package com.futsalrecord.futsalinfosystem.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.FutsalDashboard;
import com.futsalrecord.futsalinfosystem.activities.registration.FutsalRegistration;

/**
 * A simple {@link Fragment} subclass.
 */
public class FutsalLoginFragment extends Fragment {
    private EditText etAdminLoginUsername, etAdminLoginPassword;
    private Button btnAdminLogin, btnAdminRegister;
    private String adminUsername, adminPassword;


    public FutsalLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_futsal_login, container, false);
        etAdminLoginUsername = view.findViewById(R.id.etAdminLoginUsername);
        etAdminLoginPassword = view.findViewById(R.id.etAdminLoginPassword);
        btnAdminLogin = view.findViewById(R.id.btnAdminLogin);
        btnAdminRegister = view.findViewById(R.id.btnAdminRegister);
        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminUsername = etAdminLoginUsername.getText().toString().trim();
                adminPassword = etAdminLoginPassword.getText().toString().trim();

                if (adminUsername.equals("admin") && adminPassword.equals("admin")) {
                    etAdminLoginUsername.getText().clear();
                    etAdminLoginPassword.getText().clear();
                    saveSharedPreferences();
                    Intent intent = new Intent(getActivity(), FutsalDashboard.class);
                    startActivity(intent);
                } else {
                    etAdminLoginUsername.setError("Invalid username");
                    etAdminLoginPassword.setError("Invalid password");
                }
            }
        });
        btnAdminRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FutsalRegistration.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void saveSharedPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",adminUsername);
        editor.putString("password",adminPassword);
        editor.apply();
        Toast.makeText(getActivity(), "Successful", Toast.LENGTH_LONG).show();
    }
}
