package edu.bloomu.chap9.sect8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * @author Nathaniel Bladen
 */
public class FlatEarth extends Application {

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        final int size = 300;
        Scene scene = new Scene(root, size, size, Color.BLACK);

        Circle world = new Circle()

        stage.setTitle("");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
