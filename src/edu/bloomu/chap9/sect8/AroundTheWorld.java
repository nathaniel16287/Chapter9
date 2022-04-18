package edu.bloomu.chap9.sect8;

import com.sun.webkit.dom.CSSRuleListImpl;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Animation: a dot moves around a circular path. Clicking the mouse causes the dot to
 * move direction.
 *
 * @author Nathaniel Bladen
 */
public class AroundTheWorld extends Application {

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        final int size = 300;
        Scene scene = new Scene(root,size, size, Color.BLACK);

        //Create a circle that nearly fills the scene.
        Ellipse world = new Ellipse(140, 100);
        world.setStroke(Color.BLACK);
        world.setStrokeWidth(3);
        world.setFill(Color.MEDIUMPURPLE);
        world.setVisible(true);

        // Create a dot that travels around the world.
        Circle dot = new Circle(6);
        dot.setFill(Color.YELLOW);

        Button button = new Button("Click me");

        // Path transition for a dot traveling around the world
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setCycleCount(Transition.INDEFINITE);
        pt.setAutoReverse(false);
        pt.setInterpolator(Interpolator.LINEAR);
        pt.setPath(world);
        pt.setNode(button);
        pt.play();

        world.setOnMouseClicked(e -> pt.setRate(-pt.getRate()));

        root.getChildren().addAll(world,button);


        stage.setTitle("Around the World");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
