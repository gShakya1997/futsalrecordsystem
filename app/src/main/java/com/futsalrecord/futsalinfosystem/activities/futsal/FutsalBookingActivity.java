package com.futsalrecord.futsalinfosystem.activities.futsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.FutsalDashboard;
import com.futsalrecord.futsalinfosystem.api.FutsalAPI;
import com.futsalrecord.futsalinfosystem.model.Booking;
import com.futsalrecord.futsalinfosystem.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FutsalBookingActivity extends AppCompatActivity {
    private TextView btn[] = new TextView[14];
    private Button btnSaveBooking, btnClear, btnUpdateBooking;
    private TextView tvId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_booking);
        binding();
        actionListeners();
        loadBookingDetail();
    }

    private void actionListeners() {
        btnSaveBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                futsalBooking();
                recreate();
            }
        });

        btnUpdateBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookingUpdate();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn[0].setHint("true");
                btn[0].setBackgroundColor(Color.parseColor("#4CAF50"));
                btn[1].setHint("true");
                btn[1].setBackgroundColor(Color.parseColor("#4CAF50"));
                btn[2].setHint("true");
                btn[2].setBackgroundColor(Color.parseColor("#4CAF50"));
                btn[3].setHint("true");
                btn[3].setBackgroundColor(Color.parseColor("#4CAF50"));
                btn[4].setHint("true");
                btn[4].setBackgroundColor(Color.parseColor("#4CAF50"));
                btn[5].setHint("true");
                btn[5].setBackgroundColor(Color.parseColor("#4CAF50"));
                btn[6].setHint("true");
                btn[6].setBackgroundColor(Color.parseColor("#4CAF50"));
                btn[7].setHint("true");
                btn[7].setBackgroundColor(Color.parseColor("#4CAF50"));
                btn[8].setHint("true");
                btn[8].setBackgroundColor(Color.parseColor("#4CAF50"));
                btn[9].setHint("true");
                btn[9].setBackgroundColor(Color.parseColor("#4CAF50"));
                btn[10].setHint("true");
                btn[10].setBackgroundColor(Color.parseColor("#4CAF50"));
                btn[11].setHint("true");
                btn[11].setBackgroundColor(Color.parseColor("#4CAF50"));
                btn[12].setHint("true");
                btn[12].setBackgroundColor(Color.parseColor("#4CAF50"));
                btn[13].setHint("true");
                btn[13].setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        });

        btn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn[0].getHint().equals("true")) {
                    btn[0].setBackgroundColor(Color.parseColor("#F44336"));
                    btn[0].setHint("false");
                } else {
                    btn[0].setBackgroundColor(Color.parseColor("#4CAF50"));
                    btn[0].setHint("true");
                }
            }
        });

        btn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn[1].getHint().equals("true")) {
                    btn[1].setBackgroundColor(Color.parseColor("#F44336"));
                    btn[1].setHint("false");
                } else {
                    btn[1].setBackgroundColor(Color.parseColor("#4CAF50"));
                    btn[1].setHint("true");
                }
            }
        });

        btn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn[2].getHint().equals("true")) {
                    btn[2].setBackgroundColor(Color.parseColor("#F44336"));
                    btn[2].setHint("false");
                } else {
                    btn[2].setBackgroundColor(Color.parseColor("#4CAF50"));
                    btn[2].setHint("true");
                }
            }
        });

        btn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn[3].getHint().equals("true")) {
                    btn[3].setBackgroundColor(Color.parseColor("#F44336"));
                    btn[3].setHint("false");
                } else {
                    btn[3].setBackgroundColor(Color.parseColor("#4CAF50"));
                    btn[3].setHint("true");
                }
            }
        });

        btn[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn[4].getHint().equals("true")) {
                    btn[4].setBackgroundColor(Color.parseColor("#F44336"));
                    btn[4].setHint("false");
                } else {
                    btn[4].setBackgroundColor(Color.parseColor("#4CAF50"));
                    btn[4].setHint("true");
                }
            }
        });

        btn[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn[5].getHint().equals("true")) {
                    btn[5].setBackgroundColor(Color.parseColor("#F44336"));
                    btn[5].setHint("false");
                } else {
                    btn[5].setBackgroundColor(Color.parseColor("#4CAF50"));
                    btn[5].setHint("true");
                }
            }
        });

        btn[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn[6].getHint().equals("true")) {
                    btn[6].setBackgroundColor(Color.parseColor("#F44336"));
                    btn[6].setHint("false");
                } else {
                    btn[6].setBackgroundColor(Color.parseColor("#4CAF50"));
                    btn[6].setHint("true");
                }
            }
        });

        btn[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn[7].getHint().equals("true")) {
                    btn[7].setBackgroundColor(Color.parseColor("#F44336"));
                    btn[7].setHint("false");
                } else {
                    btn[7].setBackgroundColor(Color.parseColor("#4CAF50"));
                    btn[7].setHint("true");
                }
            }
        });

        btn[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn[8].getHint().equals("true")) {
                    btn[8].setBackgroundColor(Color.parseColor("#F44336"));
                    btn[8].setHint("false");
                } else {
                    btn[8].setBackgroundColor(Color.parseColor("#4CAF50"));
                    btn[8].setHint("true");
                }
            }
        });

        btn[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn[9].getHint().equals("true")) {
                    btn[9].setBackgroundColor(Color.parseColor("#F44336"));
                    btn[9].setHint("false");
                } else {
                    btn[9].setBackgroundColor(Color.parseColor("#4CAF50"));
                    btn[9].setHint("true");
                }
            }
        });

        btn[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn[10].getHint().equals("true")) {
                    btn[10].setBackgroundColor(Color.parseColor("#F44336"));
                    btn[10].setHint("false");
                } else {
                    btn[10].setBackgroundColor(Color.parseColor("#4CAF50"));
                    btn[10].setHint("true");
                }
            }
        });

        btn[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn[11].getHint().equals("true")) {
                    btn[11].setBackgroundColor(Color.parseColor("#F44336"));
                    btn[11].setHint("false");
                } else {
                    btn[11].setBackgroundColor(Color.parseColor("#4CAF50"));
                    btn[11].setHint("true");
                }
            }
        });

        btn[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn[12].getHint().equals("true")) {
                    btn[12].setBackgroundColor(Color.parseColor("#F44336"));
                    btn[12].setHint("false");
                } else {
                    btn[12].setBackgroundColor(Color.parseColor("#4CAF50"));
                    btn[12].setHint("true");
                }
            }
        });

        btn[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn[13].getHint().equals("true")) {
                    btn[13].setBackgroundColor(Color.parseColor("#F44336"));
                    btn[13].setHint("false");
                } else {
                    btn[13].setBackgroundColor(Color.parseColor("#4CAF50"));
                    btn[13].setHint("true");
                }
            }
        });
    }

    private void loadBookingDetail() {
        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        Call<List<Booking>> bookingCall = futsalAPI.getBooking(Url.token);

        bookingCall.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(FutsalBookingActivity.this, "Code " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Booking> bookingList = response.body();
                for (Booking booking : bookingList) {
                    String _id = booking.get_id();
                    String available1 = booking.getAvailable1();
                    String available2 = booking.getAvailable2();
                    String available3 = booking.getAvailable3();
                    String available4 = booking.getAvailable4();
                    String available5 = booking.getAvailable5();
                    String available6 = booking.getAvailable6();
                    String available7 = booking.getAvailable7();
                    String available8 = booking.getAvailable8();
                    String available9 = booking.getAvailable9();
                    String available10 = booking.getAvailable10();
                    String available11 = booking.getAvailable11();
                    String available12 = booking.getAvailable12();
                    String available13 = booking.getAvailable13();
                    String available14 = booking.getAvailable14();

                    tvId.setText(_id);
                    if (available1.equals("true")) {
                        btn[0].setHint("true");
                        btn[0].setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else {
                        btn[0].setHint("false");
                        btn[0].setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    if (available2.equals("true")) {
                        btn[1].setHint("true");
                        btn[1].setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else {
                        btn[1].setHint("false");
                        btn[1].setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    if (available3.equals("true")) {
                        btn[2].setHint("true");
                        btn[2].setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else {
                        btn[2].setHint("false");
                        btn[2].setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    if (available4.equals("true")) {
                        btn[3].setHint("true");
                        btn[3].setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else {
                        btn[3].setHint("false");
                        btn[3].setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    if (available5.equals("true")) {
                        btn[4].setHint("true");
                        btn[4].setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else {
                        btn[4].setHint("false");
                        btn[4].setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    if (available6.equals("true")) {
                        btn[5].setHint("true");
                        btn[5].setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else {
                        btn[5].setHint("false");
                        btn[5].setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    if (available7.equals("true")) {
                        btn[6].setHint("true");
                        btn[6].setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else {
                        btn[6].setHint("false");
                        btn[6].setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    if (available8.equals("true")) {
                        btn[7].setHint("true");
                        btn[7].setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else {
                        btn[7].setHint("false");
                        btn[7].setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    if (available9.equals("true")) {
                        btn[8].setHint("true");
                        btn[8].setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else {
                        btn[8].setHint("false");
                        btn[8].setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    if (available10.equals("true")) {
                        btn[9].setHint("true");
                        btn[9].setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else {
                        btn[9].setHint("false");
                        btn[9].setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    if (available11.equals("true")) {
                        btn[10].setHint("true");
                        btn[10].setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else {
                        btn[10].setHint("false");
                        btn[10].setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    if (available12.equals("true")) {
                        btn[11].setHint("true");
                        btn[11].setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else {
                        btn[11].setHint("false");
                        btn[11].setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    if (available13.equals("true")) {
                        btn[12].setHint("true");
                        btn[12].setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else {
                        btn[12].setHint("false");
                        btn[12].setBackgroundColor(Color.parseColor("#F44336"));
                    }

                    if (available14.equals("true")) {
                        btn[13].setHint("true");
                        btn[13].setBackgroundColor(Color.parseColor("#4CAF50"));
                    } else {
                        btn[13].setHint("false");
                        btn[13].setBackgroundColor(Color.parseColor("#F44336"));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {

            }
        });
    }

    private void bookingUpdate() {
        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        Booking booking = new Booking(
                tvId.getText().toString(),
                btn[0].getHint().toString(),
                btn[1].getHint().toString(),
                btn[2].getHint().toString(),
                btn[3].getHint().toString(),
                btn[4].getHint().toString(),
                btn[5].getHint().toString(),
                btn[6].getHint().toString(),
                btn[7].getHint().toString(),
                btn[8].getHint().toString(),
                btn[9].getHint().toString(),
                btn[10].getHint().toString(),
                btn[11].getHint().toString(),
                btn[12].getHint().toString(),
                btn[13].getHint().toString()
        );
        Call<Void> bookingCall = futsalAPI.updateBooking(Url.token, tvId.getText().toString(), booking);
        bookingCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(FutsalBookingActivity.this, "Code " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(FutsalBookingActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(FutsalBookingActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void futsalBooking() {
        String available1 = btn[0].getHint().toString();
        String available2 = btn[1].getHint().toString();
        String available3 = btn[2].getHint().toString();
        String available4 = btn[3].getHint().toString();
        String available5 = btn[4].getHint().toString();
        String available6 = btn[5].getHint().toString();
        String available7 = btn[6].getHint().toString();
        String available8 = btn[7].getHint().toString();
        String available9 = btn[8].getHint().toString();
        String available10 = btn[9].getHint().toString();
        String available11 = btn[10].getHint().toString();
        String available12 = btn[11].getHint().toString();
        String available13 = btn[12].getHint().toString();
        String available14 = btn[13].getHint().toString();

        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        Call<Void> bookingCall = futsalAPI.addBooking(Url.token,
                available1,
                available2,
                available3,
                available4,
                available5,
                available6,
                available7,
                available8,
                available9,
                available10,
                available11,
                available12,
                available13,
                available14
        );

        bookingCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(FutsalBookingActivity.this, "Code " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(FutsalBookingActivity.this, "Booked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(FutsalBookingActivity.this, "Error " + t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void binding() {
        btn[0] = findViewById(R.id.btn0);
        btn[1] = findViewById(R.id.btn1);
        btn[2] = findViewById(R.id.btn2);
        btn[3] = findViewById(R.id.btn3);
        btn[4] = findViewById(R.id.btn4);
        btn[5] = findViewById(R.id.btn5);
        btn[6] = findViewById(R.id.btn6);
        btn[7] = findViewById(R.id.btn7);
        btn[8] = findViewById(R.id.btn8);
        btn[9] = findViewById(R.id.btn9);
        btn[10] = findViewById(R.id.btn10);
        btn[11] = findViewById(R.id.btn11);
        btn[12] = findViewById(R.id.btn12);
        btn[13] = findViewById(R.id.btn13);
        btnSaveBooking = findViewById(R.id.btnSaveBooking);
        btnClear = findViewById(R.id.btnClear);
        btnUpdateBooking = findViewById(R.id.btnUpdateBooking);
        tvId = findViewById(R.id.tvId);
        btn[0].setHint("true");
        btn[1].setHint("true");
        btn[2].setHint("true");
        btn[3].setHint("true");
        btn[4].setHint("true");
        btn[5].setHint("true");
        btn[6].setHint("true");
        btn[7].setHint("true");
        btn[8].setHint("true");
        btn[9].setHint("true");
        btn[10].setHint("true");
        btn[11].setHint("true");
        btn[12].setHint("true");
        btn[13].setHint("true");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FutsalBookingActivity.this, FutsalDashboard.class);
        startActivity(intent);
        finish();
    }
}
