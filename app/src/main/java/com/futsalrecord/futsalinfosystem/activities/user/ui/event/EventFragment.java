package com.futsalrecord.futsalinfosystem.activities.user.ui.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.adapter.EventAdapter;
import com.futsalrecord.futsalinfosystem.api.UsersAPI;
import com.futsalrecord.futsalinfosystem.model.Events;
import com.futsalrecord.futsalinfosystem.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventFragment extends Fragment {
    private RecyclerView eventRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_event, container, false);
        eventRecyclerView = root.findViewById(R.id.eventRecyclerView);
        loadEventData();
        return root;
    }

    private void loadEventData() {
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<List<Events>> eventCall = usersAPI.getEventDetail(Url.tokenUser);

        eventCall.enqueue(new Callback<List<Events>>() {
            @Override
            public void onResponse(Call<List<Events>> call, Response<List<Events>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Events> eventsList = response.body();
                EventAdapter eventAdapter = new EventAdapter(getActivity(), eventsList);
                eventRecyclerView.setAdapter(eventAdapter);
                eventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<Events>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}