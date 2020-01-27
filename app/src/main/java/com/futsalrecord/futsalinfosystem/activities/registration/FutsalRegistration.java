package com.futsalrecord.futsalinfosystem.activities.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.futsalrecord.futsalinfosystem.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class FutsalRegistration extends AppCompatActivity {
    private TextInputLayout etFutsalName, etFutsalAddress, etFutsalEmail, etFutsalPhone,
            etFutsalPassword, etFutsalCPassword, etFutsalOpeningTime, etFutsalClosingTime,
            etFutsalPrice;
    private Button btnFutsalNext;
    private TextInputEditText futsalOpeningTime, futsalClosingTime;
    private TimePickerDialog timePickerDialog;
    private Calendar calendar;
    private String amPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_registration);
        initialize();
        actionButtons();
    }

    private void actionButtons() {
        btnFutsalNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateFutsalName() | !validateAddress() | !validateEmail() | !validatePhone()
                        | !validatePassword() | !validateConfirmPassword() | !validateOpeningTime() |
                        !validateClosingTime() | !validatePrice()) {
                    return;
                }
                String futsalName = etFutsalName.getEditText().getText().toString().trim();
                String futsalAddress = etFutsalAddress.getEditText().getText().toString().trim();
                String futsalEmail = etFutsalEmail.getEditText().getText().toString().trim();
                String futsalPhone = etFutsalPhone.getEditText().getText().toString().trim();
                String futsalPassword = etFutsalPassword.getEditText().getText().toString().trim();
                String futsalCPassword = etFutsalCPassword.getEditText().getText().toString().trim();
                String futsalOpeningTime = etFutsalOpeningTime.getEditText().getText().toString().trim();
                String futsalClosingTime = etFutsalClosingTime.getEditText().getText().toString().trim();
                String futsalPrice = etFutsalPrice.getEditText().getText().toString().trim();
                Bundle futsalDataBundle = new Bundle();
                futsalDataBundle.putString("futsalName", futsalName);
                futsalDataBundle.putString("futsalAddress", futsalAddress);
                futsalDataBundle.putString("futsalEmail", futsalEmail);
                futsalDataBundle.putString("futsalPhone", futsalPhone);
                futsalDataBundle.putString("futsalPassword", futsalPassword);
                futsalDataBundle.putString("futsalCPassword", futsalCPassword);
                futsalDataBundle.putString("futsalOpeningTime", futsalOpeningTime);
                futsalDataBundle.putString("futsalClosingTime", futsalClosingTime);
                futsalDataBundle.putString("futsalPrice", futsalPrice);
                Intent intentActivity = new Intent(getApplicationContext(), FutsalProfilePic.class);
                intentActivity.putExtras(futsalDataBundle);
                startActivity(intentActivity);
            }
        });

        futsalOpeningTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(FutsalRegistration.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        futsalOpeningTime.setText(String.format("%02d:%02d", hourOfDay, minute) + " " + amPm);
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });

        futsalClosingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(FutsalRegistration.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        futsalClosingTime.setText(String.format("%02d:%02d", hourOfDay, minute) + " " + amPm);
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });
    }

    //validation
    private boolean validateFutsalName() {
        String regName = etFutsalName.getEditText().getText().toString().trim();

        if (regName.isEmpty()) {
            etFutsalName.setError("Required");
            return false;
        } else {
            etFutsalName.setError(null);
            return true;
        }
    }

    private boolean validateAddress() {
        String regAddress = etFutsalAddress.getEditText().getText().toString().trim();
        if (regAddress.isEmpty()) {
            etFutsalAddress.setError("Required");
            return false;
        } else {
            etFutsalAddress.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String regEmail = etFutsalEmail.getEditText().getText().toString().trim();
        if (regEmail.isEmpty()) {
            etFutsalEmail.setError("Required");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(regEmail).matches()) {
            etFutsalEmail.setError("Please enter a valid email");
            return false;
        } else {
            etFutsalEmail.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String regPhone = etFutsalPhone.getEditText().getText().toString().trim();
        if (regPhone.isEmpty()) {
            etFutsalPhone.setError("Required");
            return false;
        } else if (regPhone.length() > 11) {
            etFutsalPhone.setError("Please enter a valid phone number");
            return false;
        } else {
            etFutsalPhone.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String regPassword = etFutsalPassword.getEditText().getText().toString().trim();
        if (regPassword.isEmpty()) {
            etFutsalPassword.setError("Required");
            return false;
        } else if (!RegistrationLogic.PASSWORD_PATTERN.matcher(regPassword).matches()) {
            etFutsalPassword.setError("Password is too weak");
            return false;
        } else {
            etFutsalPassword.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String regCpassword = etFutsalCPassword.getEditText().getText().toString().trim();
        if (!regCpassword.equals(etFutsalPassword.getEditText().getText().toString().trim())) {
            etFutsalCPassword.setError("Password does not match");
            return false;
        } else if (regCpassword.isEmpty()) {
            etFutsalCPassword.setError("Required");
            return false;
        } else {
            etFutsalCPassword.setError(null);
            return true;
        }
    }

    private boolean validateOpeningTime() {
        String regOpeningTime = etFutsalOpeningTime.getEditText().getText().toString().trim();

        if (regOpeningTime.isEmpty()) {
            etFutsalOpeningTime.setError("Required");
            return false;
        } else {
            etFutsalOpeningTime.setError(null);
            return true;
        }
    }

    private boolean validateClosingTime() {
        String regClosingTime = etFutsalClosingTime.getEditText().getText().toString().trim();

        if (regClosingTime.isEmpty()) {
            etFutsalClosingTime.setError("Required");
            return false;
        } else {
            etFutsalClosingTime.setError(null);
            return true;
        }
    }

    private boolean validatePrice() {
        String regPrice = etFutsalPrice.getEditText().getText().toString().trim();

        if (regPrice.isEmpty()) {
            etFutsalPrice.setError("Required");
            return false;
        } else if (regPrice.length() > 4) {
            etFutsalPrice.setError("Too expensive");
            return false;
        } else {
            etFutsalPrice.setError(null);
            return true;
        }
    }


    private void initialize() {
        futsalOpeningTime = findViewById(R.id.futsalOpeningTime);
        futsalClosingTime = findViewById(R.id.futsalClosingTime);
        etFutsalName = findViewById(R.id.etFutsalName);
        etFutsalAddress = findViewById(R.id.etFutsalAddress);
        etFutsalEmail = findViewById(R.id.etFutsalEmail);
        etFutsalPhone = findViewById(R.id.etFutsalPhone);
        etFutsalPassword = findViewById(R.id.etFutsalPassword);
        etFutsalCPassword = findViewById(R.id.etFutsalCPassword);
        etFutsalOpeningTime = findViewById(R.id.etFutsalOpeningTime);
        etFutsalClosingTime = findViewById(R.id.etFutsalClosingTime);
        etFutsalPrice = findViewById(R.id.etFutsalPrice);
        btnFutsalNext = findViewById(R.id.btnFutsalNext);
    }
}
