package data;

import domain.Todo;
import util.TodoException;

import java.util.ArrayList;

public class MockupRepository implements TodoRepository {

    private final ArrayList<Todo> todos;

    public MockupRepository(){
        todos = new ArrayList<>();
        todos.add(new Todo("testTodo", 10));
        todos.add(new Todo("domain.Todo from mockuprepo", 5));
        todos.add(new Todo("Very important", 30));
        todos.add(new Todo("Not that important", 2));
    }

    @Override
    public int addTodo(Todo t) {
        boolean added = todos.add(t);
        if(!added) {
            throw new TodoException("Failed to add todo");
        }
        return 0;
    }

    @Override
    public void deleteTodo(Todo t) {
        todos.remove(t);
    }

    @Override
    public void updateTodo(Todo t) {

    }

    @Override
    public Todo getTodo(int id) {
        for (Todo t : this.getTodos()){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Todo> getTodos() {
        return todos;
    }
}
