package com.oliver.thingstodo.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.oliver.thingstodo.Model.TaskModel;
import com.oliver.thingstodo.Utils.TaskRoomDatabase;

import java.util.Date;
import java.util.List;

public class ThingsTodoRepository {

    private final TaskDao taskDao;
    private final LiveData<List<TaskModel>> allTasks;


    public ThingsTodoRepository(Application application) {
        TaskRoomDatabase database = TaskRoomDatabase.getDatabase(application);
        taskDao = database.taskDao();

        allTasks = taskDao.getAllTasks();
    }

    public LiveData<List<TaskModel>> getAllTasks(){
        return allTasks;
    }

    public LiveData<TaskModel> getTask(long id){
        return taskDao.getTask(id);
    }

    public LiveData<List<TaskModel>> getTodayTasks(String date){
        return taskDao.getTodayTasks(date);
    }

    public LiveData<List<TaskModel>> getImportantTasks(boolean isImportant){
        return taskDao.getImportantTasks(isImportant);
    }

    public LiveData<List<TaskModel>> getCompletedTasks(boolean isDone){
        return taskDao.getCompletedTasks(isDone);
    }

    public void insert(TaskModel taskModel){
        TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.insertTask(taskModel));
    }

    public void update(TaskModel taskModel){
        TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.update(taskModel));
    }

    public void delete(TaskModel taskModel){
        TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.delete(taskModel));
    }
}
