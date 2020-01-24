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
    private Button btnUserRegister;
    private ImageButton imgBtnUpload;
    private RadioGroup rgRegGender;
    private RadioButton rbRegMale, rbRegFemale, rbRegOthers;
    String imgPath;
    private String imgName = "";
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        binding();
        actionButtons();
    }

    private void actionButtons() {
        imgBtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browseImg();
            }
        });

        btnUserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
                saveImgOnly();
                if (!register()) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean register() {
        if (validateUsername() || validateEmail() || validatePassword() ||
                validatePhone() || validateConfirmPassword() || validateAddress() ||
                validateGender()) {
            return false;
        } else {
            String username = userRegUsername.getEditText().getText().toString().trim();
            String address = userRegAddress.getEditText().getText().toString().trim();
            String email = userRegEmail.getEditText().getText().toString().trim();
            String phone = userRegPhone.getEditText().getText().toString().trim();
            String password = userRegPassword.getEditText().getText().toString().trim();

            Users users = new Users(username, address, email, phone, password, gender, imgName);
            UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
            Call<RegisterResponse> registerResponseCall = usersAPI.registerUser(users);

            registerResponseCall.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(UserRegistration.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(UserRegistration.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Toast.makeText(UserRegistration.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        return true;
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

    private void saveImgOnly() {
        File file = new File(imgPath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<ImageResponse> responseCall = usersAPI.uploadImage(body);
        StrictModeClass.StrictMode();
        try {
            Response<ImageResponse> imageResponseResponse = responseCall.execute();
            imgName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
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
            userRegPhone.setError("Required");
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

    private boolean validateGender() {
        try {
            int selectGender = rgRegGender.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectGender);
            gender = radioButton.getText().toString().trim();
            return true;
        } catch (Exception e) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return false;
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
        rgRegGender = findViewById(R.id.rgRegGender);
        rbRegMale = findViewById(R.id.rbRegMale);
        rbRegFemale = findViewById(R.id.rbRegFemale);
        rbRegOthers = findViewById(R.id.rbRegOthers);
    }
}
