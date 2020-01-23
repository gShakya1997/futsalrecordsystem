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
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import okhttp3.MediaType;

public class UserRegistration extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=<>])" +    //at least 1 special character
                    ".{6,}" +               //at least 6 characters
                    "$");
    private TextInputLayout userRegUsername, userRegAddress, userRegEmail, userRegPhone,
            userRegPassword, userRegCPassword;
    private Button btnUserRegister;
    private ImageButton imgBtnUpload;
    private String imgPath;
    private String imgName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        binding();
        imgBtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browseImg();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select and image", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgBtnUpload.setImageURI(uri);
        imgPath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(getApplicationContext(), uri, projection,
                null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void browseImg() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    private boolean validateEmail() {
        String regEmail = userRegEmail.getEditText().getText().toString().trim();
        if (regEmail.isEmpty()) {
            userRegEmail.setError("Please enter a valid email");
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
            userRegUsername.setError("What's your name");
            return false;
        } else if (regName.length() > 50) {
            userRegUsername.setError("Name too long");
            return false;
        } else {
            userRegUsername.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String regPhone = userRegPhone.getEditText().getText().toString().trim();
        if (regPhone.isEmpty()) {
            userRegPhone.setError("Please enter a valid phone number");
            return false;
        } else if (regPhone.length() < 10 | regPhone.length() > 14) {
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
            userRegPassword.setError("Please enter password");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(regPassword).matches()) {
            userRegPassword.setError("Password is too weak");
            return false;
        } else {
            userRegPassword.setError(null);
            return true;
        }
    }

    private void binding() {
        userRegUsername = findViewById(R.id.userRegUsername);
        userRegAddress = findViewById(R.id.userRegAddress);
        userRegEmail = findViewById(R.id.userRegEmail);
        userRegPhone = findViewById(R.id.userRegPhone);
        userRegPassword = findViewById(R.id.userRegPassword);
        userRegCPassword = findViewById(R.id.userRegCPassword);
        btnUserRegister = findViewById(R.id.btnUserRegister);
        imgBtnUpload = findViewById(R.id.imgBtnUpload);
    }
}
