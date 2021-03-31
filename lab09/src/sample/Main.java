package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main extends Application {
    //Defining variables
    int nSize = 700;
    private Canvas canvas = new Canvas(nSize, nSize);
    GraphicsContext bgc = canvas.getGraphicsContext2D();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Lab 09: Stock Performance");

        //Making lists to store the prices
        ArrayList<Float> closePricesGOOG = new ArrayList<Float>();
        ArrayList<Float> closePricesAAPL = new ArrayList<Float>();

        //Downloading the prices into the premade lists
        closePricesGOOG = downloadStockPrices("GOOG");
        closePricesAAPL = downloadStockPrices("AAPL");

        //drawing the comparison graph
        drawLinePlot(closePricesGOOG, closePricesAAPL);

        //Setting up the scene
        Group root = new Group();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, nSize, nSize));
        primaryStage.show();
    }

    public ArrayList<Float> downloadStockPrices(String company){ //Function to download stock prices and export closing
                                                                    //to a array list of floats
        ArrayList<Float> closePrices = new ArrayList<Float>();
        try{
            String sURL = "https://query1.finance.yahoo.com/v7/finance/download/" + company + "?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";
            URL netURL = new URL(sURL);

            //Making a bufferedReader to read the csv as well as open it
            BufferedReader readCSV = new BufferedReader(new InputStreamReader(netURL.openStream()));
            String s = null;
            //Reading every line and putting the closing stock in an array
            while ((s=readCSV.readLine())!=null) {
                List<String> ls = Arrays.asList(s.split(","));
                s = ls.get(5);
                if(s.equals("Adj Close") == false) closePrices.add(Float.parseFloat(s));
            }
            //For testing purpose
            System.out.println("Size of closePrices = " + closePrices.size());

        } catch (Exception e){
            e.printStackTrace();
        }
        return closePrices;
    }
    public void drawLinePlot(ArrayList<Float> line1, ArrayList<Float> line2){ //Function to compare two stocks on line graph
        bgc.strokeLine(50, nSize-50, 50, 50);
        bgc.strokeLine(50, nSize-50, nSize-50, nSize-50);
        plotLine(line1, Color.RED);
        plotLine(line2, Color.BLUE);
    }

    public void plotLine(ArrayList<Float> prices, Color colour){ //Function to graph a stock
        bgc.setStroke(colour);
        int n = 0;
        int n2 = 0;
        for(int i = 0; i<prices.size()-1; i++){
            n = 7;
            int x1 = i+1+100;
            int x2 = i+1+100;
            bgc.strokeLine((i*n)+50, nSize-50-prices.get(i)/2, ((i+1)*n)+50, nSize-50-prices.get(i+1)/2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
