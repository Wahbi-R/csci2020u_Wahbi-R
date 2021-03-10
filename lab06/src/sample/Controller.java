package sample;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Controller {
    private static double[] avgHousingPricesByYear = {247381.0, 264171.4, 287715.3, 294736.1, 308431.4, 322635.9, 340253.0, 363153.7};
    private static double[] avgCommercialPricesByYear = {1121585.3, 1219479.5, 1246354.2, 1295364.8, 1335932.6, 1472362.0, 1583521.9, 1613246.3};
    private static int[] purchasesByAgeGroup = { 648, 1021, 2453, 3173, 1868, 2247};
    private static Color[] pieColours = { Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        GraphicsContext bgc = canvas.getGraphicsContext2D();

        drawBarGraph(bgc);
    }

    //Bars for average housing prices
    private void drawBarGraph(GraphicsContext bgc) {
        for(int i = 0; i<avgHousingPricesByYear.length; i++) {
            double current = avgHousingPricesByYear[i]/5000;
            double y = 0;
            System.out.println("y: " + y);
            System.out.println(current);
            bgc.setStroke(Color.RED);
            bgc.setFill(Color.RED);
            bgc.fillRect(30 * i, 340-current, 15, current);
        }

        //Bars for average commercial prices
        for(int i = 0; i< avgCommercialPricesByYear.length; i++) {
            double current = avgCommercialPricesByYear[i]/5000;
            double y = 0;
            //y
            System.out.println("y: " + y);
            System.out.println(current);
            bgc.setStroke(Color.BLUE);
            bgc.setFill(Color.BLUE);
            bgc.fillRect(15+(30 * i), 340-current, 15, current);
        }

        int numOfPurchases = 0;
        for(int i : purchasesByAgeGroup){
            numOfPurchases += i;
        }

        //pie chart for purchases by age group
        double angle1 = 0;
        for(int i = 0; i< purchasesByAgeGroup.length; i++){
            double slicePercentage = (double) purchasesByAgeGroup[i] / numOfPurchases;
            double angle2 = slicePercentage * 360;
            bgc.setFill(pieColours[i]);
            bgc.fillArc(350, 20, 300, 300, angle1, angle2, ArcType.ROUND);
            bgc.setStroke(Color.BLACK);
            bgc.strokeArc(350, 20, 300, 300, angle1, angle2, ArcType.ROUND);

            angle1 += angle2;
        }

//        Legend (before bars were messed up)
//        bgc.setStroke(Color.BLUE);
//        bgc.strokeText("Blue: Average Housing Prices Per Year", 400, 10);
//        bgc.setStroke(Color.RED);
//        bgc.strokeText("Red: Average Commercial Prices Per Year", 400, 25);
//        bgc.setStroke(Color.AQUA);
//        bgc.strokeText("Aqua: 18-25", 400, 40);
//        bgc.setStroke(Color.GOLD);
//        bgc.strokeText("Gold: 26-35", 400, 55);
//        bgc.setStroke(Color.DARKORANGE);
//        bgc.strokeText("Dark Orange: 36-45", 400, 70);
//        bgc.setStroke(Color.DARKSALMON);
//        bgc.strokeText("Dark Salmon: 46-55", 400, 85);
//        bgc.setStroke(Color.LAWNGREEN);
//        bgc.strokeText("Lawn Green: 56-65", 400, 100);
//        bgc.setStroke(Color.PLUM);
//        bgc.strokeText("Plum: 65+", 400, 115);
    }

}




