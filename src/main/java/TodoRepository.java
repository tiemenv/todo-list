import java.util.ArrayList;

public interface TodoRepository {
    void addTodo(Todo t);
    void deleteTodo(Todo t);
    void updateTodo(Todo t);
    Todo getTodo(int id);
    ArrayList<Todo> getTodos();
}

//TODO: how to implement a MSSQL repo?
//TODO: what about a NoSQL repo?