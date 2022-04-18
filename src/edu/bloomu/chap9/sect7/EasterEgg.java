package edu.bloomu.chap9.sect7;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Displays an egg. Its shape and angle of rotation can be man by key presses
 * Clicking the mouse on either the egg or the background
 *
 * @author Nathaniel Bladen
 */
public class EasterEgg extends Application {

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        final int width = 500;
        final int height = 300;
        Scene scene = new Scene(root, width, height, Color.DEEPPINK);

        //Create the egg and add it to the root node
        final int hRadius = 170;
        final int vRadius = 100;
        final Ellipse egg = new Ellipse(hRadius, vRadius);
        egg.setFill(Color.NAVY);
        egg.setRotate(45);

        root.getChildren().add(egg);
        /**
         * The arrow keys stretch and shrink the egg. F11 and F12 rotate it. The space
         * bar turns it into a circle
         */
        class KeyHandler implements EventHandler<KeyEvent>{
            @Override
            public void handle(KeyEvent keyEvent) {
                final double radiusX = egg.getRadiusX();
                final double radiusY = egg.getRadiusY();
                final double angle = egg.getRotate();

                final int minRadius = 10;
                final int deltaRadius = 2; // change in radius
                final int deltaAngle = 10; // change in angle

                KeyCode code = keyEvent.getCode();
                switch (code){
                    case LEFT: //decrement horizontal radius
                        egg.setRadiusX(Math.max(minRadius, radiusX - deltaRadius));
                        break;
                    case RIGHT: //increment horizontal radius
                        egg.setRadiusX((Math.max(minRadius, radiusX + deltaRadius)));
                        break;
                    case UP: //increment vertical radius
                        egg.setRadiusY((Math.max(minRadius, radiusY + deltaRadius)));
                        break;
                    case DOWN: //decrement vertical radius
                        egg.setRadiusY((Math.max(minRadius, radiusY - deltaRadius)));
                        break;
                    case SPACE: //change egg to circle
                        double n = Math.min(radiusX, radiusY);
                        egg.setRadiusX(n);
                        egg.setRadiusY(n);
                        break;
                    case F12: // rotate angle
                        egg.setRotate(angle + deltaAngle);
                        break;
                    case F11: // rotate other angle
                        egg.setRotate(angle - deltaAngle);
                        break;
                }

            }
        }
        scene.setOnKeyPressed(new KeyHandler());
        /**
         * Clicking the mouse on the egg or the background will randomize either of thier
         * Colors
         */
        class MouseHandler implements EventHandler<MouseEvent>{
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getSource() == egg){
                    egg.setFill(randomColor());
                    mouseEvent.consume();
                } else {
                    scene.setFill(randomColor());
                }
            }

            private static Color randomColor() {
                ThreadLocalRandom rand = ThreadLocalRandom.current();
                double r = rand.nextDouble();
                double g = rand.nextDouble();
                double b = rand.nextDouble();
                return new Color(r,g,b,1);
            }
        }
        MouseHandler  mouseHandler = new MouseHandler();
        egg.setOnMouseClicked(mouseHandler);
        scene.setOnMouseClicked(mouseHandler);



        stage.setTitle("Easter Egg");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
