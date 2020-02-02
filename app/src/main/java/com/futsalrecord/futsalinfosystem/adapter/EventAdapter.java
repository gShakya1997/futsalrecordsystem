package com.futsalrecord.futsalinfosystem.adapter;


import android.app.usage.UsageEvents;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.model.Events;
import com.futsalrecord.futsalinfosystem.strictMode.StrictModeClass;
import com.futsalrecord.futsalinfosystem.url.Url;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private Context context;
    private List<Events> eventsList;

    public EventAdapter(Context context, List<Events> eventsList) {
        this.context = context;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventview, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        final Events events = eventsList.get(position);
        String imgPath = Url.imagePath + events.getEventImage();
        StrictModeClass.StrictMode();
        try {
            URL url = new URL(imgPath);
            holder.eventImage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.tvEventName.setText(events.getEventName());
        holder.tvEntryFee.setText(events.getEntryFee());
        holder.tvEventDetail.setText(events.getEventDetail());
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        ImageView eventImage;
        TextView tvEventName, tvEventDetail, tvEntryFee;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventImage = itemView.findViewById(R.id.eventImage);
            tvEventName = itemView.findViewById(R.id.tvEventName);
            tvEventDetail = itemView.findViewById(R.id.tvEventDetail);
            tvEntryFee = itemView.findViewById(R.id.tvEntryFee);
        }
    }
}
