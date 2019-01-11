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

    @FXML
    private Button editTodo;

    @FXML
    private Button deleteTodo;

    private ObservableList<Todo> todoObservableList;

    @FXML
    void initialize() {
        ArrayList<Todo> todos = Repositories.getInstance().getTodoRepository().getTodos();
        todoObservableList = FXCollections.observableList(todos);
        lstTodos.setItems(todoObservableList);
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
            //TODO: we need to somehow assign and know the id of the added todo
            todoObservableList.add(t);
        } catch(TodoException ex){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText(ex.getMessage());
            al.showAndWait();
        }
    }

    @FXML
    void deleteTodo(ActionEvent event) {
        int tIndex = lstTodos.getSelectionModel().getSelectedIndex();
        Todo t = lstTodos.getSelectionModel().getSelectedItem();
        try{
            //TODO: how do we grab the actual todo item from the selection in the observable list?
            Repositories.getInstance().getTodoRepository().deleteTodo(t);
            todoObservableList.remove(tIndex);
        } catch(TodoException ex){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText(ex.getMessage());
            al.showAndWait();
        }
    }

    @FXML
    void editTodo(ActionEvent event) {
        System.out.println("editTodo");
    }
}


