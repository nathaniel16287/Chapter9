package edu.bloomu.chap9.sect5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static javafx.scene.paint.Color.*;
/**
 * Each cell of a square grid is filled with a unique color selected at random from a
 * small list of possible colors.
 *
 * @author Nathaniel Bladen
 */
public class ColorfulGrid extends Application {

    private final static Color[] colors = {
            AQUAMARINE ,DARKSLATEGRAY ,DEEPPINK, DODGERBLUE, GOLD, GREEN, YELLOWGREEN,
            INDIGO, LIME, NAVY, ORANGE, PLUM, POWDERBLUE, PURPLE, STEELBLUE, TEAL,
            THISTLE, TOMATO, TURQUOISE, YELLOW
    };
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        final int size = 500;
        Scene scene = new Scene(root, size, size, BLACK);

        final int n = 3; // number of rows and columns
        final float gap = 5; // space between the rows and columns (counted in pixels)
        final float cellSize = (size - (n + 1) * gap) / n;

        Set<Color> colorSet = getColors(n * n);
        if (colorSet == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Not enough colors.");
            alert.setContentText("Terminating");
            alert.showAndWait();
            return;
        }

        // is a combination of the two arrays, uses an empty array to basically generate a new one
       Color[] colorArray = colorSet.toArray(new Color[]{});
        //Color[] colorArray = new Color[colorSet.size()];
        //colorSet.toArray(colorArray);

        // Draw n by n grid of square cells
        int count = 0;
        for (int i = 0; i < n; i ++){
            for (int j = 0; j < n; j++){
                // top-left corner of cell (i,j)
                float x = gap + j * (gap + cellSize);
                float y = gap + i * (gap + cellSize);
                Rectangle cell = new Rectangle(x, y, cellSize, cellSize);
                cell.setFill(colorArray[count++]);
                root.getChildren().add(cell);
            }
        }

        stage.setTitle("Colorful Grid");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Returns a set of n distinct colors selected at random from the colors array
     * of this class. Returns null if there are not enough colors in the array
     */
    private static Set<Color> getColors(int n){
        if (n > colors.length){
            return null; // not enough colors
        }
        Set<Color> colorSet = new HashSet<>();
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        while (colorSet.size() < n){
            int i = rand.nextInt(colors.length);
            colorSet.add(colors[i]);
        }
        return colorSet;
    }
    public static void main(String[] args) {
        launch(args);
    }
}