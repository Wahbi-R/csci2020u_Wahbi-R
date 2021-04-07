package sample;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    Socket socket;
    Stage stage;
    TextArea text;
    public ServerThread(Socket socket, TextArea text) {
        super();
        this.socket = socket;
        this.stage = stage;
        this.text = text;
    }

    public void run() {
        String message = null;
        try {
            while(!socket.isClosed()) message = readMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readMessage() throws IOException {
        String message = text.getText();
        try {
            System.out.println("test read");
            InputStream inputStream = socket.getInputStream();

            //Create DataInputStream to read from
            DataInputStream dataInputStream = new DataInputStream(inputStream);


            //Read message
            message = dataInputStream.readUTF();

            MessageUpdater.updateText(message);

            //System.out.println(message);
        }catch(IOException e){
            System.out.println("User has left the server");
            socket.close();
        }
        return message;
    }
}
