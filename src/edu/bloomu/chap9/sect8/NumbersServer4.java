package edu.bloomu.chap9.sect8;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Displays a label with a floating point number with a button for randomizing.
 *
 * @version 4 (using lambda)
 *
 * @author Nathaniel Bladen
 */
public class NumbersServer4 extends Application {

    private final Label label = new Label();
    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root);


        //add a button and a label to root node
        Button button = new Button("Server");
        root.add(button, 0, 0);
        root.add(label, 1, 0);
        //This is also needed.
        //randomizeLabel();

        String style = "-fx-font-size: 24; -fx-font-weight: bold; " +
                "-fx-text-fill: #1f2f4f";
        button.setStyle(style);
        label.setStyle(style);

        //button.setOnAction(actionEvent -> randomizeLabel());

        button.setOnAction(e -> {
            ThreadLocalRandom rand = ThreadLocalRandom.current();
            String num = String.format("%1.8f", rand.nextDouble());
            label.setText(num);
        });



        stage.setTitle("Numbers Server");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }
// this works with the other highlighted lambda statement
//    private void randomizeLabel() {
//        ThreadLocalRandom rand = ThreadLocalRandom.current();
//        String num = String.format("%1.8f", rand.nextDouble());
//        label.setText(num);
//    }

    public static void main(String[] args) {
        launch(args);
    }

}
