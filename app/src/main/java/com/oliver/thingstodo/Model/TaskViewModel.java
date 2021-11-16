package com.oliver.thingstodo.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.oliver.thingstodo.Data.ThingsTodoRepository;

import java.util.Date;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    public static ThingsTodoRepository repository;
    public final LiveData<List<TaskModel>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new ThingsTodoRepository(application);
        allTasks = repository.getAllTasks();
    }

    public LiveData<List<TaskModel>> getAllTasks(){
        return allTasks;
    }

    public LiveData<TaskModel> getTask(long id){
        return repository.getTask(id);
    }

    public LiveData<List<TaskModel>> getTodayTasks(String date){
        return repository.getTodayTasks(date);
    }

    public LiveData<List<TaskModel>> getImportantTasks(boolean isImportant){
        return repository.getImportantTasks(isImportant);
    }

    public LiveData<List<TaskModel>> getCompletedTasks(boolean isDone){
        return repository.getCompletedTasks(isDone);
    }

    public static void insert(TaskModel task){
        repository.insert(task);
    }

    public static void update(TaskModel task){
        repository.update(task);
    }

    public static void delete(TaskModel task){
        repository.delete(task);
    }
}
