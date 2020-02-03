package com.futsalrecord.futsalinfosystem.activities.futsal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.loader.content.CursorLoader;

import android.app.Notification;
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
import com.futsalrecord.futsalinfosystem.activities.FutsalDashboard;
import com.futsalrecord.futsalinfosystem.api.FutsalAPI;
import com.futsalrecord.futsalinfosystem.createChannel.CreateNotificationChannel;
import com.futsalrecord.futsalinfosystem.model.Events;
import com.futsalrecord.futsalinfosystem.serverResponse.ImageResponse;
import com.futsalrecord.futsalinfosystem.strictMode.StrictModeClass;
import com.futsalrecord.futsalinfosystem.url.Url;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FutsalEventActivity extends AppCompatActivity {
    private ImageButton imgEvent;
    private TextInputEditText eventName, eventPrice, eventDetail;
    private Button btnEventRegister;
    private String imgPath;
    private String imgName = "";
    private NotificationManagerCompat notificationManagerCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_event);
        initialize();
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateNotificationChannel createNotificationChannel = new CreateNotificationChannel(this);
        createNotificationChannel.createChannel();
        actionButtons();
    }

    private void displayNotification() {
        Notification notification = new NotificationCompat.Builder
                (this, CreateNotificationChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_insert_comment_black_24dp)
                .setContentTitle("Event registered")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    private void displayNotificationImage() {
        Notification notification = new NotificationCompat.Builder
                (this, CreateNotificationChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_insert_comment_black_24dp)
                .setContentTitle("Please select an image")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data == null) {
                displayNotificationImage();
            }
        }
        Uri uri = data.getData();
        imgEvent.setImageURI(uri);
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

    private void browseImg() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    private void actionButtons() {
        imgEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browseImg();
            }
        });

        btnEventRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImgOnly();
                registerEvent();
            }
        });
    }

    private void registerEvent() {
        String evtName = eventName.getText().toString().trim();
        String evtPrice = eventPrice.getText().toString().trim();
        String evtDetail = eventDetail.getText().toString().trim();

        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        Call<Void> eventCall = futsalAPI.addEvent(Url.token, evtName, evtPrice, evtDetail, imgName);

        eventCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(FutsalEventActivity.this, "Code " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                displayNotification();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(FutsalEventActivity.this, "Error " + t.getLocalizedMessage(),
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
        } catch (IOException e) {
            Toast.makeText(this, "Error " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void initialize() {
        imgEvent = findViewById(R.id.imgEvent);
        eventName = findViewById(R.id.eventName);
        eventPrice = findViewById(R.id.eventPrice);
        eventDetail = findViewById(R.id.eventDetail);
        btnEventRegister = findViewById(R.id.btnEventRegister);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FutsalEventActivity.this, FutsalDashboard.class);
        startActivity(intent);
        finish();
    }
}
