package com.oliver.thingstodo.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oliver.thingstodo.Adapter.RecyclerViewAdapter;
import com.oliver.thingstodo.Model.SharedViewModel;
import com.oliver.thingstodo.Model.TaskModel;
import com.oliver.thingstodo.Model.TaskViewModel;
import com.oliver.thingstodo.R;
import com.oliver.thingstodo.Utils.Utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayFragment extends Fragment {

    private TaskViewModel taskViewModel;
    private RecyclerView todayTasksRecyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SharedViewModel sharedViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TodayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodayFragment newInstance(String param1, String param2) {
        TodayFragment fragment = new TodayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        Date date = Calendar.getInstance().getTime();
        String today = Utils.formatToday(date);

        todayTasksRecyclerView = view.findViewById(R.id.today_recycler_view);
        todayTasksRecyclerView.setHasFixedSize(true);
        todayTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        taskViewModel.getTodayTasks(today).observe(getViewLifecycleOwner(), taskModels -> {

            recyclerViewAdapter = new RecyclerViewAdapter(taskModels);
            todayTasksRecyclerView.setAdapter(recyclerViewAdapter);
        });

        return view;
    }
}