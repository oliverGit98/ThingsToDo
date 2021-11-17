package com.oliver.thingstodo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

                TaskViewModel.insert(myTask);

            }

            todoTitle.setText("");
            description.setText("");

            if(this.isVisible()){
                this.dismiss();
            }

        });
    }
}
