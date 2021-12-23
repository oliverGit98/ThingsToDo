package com.oliver.thingstodo.Adapter;

import com.oliver.thingstodo.Model.TaskModel;

public interface OnRecyclerViewClickListener {

    void onCompleteButtonClick(TaskModel task);
    void onImportantButtonClick(TaskModel task);

    void onTodoLayoutClick(TaskModel currTask);
}
