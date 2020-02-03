package com.futsalrecord.futsalinfosystem.activities.futsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.FutsalDashboard;
import com.futsalrecord.futsalinfosystem.api.FutsalAPI;
import com.futsalrecord.futsalinfosystem.model.Futsal;
import com.futsalrecord.futsalinfosystem.strictMode.StrictModeClass;
import com.futsalrecord.futsalinfosystem.url.Url;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FutsalProfileActivity extends AppCompatActivity {
    private ImageView ivProfilePic;
    private TextView tvFutsal, tvFutsalEmail, tvFutsalPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_profile);
        initialize();
        loadCurrentProfile();
    }

    private void loadCurrentProfile() {
        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        Call<Futsal> futsalCall = futsalAPI.getFutsalDetails(Url.token);

        futsalCall.enqueue(new Callback<Futsal>() {
            @Override
            public void onResponse(Call<Futsal> call, Response<Futsal> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(FutsalProfileActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                StrictModeClass.StrictMode();
                try {
                    String futsalName = response.body().getFutsalName();
                    String futsalEmail = response.body().getFutsalEmail();
                    String futsalPhone = response.body().getFutsalPhone();

                    String imgPath = Url.imagePath + response.body().getFutsalImage();
                    URL url = new URL(imgPath);
                    ivProfilePic.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

                    tvFutsal.setText(futsalName);
                    tvFutsalEmail.setText(futsalEmail);
                    tvFutsalPhone.setText(futsalPhone);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Futsal> call, Throwable t) {
                Toast.makeText(FutsalProfileActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initialize() {
        ivProfilePic = findViewById(R.id.ivProfilePic);
        tvFutsal = findViewById(R.id.tvFutsal);
        tvFutsalEmail = findViewById(R.id.tvFutsalEmail);
        tvFutsalPhone = findViewById(R.id.tvFutsalPhone);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FutsalProfileActivity.this, FutsalDashboard.class);
        startActivity(intent);
        finish();
    }
}
