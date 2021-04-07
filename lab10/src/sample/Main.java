package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Main extends Application {
    Socket socket = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        Label userNameLabel = new Label("Username:");
        TextField userNameText = new TextField();
        Label messageLabel = new Label("Message:");
        TextField messageText = new TextField();

        Button sendButton = new Button("Send");
        Button exitButton = new Button("Exit");

        root.setVgap(10);
        root.setHgap(10);
        root.add(userNameLabel, 0, 0);
        root.add(userNameText, 1, 0);
        root.add(messageLabel, 0, 1);
        root.add(messageText, 1, 1);
        root.add(sendButton, 0, 2);
        root.add(exitButton, 0, 3);

        primaryStage.setTitle("Lab 10 Solutions");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        socket = new Socket("localhost", 8080);
        OutputStream out = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(out);

        //Set up send button
        sendButton.setOnAction(e -> {
            try {
                String message = userNameText.getText() + ": " + messageText.getText();
                connectToServer(dataOutputStream, message);
                System.out.println(message);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        //Set up exit button
        exitButton.setOnAction(e -> {
            try {
                exitApplication();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    private void exitApplication() throws IOException {
        if(!socket.isClosed()) socket.close();
        System.exit(1);
    }

    private void serverClosedError(){
        Stage stage = new Stage();
        Text error = new Text("The server is closed!");
        GridPane err = new GridPane();
        err.setAlignment(Pos.CENTER);
        err.add(error,0,0);
        stage.setScene(new Scene(err, 200, 100));
    }

    private void connectToServer(DataOutputStream dataOutputStream, String message) throws IOException {
        if(socket.isClosed()) {
            serverClosedError();
            return;
        }
        //Write message
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
