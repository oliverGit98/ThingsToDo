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
 * Use the {@link TodayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayFragment extends Fragment {

    private RecyclerView recyclerView;
    private TestingViewAdapter adapter;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        ArrayList<TestModel> todos = new ArrayList<>();

        todos.add(new TestModel("This is Title1", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title2", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title3", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title4", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title5", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title6", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title1", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title2", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title3", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title4", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title5", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title6", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title1", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title2", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title3", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title4", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title5", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title6", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title1", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title2", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title3", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title4", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title5", "This is description.","August 08",false));
        todos.add(new TestModel("This is Title6", "This is description.","August 08",false));

        recyclerView = view.findViewById(R.id.today_recycler_view);
        adapter = new TestingViewAdapter(this.getContext(),todos);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }
}