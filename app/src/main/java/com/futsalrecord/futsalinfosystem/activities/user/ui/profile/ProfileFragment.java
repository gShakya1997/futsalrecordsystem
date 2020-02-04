package com.futsalrecord.futsalinfosystem.activities.user.ui.profile;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.api.UsersAPI;
import com.futsalrecord.futsalinfosystem.model.Users;
import com.futsalrecord.futsalinfosystem.strictMode.StrictModeClass;
import com.futsalrecord.futsalinfosystem.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {
    private ImageView ivProfilePic;
    private TextView tvUsername, tvUserEmail, tvUserPhone, tvUserAddress;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ivProfilePic = root.findViewById(R.id.ivProfilePic);
        tvUsername = root.findViewById(R.id.tvUsername);
        tvUserEmail = root.findViewById(R.id.tvUserEmail);
        tvUserPhone = root.findViewById(R.id.tvUserPhone);
        tvUserAddress = root.findViewById(R.id.tvUserAddress);
        loadCurrentProfile();
        return root;
    }

    private void loadCurrentProfile() {
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<Users> usersCall = usersAPI.getUserDetails(Url.tokenUser);

        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                StrictModeClass.StrictMode();
                try {
                    String username = response.body().getUsername();
                    String email = response.body().getEmail();
                    String phone = response.body().getPhone();
                    String address = response.body().getAddress();

                    String imgPath = Url.imagePath + response.body().getImage();
                    URL url = new URL(imgPath);
                    ivProfilePic.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                    tvUsername.setText(username);
                    tvUserEmail.setText(email);
                    tvUserPhone.setText(phone);
                    tvUserAddress.setText(address);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}