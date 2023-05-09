package com.example.chemicalplant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList company_id, company_name, company_address, company_year, company_chemical;



    CustomAdapter(Activity activity,Context context,
                  ArrayList company_id,
                  ArrayList company_name,
                  ArrayList company_address,
                  ArrayList company_year,
                  ArrayList company_chemical){
        this.activity = activity;
        this.context = context;
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_address = company_address;
        this.company_year = company_year;
        this.company_chemical = company_chemical;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.company_id_txt.setText(String.valueOf(company_id.get(position)));
        holder.company_name_txt.setText(String.valueOf(company_name.get(position)));
        holder.company_address_txt.setText(String.valueOf(company_address.get(position)));
        holder.company_year_txt.setText(String.valueOf(company_year.get(position)));
        holder.company_chemical_txt.setText(String.valueOf(company_chemical.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(company_id.get(position)));
                intent.putExtra("company name", String.valueOf(company_name.get(position)));
                intent.putExtra("address", String.valueOf(company_address.get(position)));
                intent.putExtra("year established", String.valueOf(company_year.get(position)));
                intent.putExtra("chemical", String.valueOf(company_chemical.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return company_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView company_id_txt, company_name_txt, company_address_txt, company_year_txt, company_chemical_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            company_id_txt = itemView.findViewById(R.id.company_id_txt);
            company_name_txt = itemView.findViewById(R.id.company_name_txt);
            company_address_txt = itemView.findViewById(R.id.company_address_txt);
            company_year_txt = itemView.findViewById(R.id.company_year_txt);
            company_chemical_txt = itemView.findViewById(R.id.company_chemical_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                ArrayList<String> filteredList = new ArrayList<>();
                if (charSequence == null || charSequence.length() == 0) {
                    filteredList.addAll(company_name);
                } else {
                    String filterPattern = charSequence.toString().toLowerCase().trim();
                    for (Object nameObject : company_name) {
                        String name = (String) nameObject;
                        if (name.toLowerCase().contains(filterPattern)) {
                            filteredList.add(name);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                company_name.clear();
                company_name.addAll((ArrayList<String>) filterResults.values);
                notifyDataSetChanged();
            }
        };
        return filter;
    }


}