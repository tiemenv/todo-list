package gui;

import data.Repositories;
import domain.Todo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import util.TodoException;

public class AddTodo {

    @FXML
    private TextField txtDescription;

    @FXML
    private ToggleGroup priority;

    @FXML
    private RadioButton radioLow;

    @FXML
    private RadioButton radioNormal;

    @FXML
    private RadioButton radioHigh;

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;
    private Stage stage;

    @FXML
    void addTodo(ActionEvent event) {
        System.out.println(priority.getSelectedToggle().toString());
        Todo t = new Todo(txtDescription.getCharacters().toString());
        ManageTodos.tryToAddTodo(t);
        System.out.println(txtDescription.getCharacters().toString());
        stage.close();
    }


    @FXML
    void cancelTodo(ActionEvent event) {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
