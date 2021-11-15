package com.oliver.thingstodo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.oliver.thingstodo.Model.TaskModel;
import com.oliver.thingstodo.R;
import com.oliver.thingstodo.TestingViewAdapter;
import com.oliver.thingstodo.Utils.Utils;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<TaskModel> taskList;

    public RecyclerViewAdapter(List<TaskModel> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TaskModel task = taskList.get(position);
        String formattedDate = Utils.formatDate(task.getDueDate());

        holder.taskTitle.setText(task.getTitle());
        holder.dateChip.setText(formattedDate);

        if(task.isDone()){
            holder.completedButton.setChecked(true);
        }else{
            holder.completedButton.setChecked(false);
        }

        if(task.isImportant()){
            holder.importantButton.setChecked(true);
        }else{
            holder.importantButton.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ToggleButton completedButton;
        public TextView taskTitle;
        public Chip dateChip;
        public ToggleButton importantButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            completedButton = itemView.findViewById(R.id.todo_radio_button);
            taskTitle = itemView.findViewById(R.id.todo_title);
            dateChip = itemView.findViewById(R.id.date_chip);
            importantButton = itemView.findViewById(R.id.important_button);
        }
    }
}
