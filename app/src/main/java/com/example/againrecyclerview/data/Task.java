package com.example.againrecyclerview.data;

public class Task {
    private int mId;
    private String mTaskName;
    private String mTaskPriority;

    public Task(String mTaskName, String mTaskPriority) {
        this.mTaskName = mTaskName;
        this.mTaskPriority = mTaskPriority;
    }

    public Task(int mId, String mTaskName, String mTaskPriority) {
        this.mId = mId;
        this.mTaskName = mTaskName;
        this.mTaskPriority = mTaskPriority;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTaskName() {
        return mTaskName;
    }

    public void setmTaskName(String mTaskName) {
        this.mTaskName = mTaskName;
    }

    public String getmTaskPriority() {
        return mTaskPriority;
    }

    public void setmTaskPriority(String mTaskPriority) {
        this.mTaskPriority = mTaskPriority;
    }
}
