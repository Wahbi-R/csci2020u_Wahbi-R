package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 700+50, 340+50));
        Label label = new Label("Red bars: Average Housing Prices Per Year\nBlue bars: Average Commercial Prices Per Year\nAqua: 18-25\nGold: 26-35\nDark Orange: 36-45\nDark Salmon: 46-55\nLawn Green: 56-65\nPlum: 65+");
        Stage legend = new Stage();
        legend.setTitle("Legend");
        legend.setScene(new Scene(label, 400, 150));
        legend.setX(1325);
        legend.setY(205);
        primaryStage.show();
        legend.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
