package com.example.mydailyjournal.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydailyjournal.R;
import com.example.mydailyjournal.model.Model;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater layoutInflater;
    List<Model> models;

    public Adapter(Context context, List<Model> models){
        this.layoutInflater = LayoutInflater.from(context);
        this.models = models;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = layoutInflater.inflate(R.layout.list_view, parent, false);

      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int i) {
        String title, date, time;

        title = models.get(i).getTitle();
        date = models.get(i).getDate();
        time = models.get(i).getTime();
        holder.tvTitle.setText(title);
        holder.tvDate.setText(date);
        holder.tvTime.setText(time);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvDate, tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
