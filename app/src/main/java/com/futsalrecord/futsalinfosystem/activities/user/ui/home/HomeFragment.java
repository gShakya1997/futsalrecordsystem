package com.futsalrecord.futsalinfosystem.activities.user.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.adapter.FutsalAdapter;
import com.futsalrecord.futsalinfosystem.api.UsersAPI;
import com.futsalrecord.futsalinfosystem.model.FutsalDetails;
import com.futsalrecord.futsalinfosystem.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private RecyclerView futsalRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        futsalRecyclerView = root.findViewById(R.id.futsalRecyclerView);
        loadFutsalData();
        return root;
    }

    private void loadFutsalData() {
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<List<FutsalDetails>> futsalCall = usersAPI.getFutsalDetails(Url.tokenUser);

        futsalCall.enqueue(new Callback<List<FutsalDetails>>() {
            @Override
            public void onResponse(Call<List<FutsalDetails>> call, Response<List<FutsalDetails>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<FutsalDetails> futsalDetailsList = response.body();
                FutsalAdapter futsalAdapter = new FutsalAdapter(getActivity(), futsalDetailsList);
                futsalRecyclerView.setAdapter(futsalAdapter);
                futsalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<FutsalDetails>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}