package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Server extends Application{
     Socket clientSocket = null;
     ServerSocket serverSocket = null;
     ServerThread[] serverThreads;
     int clientTotal = 0;

     int maxClients = 20;

     public void startServer(TextArea messageField) throws IOException{
         MessageUpdater temp = new MessageUpdater(messageField);
         serverSocket = new ServerSocket(8080);
         System.out.println("The Server is Open");
         TextArea finalMessageField = messageField;
         Thread currentThread = new Thread(() -> {
             serverThreads = new ServerThread[maxClients];
             while(true){
                 try {
                     clientSocket = serverSocket.accept();
                     if(clientTotal < maxClients){
                         serverThreads[clientTotal] = new ServerThread(clientSocket, finalMessageField);
                         serverThreads[clientTotal].start();
                         clientTotal++;
                     }else{
                         System.out.println("The maximum number of users have connected.");
                     }
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

             }
         });
         currentThread.start();
     }

    @Override
    public void start(Stage stage) throws Exception {
         String currentMessages = "";
         GridPane root = new GridPane();
         root.setPadding(new Insets(30, 30, 30, 30));

         //Set up box of test
         TextArea messageField = new TextArea(currentMessages);
         messageField.setMinWidth(350);
         messageField.setMinHeight(250);
         root.add(messageField, 0, 0);

         //Set up exit button
         Button exitButton = new Button("Exit");
         root.add(exitButton, 0, 1);
         exitButton.setOnAction(e -> {
             try {
                 exitPress();
             } catch (IOException ioException) {
                 ioException.printStackTrace();
             }
         });

         //Set up scene
         stage.setScene(new Scene(root, 410, 310));
         stage.show();
         //messageField.setText("SOMETHING");
         startServer(messageField);
    }

    private void exitPress() throws IOException {
         for(int i = 0; i<serverThreads.length; i++){
             if(serverThreads[i] != null && !serverThreads[i].socket.isClosed()) serverThreads[i].socket.close();
         }
         System.exit(1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
