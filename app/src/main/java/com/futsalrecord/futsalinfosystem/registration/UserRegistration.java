package com.futsalrecord.futsalinfosystem.registration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.MainActivity;
import com.futsalrecord.futsalinfosystem.api.UsersAPI;
import com.futsalrecord.futsalinfosystem.model.Users;
import com.futsalrecord.futsalinfosystem.serverResponse.ImageResponse;
import com.futsalrecord.futsalinfosystem.serverResponse.RegisterResponse;
import com.futsalrecord.futsalinfosystem.strictMode.StrictModeClass;
import com.futsalrecord.futsalinfosystem.url.Url;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserRegistration extends AppCompatActivity {
    private TextInputLayout userRegUsername, userRegAddress, userRegEmail, userRegPhone,
            userRegPassword, userRegCPassword;
    private Button btnUserNext;
    private RadioGroup rgRegGender;
    private RadioButton rbRegMale, rbRegFemale, rbRegOthers;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        initialize();
        actionButtons();
    }

    private void actionButtons() {

        btnUserNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsername() | !validateEmail() | !validatePassword() |
                        !validatePhone() | !validateConfirmPassword() | !validateAddress()) {
                    return;
                }
                int selectGender = rgRegGender.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectGender);
                gender = radioButton.getText().toString().trim();
                String username = userRegUsername.getEditText().getText().toString().trim();
                String address = userRegAddress.getEditText().getText().toString().trim();
                String email = userRegEmail.getEditText().getText().toString().trim();
                String phone = userRegPhone.getEditText().getText().toString().trim();
                String password = userRegPassword.getEditText().getText().toString().trim();
                Bundle userDataBundle = new Bundle();
                userDataBundle.putString("username", username);
                userDataBundle.putString("address", address);
                userDataBundle.putString("email", email);
                userDataBundle.putString("phone", phone);
                userDataBundle.putString("password", password);
                userDataBundle.putString("gender", gender);
                Intent intentActivity = new Intent(getApplicationContext(), UserProfilePic.class);
                intentActivity.putExtras(userDataBundle);
                startActivity(intentActivity);
            }
        });
    }

    //Validation
    private boolean validateEmail() {
        String regEmail = userRegEmail.getEditText().getText().toString().trim();
        if (regEmail.isEmpty()) {
            userRegEmail.setError("Required");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(regEmail).matches()) {
            userRegEmail.setError("Please enter a valid email");
            return false;
        } else {
            userRegEmail.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String regName = userRegUsername.getEditText().getText().toString().trim();

        if (regName.isEmpty()) {
            userRegUsername.setError("Required");
            return false;
        } else if (regName.length() > 31) {
            userRegUsername.setError("Username too long");
            return false;
        } else if (regName.length() < 7) {
            userRegUsername.setError("Username too short (More than 6 characters)");
            return false;
        } else {
            userRegUsername.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String regPhone = userRegPhone.getEditText().getText().toString().trim();
        if (regPhone.isEmpty()) {
            userRegPhone.setError("Required");
            return false;
        } else if (regPhone.length() < 10) {
            userRegPhone.setError("Please enter a valid phone number");
            return false;
        } else {
            userRegPhone.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String regPassword = userRegPassword.getEditText().getText().toString().trim();
        if (regPassword.isEmpty()) {
            userRegPassword.setError("Required");
            return false;
        } else if (!RegistrationLogic.PASSWORD_PATTERN.matcher(regPassword).matches()) {
            userRegPassword.setError("Password is too weak");
            return false;
        } else {
            userRegPassword.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String regCpassword = userRegCPassword.getEditText().getText().toString().trim();
        if (!regCpassword.equals(userRegPassword.getEditText().getText().toString().trim())) {
            userRegCPassword.setError("Password does not match");
            return false;
        } else if (regCpassword.isEmpty()) {
            userRegCPassword.setError("Required");
            return false;
        } else {
            userRegCPassword.setError(null);
            return true;
        }
    }

    private boolean validateAddress() {
        String regAddress = userRegAddress.getEditText().getText().toString().trim();
        if (regAddress.isEmpty()) {
            userRegAddress.setError("Required");
            return false;
        } else {
            userRegAddress.setError(null);
            return true;
        }
    }

    private void initialize() {
        userRegUsername = findViewById(R.id.userRegUsername);
        userRegAddress = findViewById(R.id.userRegAddress);
        userRegEmail = findViewById(R.id.userRegEmail);
        userRegPhone = findViewById(R.id.userRegPhone);
        userRegPassword = findViewById(R.id.userRegPassword);
        userRegCPassword = findViewById(R.id.userRegCPassword);
        btnUserNext = findViewById(R.id.btnUserNext);
        rgRegGender = findViewById(R.id.rgRegGender);
        rbRegMale = findViewById(R.id.rbRegMale);
        rbRegFemale = findViewById(R.id.rbRegFemale);
        rbRegOthers = findViewById(R.id.rbRegOthers);
    }
}
