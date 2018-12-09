package gui;

import data.Repositories;
import domain.Todo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.TodoException;

import java.util.ArrayList;
import java.util.Optional;

public class ManageTodos {

    @FXML
    private ListView<Todo> lstTodos;

    @FXML
    private Button btnNew;

    private ObservableList<Todo> todoObservableList;

    @FXML
    void initialize() {
        ArrayList<Todo> todos = Repositories.getInstance().getTodoRepository().getTodos();
        todoObservableList = FXCollections.observableList(todos);
        lstTodos.setItems(todoObservableList);
        System.out.println("init");
    }

    @FXML
    void addTodo(ActionEvent event) {

        TextInputDialog tid = new TextInputDialog();
        tid.setContentText("Add todo description");
        Optional<String> descriptionOpt = tid.showAndWait();

        if (descriptionOpt.isPresent()) {
            String description = descriptionOpt.get();

            Todo t = new Todo(description);
            tryToAddTodo(t);


        }
    }

    private void tryToAddTodo(Todo t){
        try {
            Repositories.getInstance().getTodoRepository().addTodo(t);
        } catch(TodoException ex){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText(ex.getMessage());
            al.showAndWait();
        }
    }
}


