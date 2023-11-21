package com.example.hikecw.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hikecw.Model.ObservationModel;
import com.example.hikecw.R;

import java.util.ArrayList;

public class ObservationAdapter extends RecyclerView.Adapter<ObservationAdapter.ModuleHolderObservation>{
    ArrayList<ObservationModel> observationModels;

    Context context;

    public ObservationAdapter(ArrayList<ObservationModel> observationModels, Context context) {
        this.observationModels = observationModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ObservationAdapter.ModuleHolderObservation onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.observation_card,parent,false);
        return new ModuleHolderObservation(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObservationAdapter.ModuleHolderObservation holder, int position) {
        ObservationModel o = observationModels.get(position);
        holder.observationName.setText(String.valueOf(o.getObservationName()));
        holder.observationTime.setText(String.valueOf(o.getTime()));
        holder.observationComment.setText(String.valueOf(o.getComment()));
    }

    @Override
    public int getItemCount() {
        return observationModels.size();
    }

    public static class ModuleHolderObservation extends RecyclerView.ViewHolder {
        TextView observationName, observationTime, observationComment;

        public ModuleHolderObservation(@NonNull View itemView) {
            super(itemView);
            observationName = itemView.findViewById(R.id.observationName);
            observationTime = itemView.findViewById(R.id.observationTime);
            observationComment = itemView.findViewById(R.id.observationComment);
        }
    }
}
