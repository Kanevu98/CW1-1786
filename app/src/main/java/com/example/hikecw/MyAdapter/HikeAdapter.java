package com.example.hikecw.MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hikecw.Activity.ViewDetail;
import com.example.hikecw.Module.HikeMD;
import com.example.hikecw.R;

import java.util.ArrayList;

public class HikeAdapter extends RecyclerView.Adapter<HikeAdapter.ModuleHolderHike> {
    Context context;
    ArrayList<HikeMD> HikeMD;

    public HikeAdapter(Context context, ArrayList<com.example.hikecw.Module.HikeMD> hikeMD) {
        this.context = context;
        this.HikeMD = hikeMD;
    }

    @NonNull
    @Override
    public HikeAdapter.ModuleHolderHike onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_data,parent,false);
        return new ModuleHolderHike(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HikeAdapter.ModuleHolderHike holder, int position) {
        HikeMD md = HikeMD.get(position);
        holder.txtHikeName.setText(String.valueOf(md.getHikeName()));
        holder.txtLevel.setText(String.valueOf(md.getLevel()));
        holder.txtLength.setText(String.valueOf(md.getLength()));
        holder.txtLocation.setText(String.valueOf(md.getLocation()));
        holder.txtDate.setText(String.valueOf(md.getDate()));
        holder.cardshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ViewDetail.class);
                i.putExtra("id",md.getId());
                i.putExtra("name",md.getHikeName());
                i.putExtra("location",md.getLocation());
                i.putExtra("date",md.getDate());
                i.putExtra("length",md.getLength());
                i.putExtra("level",md.getLevel());
                i.putExtra("description",md.getDescription());
                i.putExtra("parking",md.getParking());
                i.putExtra("rate",md.getRate());
                i.putExtra("weather",md.getWeather());

                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return HikeMD.size();
    }

    public class ModuleHolderHike extends RecyclerView.ViewHolder {
        TextView txtHikeName, txtLevel, txtLength, txtLocation, txtDate;
        CardView cardshow;
        public ModuleHolderHike(@NonNull View itemView) {
            super(itemView);
            txtHikeName = (TextView) itemView.findViewById(R.id.txtHikeName);
            txtLevel = (TextView) itemView.findViewById(R.id.txtLevel);
            txtLength = (TextView) itemView.findViewById(R.id.txtLength);
            txtLocation = (TextView) itemView.findViewById(R.id.txtLocation);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            cardshow = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}
