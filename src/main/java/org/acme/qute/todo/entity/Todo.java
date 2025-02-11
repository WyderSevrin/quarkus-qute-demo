package org.acme.qute.todo.entity;



public class Todo {


    public int id;
    public String title;
    public int priority;
    public boolean completed;


    public Todo() {
        this.id = 1;
        this.title = "asas";
        this.priority = 1;
        this.completed = false;
    }


    public Todo(int id, String title, int priority, boolean completed) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    public static int count(){
        return 10;
    }
}
