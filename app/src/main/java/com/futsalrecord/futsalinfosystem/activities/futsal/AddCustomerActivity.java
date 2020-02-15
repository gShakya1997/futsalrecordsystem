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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.api.FutsalAPI;
import com.futsalrecord.futsalinfosystem.createChannel.CreateNotificationChannel;
import com.futsalrecord.futsalinfosystem.model.Customers;
import com.futsalrecord.futsalinfosystem.url.Url;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCustomerActivity extends AppCompatActivity {
    private TextInputEditText edCustomerFullname, edCustomerEmail, edCustomerPhoneNo, edCustomerAddress;
    private RadioGroup rgCustomerGender;
    private Button btnAddC;
    private NotificationManagerCompat notificationManagerCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
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
                .setContentTitle("Customer added")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    private void actionButtons() {
        btnAddC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomers();
                edCustomerFullname.setText("");
                edCustomerEmail.setText("");
                edCustomerPhoneNo.setText("");
                edCustomerAddress.setText("");
            }
        });
    }

    private void addCustomers() {
        int selectGender = rgCustomerGender.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectGender);
        String customerGender = radioButton.getText().toString();
        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        Call<Void> futsalCall = futsalAPI.addCustomers(Url.token,
                edCustomerFullname.getText().toString(),
                edCustomerEmail.getText().toString(),
                edCustomerPhoneNo.getText().toString(),
                customerGender,
                edCustomerAddress.getText().toString());

        futsalCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddCustomerActivity.this, "Code " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                displayNotification();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddCustomerActivity.this, "Error " + t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, FutsalCustomerDataActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    private void initialize() {
        edCustomerFullname = findViewById(R.id.customerFullname);
        edCustomerEmail = findViewById(R.id.customerEmail);
        edCustomerPhoneNo = findViewById(R.id.customerPhoneNo);
        edCustomerAddress = findViewById(R.id.customerAddress);
        rgCustomerGender = findViewById(R.id.customerGender);
        btnAddC = findViewById(R.id.btnAddC);
    }
}
