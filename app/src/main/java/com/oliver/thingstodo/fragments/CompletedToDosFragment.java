package com.oliver.thingstodo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oliver.thingstodo.R;
import com.oliver.thingstodo.TestModel;
import com.oliver.thingstodo.TestingViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompletedToDosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompletedToDosFragment extends Fragment {

    RecyclerView completedTasks;
    TestingViewAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CompletedToDosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompletedToDosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompletedToDosFragment newInstance(String param1, String param2) {
        CompletedToDosFragment fragment = new CompletedToDosFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_completed_to_dos, container, false);

        ArrayList<TestModel> tasks = new ArrayList<>();

        tasks.add(new TestModel("Important Todos Title is Awesome", "No description", "January 12", false));
        tasks.add(new TestModel("Important Todos Title is Awesome", "No description", "January 12", false));
        tasks.add(new TestModel("Important Todos Title is Awesome", "No description", "January 12", false));
        tasks.add(new TestModel("Important Todos Title is Awesome", "No description", "January 12", false));

        completedTasks = view.findViewById(R.id.completedtasks_recycler_view);

        adapter = new TestingViewAdapter(this.getContext(),tasks);

        completedTasks.setAdapter(adapter);
        completedTasks.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }
}