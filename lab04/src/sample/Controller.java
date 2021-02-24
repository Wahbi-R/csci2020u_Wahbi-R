package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private DatePicker dateField;

    public Controller() {
    }

    @FXML
    protected void registerAction(ActionEvent event) {
        System.out.println("Username: " + this.usernameField.getText());
        System.out.println("Password: " + this.passwordField.getText());
        System.out.println("Full Name: " + this.fullNameField.getText());
        System.out.println("E-Mail: " + this.emailField.getText());
        System.out.println("Phone Number: " + this.phoneField.getText().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3"));
        System.out.println("Date of Birth: " + this.dateField.getValue());
    }
}
