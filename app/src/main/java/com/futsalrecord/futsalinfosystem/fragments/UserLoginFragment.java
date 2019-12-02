package com.futsalrecord.futsalinfosystem.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserLoginFragment extends Fragment {
    private EditText etStaffLoginUsername, etStaffLoginPassword;
    private Button btnStaffLogin;
    private String staffUsername, staffPassword;

    public UserLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);
        etStaffLoginUsername = view.findViewById(R.id.etStaffLoginUsername);
        etStaffLoginPassword = view.findViewById(R.id.etStaffLoginPassword);
        btnStaffLogin = view.findViewById(R.id.btnStaffLogin);
        btnStaffLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staffUsername = etStaffLoginUsername.getText().toString().trim();
                staffPassword = etStaffLoginPassword.getText().toString().trim();

                if(staffUsername.equals("staff") && staffPassword.equals("staff")){
                    etStaffLoginUsername.getText().clear();
                    etStaffLoginPassword.getText().clear();
                    Toast.makeText(getActivity(),"Login Success",Toast.LENGTH_LONG).show(); //For testing
                } else {
                    etStaffLoginUsername.setError("Invalid username");
                    etStaffLoginPassword.setError("Invalid password");
                }
            }
        });
        return view;
    }

}
