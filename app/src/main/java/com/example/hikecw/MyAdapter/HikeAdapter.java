package com.example.hikecw.MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hikecw.Activity.EditHike;
import com.example.hikecw.Activity.ListPlan;
import com.example.hikecw.Activity.ViewDetail;
import com.example.hikecw.Database.HikeDB;
import com.example.hikecw.Model.HikeModel;
import com.example.hikecw.R;

import java.util.ArrayList;

public class HikeAdapter extends RecyclerView.Adapter<HikeAdapter.ModuleHolderHike> implements Filterable {
    Context context;
    ArrayList<HikeModel> HikeModel;
    ArrayList<HikeModel> HikeModelOld;

    public HikeAdapter(Context context, ArrayList<HikeModel> hikeModel, ArrayList<HikeModel> HikeModelOld) {
        this.context = context;
        this.HikeModel = hikeModel;
        this.HikeModelOld = HikeModelOld;
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
        HikeModel md = HikeModel.get(position);
        holder.txtHikeName.setText(String.valueOf(md.getHikeName()));
        holder.txtLevel.setText(String.valueOf(md.getLevel()));
        holder.txtLength.setText(String.valueOf(md.getLength()));
        holder.txtLocation.setText(String.valueOf(md.getLocation()));
        holder.txtDate.setText(String.valueOf(md.getDate()));
        holder.dropdownMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, holder.dropdownMenu);
                popupMenu.inflate(R.menu.flow_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.dropdown_menu_edit) {
                            Intent i = new Intent(view.getContext(), EditHike.class);
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
                        } else {
                            Toast.makeText(context,"Delete success", Toast.LENGTH_SHORT).show();
                            HikeDB db = new HikeDB(context);
                            db.deleteHike(md.getId());
                            Intent intent = new Intent(view.getContext(), ListPlan.class);
                            view.getContext().startActivity(intent);
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
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
        return HikeModel.size();
    }

    public class ModuleHolderHike extends RecyclerView.ViewHolder {
        TextView txtHikeName, txtLevel, txtLength, txtLocation, txtDate;
        CardView cardshow;
        ImageButton dropdownMenu;
        public ModuleHolderHike(@NonNull View itemView) {
            super(itemView);
            txtHikeName = (TextView) itemView.findViewById(R.id.txtHikeName);
            txtLevel = (TextView) itemView.findViewById(R.id.txtLevel);
            txtLength = (TextView) itemView.findViewById(R.id.txtLength);
            txtLocation = (TextView) itemView.findViewById(R.id.txtLocation);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            cardshow = (CardView) itemView.findViewById(R.id.cardView);
            dropdownMenu = itemView.findViewById(R.id.flowmenu);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()) {
                    HikeModel = HikeModelOld;
                } else {
                    ArrayList<HikeModel> list = new ArrayList<>();
                    for (HikeModel hike: HikeModelOld) {
                        if(hike.getHikeName().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(hike);
                        }
                        if(hike.getLocation().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(hike);
                        }
                        if(hike.getDate().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(hike);
                        }
                        if(hike.getWeather().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(hike);
                        }
                    }
                    HikeModel = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = HikeModel;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                HikeModel = (ArrayList<HikeModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
