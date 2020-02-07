package com.futsalrecord.futsalinfosystem.activities.futsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.FutsalDashboard;
import com.futsalrecord.futsalinfosystem.api.FutsalAPI;
import com.futsalrecord.futsalinfosystem.createChannel.CreateNotificationChannel;
import com.futsalrecord.futsalinfosystem.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FutsalFeedback extends AppCompatActivity {
    private RatingBar ratingBar;
    private EditText etFeedback;
    private Button btnSubmitFeedback;
    private TextView tvRating;
    private float ratingFeedback;
    private NotificationManagerCompat notificationManagerCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_feedback);
        initialize();

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateNotificationChannel createNotificationChannel = new CreateNotificationChannel(this);
        createNotificationChannel.createChannel();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tvRating.setText("Rating: " + rating);
                ratingFeedback = rating;
            }
        });
        btnSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                futsalFeedback();
                Intent intent = new Intent(FutsalFeedback.this, FutsalDashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void futsalFeedback() {
        String feedback = etFeedback.getText().toString();
        String rating = Float.toString(ratingFeedback);

        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        Call<Void> feedbackCall = futsalAPI.addFeedback(Url.token, rating, feedback);

        feedbackCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(FutsalFeedback.this, "Code " + response.body(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                displayNotification();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(FutsalFeedback.this, "Error " + t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
}

    private void displayNotification() {
        Notification notification = new NotificationCompat.Builder
                (this, CreateNotificationChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_thumb_up_black_24dp)
                .setContentTitle("Feedback submitted")
                .setContentText("Thank you for your feedback")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    private void initialize() {
        ratingBar = findViewById(R.id.ratingbar);
        etFeedback = findViewById(R.id.etFeedback);
        btnSubmitFeedback = findViewById(R.id.btnSubmitFeedback);
        tvRating = findViewById(R.id.tvRating);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FutsalFeedback.this, FutsalDashboard.class);
        startActivity(intent);
        finish();
    }
}
