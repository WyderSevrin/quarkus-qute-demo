package org.acme.qute.todo.control;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.qute.todo.entity.Todo;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TodoService {


    List<Todo> todos = new ArrayList<>();


    public void add(Todo todo){
        this.todos.add(todo);
    }

    public void delete(Todo todo){
        this.todos.remove(todo);
    }

    public List<Todo> read(){
        return todos;
    }

    public Todo find(int id){
        return this.todos.stream().filter(todo -> todo.id == id).findFirst().orElse(todos.get(0));
    }


    public int count(){
        return this.todos.size();
    }


}
