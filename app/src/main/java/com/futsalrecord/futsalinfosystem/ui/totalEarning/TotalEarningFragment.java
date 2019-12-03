package com.futsalrecord.futsalinfosystem.ui.totalEarning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.futsalrecord.futsalinfosystem.R;
import com.squareup.picasso.Picasso;

public class TotalEarningFragment extends Fragment {
    private ImageView imageView;
    private String imageUrl = "https://static.scientificamerican.com/blogs/cache/file/BB6F1FE0-4FDE-4E6E-A986664CE30602E4_source.jpg?w=590&h=800&2F8476C1-DF14-49BA-84FFE94218CC4933";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_total_earning, container, false);
        imageView = root.findViewById(R.id.ivImage);
        Picasso.get().load(imageUrl).into(imageView);
        return root;
    }
}