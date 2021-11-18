package com.oliver.thingstodo.fragments;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.oliver.thingstodo.Adapter.RecyclerViewAdapter;
import com.oliver.thingstodo.MainActivity;
import com.oliver.thingstodo.Model.SharedViewModel;
import com.oliver.thingstodo.Model.TaskModel;
import com.oliver.thingstodo.Model.TaskViewModel;
import com.oliver.thingstodo.R;

import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImportantToDosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImportantToDosFragment extends Fragment {

    private TaskViewModel taskViewModel;
    private RecyclerView importantTasksRecyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SharedViewModel sharedViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ImportantToDosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImportantToDosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImportantToDosFragment newInstance(String param1, String param2) {
        ImportantToDosFragment fragment = new ImportantToDosFragment();
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
        View view = inflater.inflate(R.layout.fragment_important_to_dos, container, false);

        boolean isImportant = true;

        importantTasksRecyclerView = view.findViewById(R.id.important_tasks_recycler_view);
        importantTasksRecyclerView.setHasFixedSize(true);
        importantTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        taskViewModel.getImportantTasks(isImportant).observe(getViewLifecycleOwner(), taskModels -> {
            recyclerViewAdapter = new RecyclerViewAdapter(taskModels);
            importantTasksRecyclerView.setAdapter(recyclerViewAdapter);
            initSwipe(taskModels);
        });

        return view;
    }

    public void initSwipe(List<TaskModel> taskList){

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                TaskModel currentTask = taskList.get(position);
                switch (direction){
                    case ItemTouchHelper.LEFT:
                        TaskViewModel.delete(currentTask);
                        recyclerViewAdapter.notifyDataSetChanged();
                        Snackbar.make(importantTasksRecyclerView, currentTask.getTitle() +" Deleted", BaseTransientBottomBar.LENGTH_LONG)
                                .setAction("UNDO", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        TaskViewModel.insert(currentTask);
                                        recyclerViewAdapter.notifyDataSetChanged();
                                        recyclerViewAdapter.notifyItemInserted(position);
                                    }
                                }).show();
                        break;

                    case ItemTouchHelper.RIGHT:
                        sharedViewModel.setSelectedTask(currentTask);
                        sharedViewModel.setIsEdit(true);

                        ((MainActivity)getActivity()).showBottomSheet();
                        recyclerViewAdapter.notifyDataSetChanged();

                        break;
                }

            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(ImportantToDosFragment.this.getContext(), R.color.swipeLeft))
                        .addSwipeLeftActionIcon(R.drawable.ic_delete)
                        .setSwipeLeftActionIconTint(R.color.iconColor)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(ImportantToDosFragment.this.getContext(), R.color.swipeRight))
                        .addSwipeRightActionIcon(R.drawable.ic_edit)
                        .setSwipeRightActionIconTint(R.color.iconColor)
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(importantTasksRecyclerView);
    }

}