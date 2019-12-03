package com.futsalrecord.futsalinfosystem.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.registration.UserRegistration;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserLoginFragment extends Fragment {
    private EditText etUserLoginUsername, etUserLoginPassword;
    private Button btnUserLogin, btnUserRegister;
    private String userUsername, userPassword;

    public UserLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);
        etUserLoginUsername = view.findViewById(R.id.etUserLoginUsername);
        etUserLoginPassword = view.findViewById(R.id.etUserLoginPassword);
        btnUserLogin = view.findViewById(R.id.btnUserLogin);
        btnUserRegister = view.findViewById(R.id.btnUserRegister);
        btnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userUsername = etUserLoginUsername.getText().toString().trim();
                userPassword = etUserLoginPassword.getText().toString().trim();

                if(userUsername.equals("user") && userPassword.equals("user")){
                    etUserLoginUsername.getText().clear();
                    etUserLoginPassword.getText().clear();
                    Toast.makeText(getActivity(),"Login Success",Toast.LENGTH_LONG).show(); //For testing
                } else {
                    etUserLoginUsername.setError("Invalid username");
                    etUserLoginPassword.setError("Invalid password");
                }
            }
        });

        btnUserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserRegistration.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
