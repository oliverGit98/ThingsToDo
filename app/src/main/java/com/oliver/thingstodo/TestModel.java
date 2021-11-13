package com.oliver.thingstodo;

public class TestModel {

    private String title;
    private String Description;
    private String Date;
    private boolean isExpanded;
    private boolean isDone;

    public TestModel(String title, String description, String date , boolean isDone) {
        this.title = title;
        this.Description = description;
        this.Date = date;
        isExpanded = false;
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "title='" + title + '\'' +
                ", Description='" + Description + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
}
