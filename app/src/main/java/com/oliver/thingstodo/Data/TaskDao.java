package com.oliver.thingstodo.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.oliver.thingstodo.Model.TaskModel;

import java.util.Date;
import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertTask(TaskModel taskModel);

    @Query("DELETE FROM task_table")
    void deleteAll();

    @Query("SELECT * FROM task_table ORDER BY task_table.due_date ASC")
    LiveData<List<TaskModel>> getAllTasks();

    @Query("SELECT * FROM task_table WHERE task_table.task_id == :id")
    LiveData<TaskModel> getTask(long id);

    @Query("SELECT * FROM task_table WHERE task_table.string_date == :date")
    LiveData<List<TaskModel>> getTodayTasks(String date);

    @Query("SELECT * FROM task_table WHERE task_table.is_important == :isImportant")
    LiveData<List<TaskModel>> getImportantTasks(boolean isImportant);

    @Query("SELECT * FROM task_table WHERE task_table.is_done == :isDone")
    LiveData<List<TaskModel>> getCompletedTasks(boolean isDone);

    @Update
    void update(TaskModel taskModel);

    @Delete
    void delete(TaskModel taskModel);

}
