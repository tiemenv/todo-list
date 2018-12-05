import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class Gui {

    @FXML
    private ListView<Todo> lstTodos;

    @FXML
    private Button btnNew;

    private ObservableList<Todo> todoObservableList;

    @FXML
    void initialize(){
        ArrayList<Todo> todos = Repositories.getInstance().getTodoRepository().getTodos();
        todoObservableList = FXCollections.observableList(todos);
        lstTodos.setItems(todoObservableList);
        System.out.println("init");
    }

    @FXML
    void addTodo(ActionEvent event) {
        System.out.println("Add todo");
    }

}
