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
import com.futsalrecord.futsalinfosystem.activities.GettingStarted;
import com.futsalrecord.futsalinfosystem.activities.registration.UserRegistration;
import com.futsalrecord.futsalinfosystem.bll.LoginBLL;
import com.futsalrecord.futsalinfosystem.strictMode.StrictModeClass;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserLoginFragment extends Fragment {
    private TextInputLayout userLoginUsername, userLoginPassword;
    private Button btnUserLogin, btnUserRegister;
    private String username, password;

    public UserLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);

        //initialize
        userLoginUsername = view.findViewById(R.id.userLoginUsername);
        userLoginPassword = view.findViewById(R.id.userLoginPassword);
        btnUserLogin = view.findViewById(R.id.btnUserLogin);
        btnUserRegister = view.findViewById(R.id.btnUserRegister);

        //action buttons
        btnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
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

    private void login(){
        username = userLoginUsername.getEditText().getText().toString().trim();
        password = userLoginPassword.getEditText().getText().toString().trim();

        LoginBLL loginBLL = new LoginBLL();
        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(username,password)){
            saveSharedPreferences();
            Intent intent = new Intent(getActivity(), GettingStarted.class);
            startActivity(intent);
        } else {
            userLoginUsername.setError("Enter correct username");
            userLoginPassword.setError("Enter correct password");
        }
    }

    private void saveSharedPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.apply();
    }
}
