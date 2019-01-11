package data;

import domain.Todo;

import java.util.ArrayList;

public interface TodoRepository {
    int addTodo(Todo t);
    void deleteTodo(Todo t);
    void updateTodo(Todo t);
    Todo getTodo(int id);
    ArrayList<Todo> getTodos();
}

//TODO: what about a NoSQL repo?
//TODO: h2 repo