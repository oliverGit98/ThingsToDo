package com.oliver.thingstodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class TestingViewAdapter extends RecyclerView.Adapter<TestingViewAdapter.ViewHolder> {

    private List<TestModel> todos = new ArrayList<>();
    Context mContext;
    public TestingViewAdapter(Context context,ArrayList<TestModel> todos) {
        this.mContext = context;
        this.todos = todos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(todos.get(position).getTitle());
        holder.date.setText(todos.get(position).getDate());
//        holder.radio.setChecked(todos.get(position).isDone());
        //holder.date.setText(todos.get(position).getDate());
        //holder.description.setText(todos.get(position).getDescription());

        if(todos.get(position).isDone()){
            holder.radio.setChecked(true);
        }else{
            holder.radio.setChecked(false);
        }


//        if(todos.get(position).isExpanded()){
//            TransitionManager.beginDelayedTransition(holder.parent);
//            holder.expandableLayout.setVisibility(View.VISIBLE);
//            holder.expand.setVisibility(View.GONE);
//        }
//        else{
//            TransitionManager.beginDelayedTransition(holder.parent);
//            holder.expandableLayout.setVisibility(View.GONE);
//            holder.collaps.setVisibility(View.VISIBLE);
//        }


    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

//    public void setList(ArrayList<TestModel> todos) {
//        this.todos = todos;
//        notifyDataSetChanged();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView parent;
        TextView title, description;
        Chip date;
        ImageButton expand, collaps;
        ToggleButton important, radio;
        //ConstraintLayout expandableLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent_layout);
            title = itemView.findViewById(R.id.todo_title);
            radio = itemView.findViewById(R.id.todo_radio_button);
            date = itemView.findViewById(R.id.date_chip);


            radio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(radio.isChecked()){
                        TestModel model = todos.get((getAdapterPosition()));
                        model.setIsDone(!model.isDone());
                        notifyItemChanged(getAdapterPosition());
                    }
                    else{
                        TestModel model = todos.get((getAdapterPosition()));
                        model.setIsDone(!model.isDone());
                        notifyItemChanged(getAdapterPosition());
                    }
                }
            });

//            parent = itemView.findViewById(R.id.parent);
//            title = itemView.findViewById(R.id.title);
//           // description = itemView.findViewById(R.id.description);
//            date = itemView.findViewById(R.id.date);
//            //date = itemView.findViewById(R.id.date_chip);
//
//            expand = itemView.findViewById(R.id.button_expand);
//            //collaps = itemView.findViewById(R.id.collaps_button);
//            //important = itemView.findViewById(R.id.toggle_important);
//            //expandableLayout = itemView.findViewById(R.id.exapand_layout);


//            expand.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    TestModel testModel = todos.get(getAdapterPosition());
//                    testModel.setExpanded(!testModel.isExpanded());
//                    notifyItemChanged(getAdapterPosition());
//                }
//            });
//
//            collaps.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    TestModel testModel = todos.get(getAdapterPosition());
//                    testModel.setExpanded(!testModel.isExpanded());
//                    notifyItemChanged(getAdapterPosition());
//                }
//            });


        }
    }
}
