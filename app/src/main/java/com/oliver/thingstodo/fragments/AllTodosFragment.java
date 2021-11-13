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
 * Use the {@link AllTodosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllTodosFragment extends Fragment {

    RecyclerView allTasks;
    TestingViewAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllTodosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllTodosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllTodosFragment newInstance(String param1, String param2) {
        AllTodosFragment fragment = new AllTodosFragment();
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
        View view = inflater.inflate(R.layout.fragment_all_todos, container, false);

        ArrayList<TestModel> tasks = new ArrayList<>();

        tasks.add(new TestModel("All Todos Title is Awesome", "No description", "January 12", false));
        tasks.add(new TestModel("All Todos Title is Awesome", "No description", "January 12", false));
        tasks.add(new TestModel("All Todos Title is Awesome", "No description", "January 12", false));
        tasks.add(new TestModel("All Todos Title is Awesome", "No description", "January 12", false));

        allTasks = view.findViewById(R.id.alltodo_recycler_view);

        adapter = new TestingViewAdapter(this.getContext(),tasks);

        allTasks.setAdapter(adapter);
        allTasks.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;


    }
}