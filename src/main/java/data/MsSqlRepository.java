package data;

import domain.Todo;
import util.TodoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MsSqlRepository implements TodoRepository {

    private static final String SQL_GET_TODOS = "select * from dbo.todos";
    private static final String SQL_GET_TODO = "select * from dbo.todos where id like ?";
    private static final String SQL_ADD_TODO = "insert into dbo.todos (description, importance) values (?, ?);";
    private static final String SQL_UPDATE_TODO_DESCRIPTION = "update dbo.todos set description = ? where id like ?";
    private static final String SQL_UPDATE_TODO_IMPORTANCE = "update dbo.todos set importance = ? where id like ?";
    private static final String SQL_DELETE_TODO = "delete from dbo.todos where id = ?";

    //dirty, problems when multithreading!
    private static final String SQL_GET_LAST_ID = "select top(1) id from dbo.todos order by id desc";

    @Override
    public int addTodo(Todo t) {
        try (
                Connection con = MsSqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_ADD_TODO);
        ) {
            System.out.println("Adding todo");
            prep.setString(1, t.getDescription());
            prep.setInt(2, t.getImportance());
            prep.executeUpdate();
            //now get the id of the inserted item
            try(PreparedStatement prep2 = con.prepareStatement(SQL_GET_LAST_ID)){
                try(ResultSet rs = prep2.executeQuery()){
                    while(rs.next()){
                        return rs.getInt("id");
                    }
                }
            }
        } catch (SQLException ex) {
            throw new TodoException("Unable to add todo to DB", ex);
        }
        return 0;
    }

    @Override
    public void deleteTodo(Todo t) {
        try (
                Connection con = MsSqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_DELETE_TODO);
        ) {
            //TODO: get the id from the selected Todo
            //t.getId() works for the initialized items, but not for later added ones
            System.out.println("Deleting todo with id: " + t.getId());
            prep.setInt(1, t.getId());
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw new TodoException("Unable to add todo to DB", ex);
        }
    }

    @Override
    public void updateTodo(Todo t) {
        //TODO: implement
    }

    @Override
    public Todo getTodo(int id) {
        return null;
    }

    @Override
    public ArrayList<Todo> getTodos() {
        ArrayList<Todo> todos = new ArrayList<>();
        try (
                Connection con = MsSqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_GET_TODOS);
        ) {
            try (ResultSet rs = prep.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String description = rs.getString("description");
                    int importance = rs.getInt("importance");
                    int done = rs.getInt("done");
                    //convert int to bool for column done
                    //dirty conversion
                    Todo t = new Todo(id, description, importance, done == 1);
                    todos.add(t);
                }
                return todos;
            }

        } catch (SQLException ex) {
            throw new TodoException("Can't fetch todos from DB", ex);
        }
    }
}
