package com.oliver.thingstodo.Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<TaskModel> selectedTask = new MutableLiveData<>();
    private boolean isEdit;

    public void setSelectedTask(TaskModel task){
        selectedTask.setValue(task);
    }

    public LiveData<TaskModel> getSelectedTask(){
        return selectedTask;
    }

    public void setIsEdit(boolean isEdit){
        this.isEdit = isEdit;
    }

    public boolean getIsEdit(){
        return isEdit;
    }

}
