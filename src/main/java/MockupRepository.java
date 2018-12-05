import java.util.ArrayList;

public class MockupRepository implements TodoRepository {

    private final ArrayList<Todo> todos;

    public MockupRepository(){
        todos = new ArrayList<>();
        todos.add(new Todo("testTodo", 10));
        todos.add(new Todo("Todo from mockuprepo", 5));
        todos.add(new Todo("Very important", 30));
        todos.add(new Todo("Not that important", 2));
    }

    @Override
    public void addTodo(Todo t) {
        boolean added = todos.add(t);
        if(!added) {
            throw new TodoException("Failed to add todo");
        }
    }

    @Override
    public void deleteTodo(Todo t) {
        todos.remove(t);
    }

    @Override
    public void updateTodo(Todo t) {
        //TODO: implement
    }

    @Override
    public Todo getTodo(int id) {
        for (Todo t : this.getTodos()){
            //Todo: no equals required? Just comparing 2 ints?
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
