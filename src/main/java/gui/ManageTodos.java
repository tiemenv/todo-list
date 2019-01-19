package gui;

import data.Repositories;
import domain.Todo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.TodoException;

import java.io.IOException;
import java.net.URL;
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

    public static ObservableList<Todo> todoObservableList;

    @FXML
    void initialize() {
        //TODO: sort by importance
        ArrayList<Todo> todos = Repositories.getInstance().getTodoRepository().getTodos();
        todoObservableList = FXCollections.observableList(todos);
        lstTodos.setItems(todoObservableList);
    }

    @FXML
    void addTodo(ActionEvent event) {
        openAddTodoPane();
    }

    public static void tryToAddTodo(Todo t){
        try {
            todoObservableList.add(new Todo(Repositories.getInstance().getTodoRepository().addTodo(t), t.getDescription(), t.getImportance(), t.isDone()));
        } catch(TodoException ex){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText(ex.getMessage());
            al.showAndWait();
        }
    }

    private void openAddTodoPane(){
        try{
            URL fxmlURL = ClassLoader.getSystemResource("fxml/AddTodo.fxml");
            FXMLLoader detailLoader = new FXMLLoader(fxmlURL);

            Pane root = detailLoader.load();
            AddTodo addTodo = detailLoader.getController();

            Scene scene = new Scene(root);

            Stage stage = new Stage();

            addTodo.setStage(stage);
            stage.setScene(scene);

            stage.showAndWait();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }



    @FXML
    void deleteTodo(ActionEvent event) {
        int tIndex = lstTodos.getSelectionModel().getSelectedIndex();
        Todo t = lstTodos.getSelectionModel().getSelectedItem();
        try{
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
        int tIndex = lstTodos.getSelectionModel().getSelectedIndex();
        Todo t = lstTodos.getSelectionModel().getSelectedItem();
        TextInputDialog tid = new TextInputDialog();
        tid.setContentText("Update todo description");
        Optional<String> descriptionOpt = tid.showAndWait();

        if (descriptionOpt.isPresent()) {
            String description = descriptionOpt.get();
            Todo updatedTodo = new Todo(t.getId(), description, t.getImportance(), t.isDone());
            todoObservableList.set(tIndex, updatedTodo);
        }
    }

}


