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
import com.futsalrecord.futsalinfosystem.bll.LoginBLL;
import com.futsalrecord.futsalinfosystem.strictMode.StrictModeClass;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class FutsalLoginFragment extends Fragment {
    private TextInputLayout futsalLoginUsername, futsalLoginPassword;
    private Button btnFutsalLogin, btnFutsalRegister;
    private String futsalname, futsalPassword;


    public FutsalLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_futsal_login, container, false);
        futsalLoginUsername = view.findViewById(R.id.futsalLoginUsername);
        futsalLoginPassword = view.findViewById(R.id.futsalLoginPassword);
        btnFutsalLogin = view.findViewById(R.id.btnFutsalLogin);
        btnFutsalRegister = view.findViewById(R.id.btnFutsalRegister);
        btnFutsalLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btnFutsalRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FutsalRegistration.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void login() {
        futsalname = futsalLoginUsername.getEditText().getText().toString().trim();
        futsalPassword = futsalLoginPassword.getEditText().getText().toString().trim();

        LoginBLL loginBLL = new LoginBLL();
        StrictModeClass.StrictMode();
        if (loginBLL.checkFutsal(futsalname, futsalPassword)) {
            saveSharedPreferences();
            Intent intent = new Intent(getActivity(), FutsalDashboard.class);
            startActivity(intent);
        } else {
            futsalLoginUsername.setError("Enter correct username");
            futsalLoginPassword.setError("Enter correct password");
        }
    }

    private void saveSharedPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Futsal", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("FutsalName", futsalname);
        editor.putString("FutsalPassword", futsalPassword);
        editor.apply();
    }
}
