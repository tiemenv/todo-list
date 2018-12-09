package data;

import data.MsSqlConnection;
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

    @Override
    public void addTodo(Todo t) {
        try (
                Connection con = MsSqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_ADD_TODO)
        ) {
            prep.setString(1, t.getDescription());
            prep.setInt(2, t.getImportance());
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw new TodoException("Unable to add book to DB", ex);
        }

    }

    @Override
    public void deleteTodo(Todo t) {

    }

    @Override
    public void updateTodo(Todo t) {

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
                    String description = rs.getString("description");
                    int importance = rs.getInt("importance");
                    int done = rs.getInt("done");
                    //convert int to bool for column done
                    Todo t = new Todo(description, importance, done == 1);
                    todos.add(t);
                }
                return todos;
            }

        } catch (SQLException ex) {
            throw new TodoException("Can't fetch todos from DB", ex);
        }
    }
}
