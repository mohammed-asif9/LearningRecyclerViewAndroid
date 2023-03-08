package com.example.againrecyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.againrecyclerview.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<List<String>> dataList;

    public RecyclerViewAdapter(Context context, List<List<String>> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.taskview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        List<String> data = dataList.get(position);
        holder.getTaskTextView().setText(data.get(0));
        holder.getPriorityTextView().setText(data.get(1));



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         TextView taskTextView;
         TextView priorityTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            taskTextView = itemView.findViewById(R.id.textView);
            priorityTextView = itemView.findViewById(R.id.textView2);
        }

        public TextView getTaskTextView() {
            return taskTextView;
        }

        public TextView getPriorityTextView() {
            return priorityTextView;
        }


        @Override
        public void onClick(View view) {dataList.get(getAdapterPosition());
            Toast.makeText(view.getContext(),"you clicked on "+ dataList.get(getAdapterPosition())
                    ,Toast.LENGTH_SHORT).show();
        }
    }
}
