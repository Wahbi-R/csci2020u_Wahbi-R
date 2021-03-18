package sample;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import java.io.*;
import java.util.Scanner;

import org.apache.commons.csv.*;

public class Controller {
    private static Color[] pieColours = {Color.LIGHTSKYBLUE, Color.YELLOW, Color.ORANGE, Color.PINK};

    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() throws FileNotFoundException {
        GraphicsContext bgc = canvas.getGraphicsContext2D();

        drawPieGraph(bgc);
    }




    //Bars for average housing prices
    private void drawPieGraph(GraphicsContext bgc) throws FileNotFoundException {
        //Defining colours
        bgc.setFill(Color.LIGHTSKYBLUE);
        bgc.fillRect(30, 50, 50, 25);
        bgc.setStroke(Color.BLACK);
        bgc.strokeRect(30, 50, 50, 25);
        bgc. strokeText("FLASH FLOOD", 85, 67);

        bgc.setFill(Color.YELLOW);
        bgc.fillRect(30, 50+40, 50, 25);
        bgc.setStroke(Color.BLACK);
        bgc.strokeRect(30, 50+40, 50, 25);
        bgc. strokeText("SEVERE THUNDERSTORM", 85, 67+40);

        int n = 2;
        bgc.setFill(Color.ORANGE);
        bgc.fillRect(30, 50+40*n, 50, 25);
        bgc.setStroke(Color.BLACK);
        bgc.strokeRect(30, 50+40*n, 50, 25);
        bgc. strokeText("SPECIAL MARINE", 85, 67+40*n);

        n = 3;
        bgc.setFill(Color.PINK);
        bgc.fillRect(30, 50+40*n, 50, 25);
        bgc.setStroke(Color.BLACK);
        bgc.strokeRect(30, 50+40*n, 50, 25);
        bgc. strokeText("TORNADO", 85, 67+40*n);

        int floods = 0;
        int thunderstorms = 0;
        int marine = 0;
        int tornado = 0;
        int total = 0;
        Scanner sc = new Scanner(new File("weatherwarnings-2015.csv"));
        sc.useDelimiter(",");
        while(sc.hasNext()){
            String temp = sc.next();
            if(temp.contains("FLASH FLOOD")){
                total++;
                floods += 1;
            }
            if(temp.contains("SEVERE THUNDERSTORM")){
                total++;
                thunderstorms += 1;
            }
            if(temp.contains("SPECIAL MARINE")){
                total++;
                marine += 1;
            }
            if(temp.contains("TORNADO")){
                total++;
                tornado += 1;
            }
        }
        int[] events = {floods, thunderstorms, marine, tornado};
        //pie chart for purchases by age group
        double angle1 = 0;
        for(int i = 0; i< events.length; i++){
            double slicePercentage = (double) events[i] / total;
            double angle2 = slicePercentage * 360;
            bgc.setFill(pieColours[i]);
            bgc.fillArc(350, 20, 300, 300, angle1, angle2, ArcType.ROUND);
            bgc.setStroke(Color.BLACK);
            bgc.strokeArc(350, 20, 300, 300, angle1, angle2, ArcType.ROUND);

            angle1 += angle2;
        }
    }

}
