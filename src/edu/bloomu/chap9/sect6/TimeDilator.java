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

/**
 * Suppose a spaceship races away from the earth at constant velocity v for time t
 * according to a clock on the spaceship/ Einstein's Special Theory of Relativity tells
 * how much time u will have elapsed on the earth: t^2 / u^2 = 1 - (v^2 / c^2)
 *
 * Displays the time elapesed on earth given v and t from the user
 *
 * @author Nathaniel Bladen
 */
public class TimeDilator extends Application {

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
        String initialLabelText = getElapsedTime(defaultVelocity,defaultTime);
        Label resultLabel = new Label(initialLabelText);

        //place nodes in the grid pane
        root.add(vLabel, 0,0);
        root.add(vTextField, 1,0);
        root.add(tLabel, 0,1);
        root.add(tTextField, 1,1);
        root.add(button, 0,2);
        root.add(resultLabel, 1,2);

        class ButtonHandler implements EventHandler<ActionEvent>{

            @Override
            public void handle(ActionEvent actionEvent) {
                double v = Double.parseDouble(vTextField.getText());
                double t = Double.parseDouble(tTextField.getText());
                String result = getElapsedTime(v,t);
                resultLabel.setText(result);
            }
        }
        button.setOnAction(new ButtonHandler());



        stage.setTitle("Time Dilator");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Calculates time dilation for an object moving at constant velocity v (given as
     * fraction of light speed) for time t (in years)
     *
     */
    private static String getElapsedTime(double v, double t){
        double u = Math.sqrt(t * t / (1-v * v));
        return String.format("%.2f years", u);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
