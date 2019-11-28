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

import com.futsalrecord.futsalinfosystem.AdminDashboard;
import com.futsalrecord.futsalinfosystem.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminLoginFragment extends Fragment {
    private EditText etAdminLoginUsername, etAdminLoginPassword;
    private Button btnAdminLogin;
    private String adminUsername, adminPassword;


    public AdminLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_login, container, false);
        etAdminLoginUsername = view.findViewById(R.id.etAdminLoginUsername);
        etAdminLoginPassword = view.findViewById(R.id.etAdminLoginPassword);
        btnAdminLogin = view.findViewById(R.id.btnAdminLogin);
        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminUsername = etAdminLoginUsername.getText().toString().trim();
                adminPassword = etAdminLoginPassword.getText().toString().trim();

                if (adminUsername.equals("admin") && adminPassword.equals("admin")) {
                    etAdminLoginUsername.getText().clear();
                    etAdminLoginPassword.getText().clear();
                    saveSharedPreferences();
                    Intent intent = new Intent(getActivity(), AdminDashboard.class);
                    startActivity(intent);
                } else {
                    etAdminLoginUsername.setError("Invalid username");
                    etAdminLoginPassword.setError("Invalid password");
                }
            }
        });
        return view;
    }

    private void saveSharedPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",adminUsername);
        editor.putString("password",adminPassword);
        editor.commit();
        Toast.makeText(getActivity(), "Successful", Toast.LENGTH_LONG).show();
    }
}
