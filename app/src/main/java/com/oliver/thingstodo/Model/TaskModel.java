package com.oliver.thingstodo.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.oliver.thingstodo.Utils.Utils;

import java.util.Date;

@Entity(tableName = "task_table")
public class TaskModel {

    @ColumnInfo(name = "task_id")
    @PrimaryKey(autoGenerate = true)
    public long taskId;

    public String title;

    public String description;

    @ColumnInfo(name = "due_date")
    public Date dueDate;

    @ColumnInfo(name = "string_date")
    public String stringDate;

    @ColumnInfo(name = "created_at")
    public Date dateCreated;

    @ColumnInfo(name = "is_important")
    public boolean isImportant;

    @ColumnInfo(name = "is_done")
    public boolean isDone;

    public TaskModel(String title, String description, Date dueDate, Date dateCreated, boolean isImportant, boolean isDone) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.dateCreated = dateCreated;
        this.isImportant = isImportant;
        this.isDone = isDone;
        this.stringDate = Utils.formatToday(dueDate);
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", dateCreated=" + dateCreated +
                ", isImportant=" + isImportant +
                ", idDone=" + isDone +
                '}';
    }
}
