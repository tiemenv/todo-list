package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddTodo {

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;
    private Stage stage;

    @FXML
    void addTodo(ActionEvent event) {

    }

    @FXML
    void cancelTodo(ActionEvent event) {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
