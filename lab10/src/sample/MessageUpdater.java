package sample;

import javafx.scene.control.TextArea;

public class MessageUpdater {
    public static TextArea text;
    public static String messages = "";
    public MessageUpdater(TextArea text){
        MessageUpdater.text = text;
    }
    public static void updateText(String message){
        messages += message + "\n" + "\n";
        text.setText(messages);
    }

    public static TextArea getText(){
        return text;
    }
}
