package com.futsalrecord.futsalinfosystem.activities.registration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.MainActivity;
import com.futsalrecord.futsalinfosystem.api.FutsalAPI;
import com.futsalrecord.futsalinfosystem.model.Futsal;
import com.futsalrecord.futsalinfosystem.serverResponse.ImageResponse;
import com.futsalrecord.futsalinfosystem.serverResponse.RegisterResponse;
import com.futsalrecord.futsalinfosystem.strictMode.StrictModeClass;
import com.futsalrecord.futsalinfosystem.url.Url;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FutsalProfilePic extends AppCompatActivity {
    private ImageButton imgBtnUploadFutsal;
    private Button btnFutsalRegister, btnFutsalBack;
    String imgPath;
    private String imgName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_profile_pic);
        initialize();
        actionButtons();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgBtnUploadFutsal.setImageURI(uri);
        imgPath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(getApplicationContext(), uri, projection
                , null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void actionButtons() {
        imgBtnUploadFutsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browseImg();
            }
        });

        btnFutsalRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImgOnly();
                register();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void register() {

        Intent intent = getIntent();
        Bundle futsalDataBundle = intent.getExtras();
        String futsalName = futsalDataBundle.getString("futsalName");
        String futsalAddress = futsalDataBundle.getString("futsalAddress");
        String futsalEmail = futsalDataBundle.getString("futsalEmail");
        String futsalPhone = futsalDataBundle.getString("futsalPhone");
        String futsalPassword = futsalDataBundle.getString("futsalPassword");
        String futsalOpeningTime = futsalDataBundle.getString("futsalOpeningTime");
        String futsalClosingTime = futsalDataBundle.getString("futsalClosingTime");
        String futsalPrice = futsalDataBundle.getString("futsalPrice");

        Futsal futsal = new Futsal(futsalName, futsalAddress, futsalEmail, futsalPhone, futsalPassword
                , futsalOpeningTime, futsalClosingTime, futsalPrice, imgName);
        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        Call<RegisterResponse> registerResponseCall = futsalAPI.registerFutsal(futsal);

        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(FutsalProfilePic.this, "Code " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(FutsalProfilePic.this, "Successfully registered",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(FutsalProfilePic.this, "Error " + t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveImgOnly() {
        File file = new File(imgPath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);
        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        Call<ImageResponse> responseCall = futsalAPI.uploadFutsalImage(body);
        StrictModeClass.StrictMode();
        try {
            Response<ImageResponse> imageResponseResponse = responseCall.execute();
            imgName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted " + imgName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void browseImg() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    private void initialize() {
        imgBtnUploadFutsal = findViewById(R.id.imgBtnUploadFutsal);
        btnFutsalRegister = findViewById(R.id.btnFutsalRegister);
        btnFutsalBack = findViewById(R.id.btnFutsalBack);
    }
}
