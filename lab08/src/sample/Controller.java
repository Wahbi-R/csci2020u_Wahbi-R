package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

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

    private String currentFilename = "";
    private Stage stage;

    @FXML
    private void initialize() {
        tabView.setItems(DataSource.getAllMarks());
        //Menu buttons/items

        MenuBar mainMenu = new MenuBar();
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");
        MenuItem exitItem = new MenuItem("Exit");
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(newItem, openItem, saveItem, saveAsItem, exitItem);
        mainMenu.getMenus().add(fileMenu);

        VBox root = new VBox();
        root.getChildren().addAll(mainMenu);
    }
    public void setStage(Stage stage){
        this.stage=stage;
    }
    private void save(){
       // DataSource.
    }

    public void handleNewAction(ActionEvent event) {
        tabView.getItems().clear();
    }

    public void handleOpenAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(stage);
    }

    public void handleSaveAction(ActionEvent event) {
    }

    public void handleSaveAsAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", ".csv"));
        fileChooser.showSaveDialog(stage);
    }

    public void handleExitAction(ActionEvent event) {
        stage.close();
    }

}
