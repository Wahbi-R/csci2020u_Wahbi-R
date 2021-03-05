package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Controller {
    @FXML
    private String studId;
    @FXML
    private String studName;
    @FXML
    private float midtermGrade;
    @FXML
    private float assignGrade;
    @FXML
    private float finalExam;
    @FXML
    private float finalMark;
    @FXML
    private String letterGrade;
    @FXML
    private TableView tabView;

    @FXML
    private void initialize() {
        tabView.setItems(DataSource.getAllMarks());
    }
}
