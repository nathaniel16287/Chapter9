package edu.bloomu.chap9.sect6;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * Suppose a spaceship races away from the earth at constant velocity v for time t
 * according to a clock on the spaceship/ Einstein's Special Theory of Relativity tells
 * how much time u will have elapsed on the earth: t^2 / u^2 = 1 - (v^2 / c^2)
 * <p>
 * Displays the time elapesed on earth given v and t from the user
 * <p>
 * This version handles user input errors and styles the text
 *
 * @author Nathaniel Bladen
 */
public class TimeDilator2 extends Application {

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        final int width = 450;
        final int height = 125;
        Scene scene = new Scene(root, width, height);

        final int hvGap = 10;
        root.setHgap(hvGap);
        root.setVgap(hvGap);
        root.setAlignment(Pos.CENTER);

        // Create and initialize the nodes to be added to the scene
        final double defaultVelocity = 0.95;
        Label vLabel = new Label("Velocity as a fraction of light speed");
        TextField vTextField = new TextField("" + defaultVelocity);

        final double defaultTime = 10;
        Label tLabel = new Label("Travel time in years");
        TextField tTextField = new TextField("" + defaultTime);

        Button button = new Button("Elapsed Time on Earth");
        String initialLabelText = getElapsedTime(defaultVelocity, defaultTime);
        Label resultLabel = new Label(initialLabelText);

        //place nodes in the grid pane
        root.add(vLabel, 0, 0);
        root.add(vTextField, 1, 0);
        root.add(tLabel, 0, 1);
        root.add(tTextField, 1, 1);
        root.add(button, 0, 2);
        root.add(resultLabel, 1, 2);

        // Create style strings with text in the labels
        String base = "-fx-font-size: 12px; -fx-font-weight:bold;";
        String normal = base + "-fx-text-fill: #aa00a4";
        String error = base + "-fx-text-fill: #aa0000";

        // Set style for all nodes
        vTextField.setStyle(normal);
        vLabel.setStyle(normal);
        tTextField.setStyle(normal);
        tLabel.setStyle(normal);
        button.setStyle(normal);
        resultLabel.setStyle(normal);

        class ButtonHandler implements EventHandler<ActionEvent> {

            @Override
            public void handle(ActionEvent actionEvent) {
                String vText = vTextField.getText();
                String tText = tTextField.getText();

                // the following code we are about to write can be simplified using
                // exception handling techniques, also the error handling could be
                // improved with more specific messages about the nature of the error

                // check for empty fields
                if (vText.isBlank() || tText.isBlank()){
                    setLabel(resultLabel, error, "Missing Input, " +
                            "Lol you really are Maidenless");
                    return;
                }

                // check for non-empty non-numeric input
                if (canNotBeParsed(vText) || canNotBeParsed(tText)){
                    setLabel(resultLabel,error, "Non-numeric, " +
                            "plus maidenless");
                    return;
                }

                // check for invalid numbers
                double v = Double.parseDouble(vText);
                double t = Double.parseDouble(tText);
                if (t < 0 || v < 0 || v >= 1){
                    setLabel(resultLabel, error, "Invalid input" +
                            " still maidenless");
                    return;
                }
                // Input is valid, so update return label
                setLabel(resultLabel, normal, getElapsedTime(v,t));

            }
        }
        button.setOnAction(new ButtonHandler());

        stage.setTitle("Time Dilator");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Returns true if a given string can be parsed as a double
     */
    private static boolean canNotBeParsed(String str){
        Scanner in = new Scanner(str);
        return !in.hasNextDouble();
    }

    /**
     * Sets the text on a given label and applies a given style.
     */
    private static void setLabel(Label label, String style, String text ){
        label.setStyle(style);
        label.setText(text);
    }

    /**
     * Calculates time dilation for an object moving at constant velocity v (given as
     * fraction of light speed) for time t (in years)
     */
    private static String getElapsedTime(double v, double t) {
        double u = Math.sqrt(t * t / (1 - v * v));
        return String.format("%.2f years", u);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
