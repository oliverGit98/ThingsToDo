package com.oliver.thingstodo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.oliver.thingstodo.Model.SharedViewModel;
import com.oliver.thingstodo.Model.TaskModel;
import com.oliver.thingstodo.Model.TaskViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private EditText todoTitle;
    private EditText description;
    private CalendarView calendarView;
    private FloatingActionButton saveFab;
    private Date dueDate;
    Calendar calendar = Calendar.getInstance();

    private SharedViewModel sharedViewModel;
    private boolean isEdit;

    public BottomSheetFragment(){
    }

//    public BottomSheetFragment(SharedViewModel sharedViewModel){
//        this.sharedViewModel = sharedViewModel;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);

        todoTitle = view.findViewById(R.id.enter_todo_title);
        description = view.findViewById(R.id.enter_todo_description);
        calendarView = view.findViewById(R.id.calendar_view);
        saveFab = view.findViewById(R.id.save_todo_fab);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(sharedViewModel.getSelectedTask().getValue() != null){
            isEdit = sharedViewModel.getIsEdit();
            TaskModel task = sharedViewModel.getSelectedTask().getValue();
            todoTitle.setText(task.getTitle());
            description.setText(task.getDescription());
            calendarView.setDate(task.getDueDate().getTime());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
               calendar.clear();
               calendar.setTimeInMillis(0);
               calendar.set(year, month, dayOfMonth, 0 , 0 ,0);
               dueDate = calendar.getTime();

            }
        });

        saveFab.setOnClickListener(view1 -> {
            String title = todoTitle.getText().toString().trim();
            String desc = description.getText().toString().trim();
            if(!TextUtils.isEmpty(title) && dueDate != null){

                TaskModel myTask = new TaskModel(title, desc, dueDate, Calendar.getInstance().getTime(), false, false);

                if(isEdit){
                    TaskModel updatedTask = sharedViewModel.getSelectedTask().getValue();
                    updatedTask.setTitle(title);
                    updatedTask.setDescription(desc);
                    updatedTask.setDueDate(dueDate);

                    TaskViewModel.update(updatedTask);
                    sharedViewModel.setSelectedTask(null);
                    sharedViewModel.setIsEdit(false);
                }
                else{
                    TaskViewModel.insert(myTask);
                }
                todoTitle.setText("");
                description.setText("");

                if(this.isVisible()){
                    this.dismiss();
                }
            }
            else{
                Snackbar.make(saveFab, "Empty Fields", BaseTransientBottomBar.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        todoTitle.setText("");
        description.setText("");
        calendarView.setDate(Calendar.getInstance().getTime().getTime());
        sharedViewModel.setSelectedTask(null);
        sharedViewModel.setIsEdit(false);
    }
}
