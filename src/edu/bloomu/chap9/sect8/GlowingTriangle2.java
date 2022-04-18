package edu.bloomu.chap9.sect8;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.scene.paint.Color.*;

/**
 * Displays a triangle with linear gradient and fade transition.
 * The corners are selected by the user through mouse clicks. The triangle can be dragged
 * with the mouse
 *
 * @author Nathaniel Bladen
 */
public class GlowingTriangle2 extends Application {

    private final Polygon triangle = new Polygon();

    // offset of cursor location from start of drag operation
    private double cursorX;
    private double cursorY;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        final int size = 300;
        Scene scene = new Scene(root, size, size, getGradient(BLACK,WHITE));

        // Add a drop shadow
        DropShadow dropShadow = new DropShadow(6.0, 6.0, 6.0, GRAY);
        triangle.setEffect(dropShadow);


        class MouseClickListener implements EventHandler<MouseEvent> {
            @Override
            public void handle(MouseEvent mouseEvent) {

                //add a point to polygon at location of click
                double x = mouseEvent.getX();
                double y = mouseEvent.getY();
                triangle.getPoints().addAll(x,y);
                // if fewer than three points have been added, draw a dot at the location
                // of the click. Otherwise, remove the two dots, remove this event handler
                // and add the triangle to the scene.
                if (triangle.getPoints().size() < 6) {
                    Circle dot = new Circle(x, y, 4);
                    dot.setFill(BLACK);
                    root.getChildren().add(dot);
                } else {
                    root.getChildren().clear();
                    triangle.setFill(getGradient(FORESTGREEN,NAVY));
                    getTransition().play();
                    root.setOnMouseClicked(null);
                    root.getChildren().add(triangle);
                }
            }
        }
        MouseClickListener mouseClickListener = new MouseClickListener();
        root.setOnMouseClicked(mouseClickListener);


        class MousePressedHandler implements EventHandler<MouseEvent>{
            @Override
            public void handle(MouseEvent mouseEvent) {
                cursorX = mouseEvent.getSceneX() - cursorX;
                cursorY = mouseEvent.getSceneY() - cursorY;
                triangle.setCursor(Cursor.DEFAULT);
            }
        }
        triangle.setOnMousePressed(new MousePressedHandler());

        class MouseDraggedHandler implements EventHandler<MouseEvent>{
            @Override
            public void handle(MouseEvent mouseEvent) {
                triangle.setTranslateX(mouseEvent.getSceneX() - cursorX);
                triangle.setTranslateY(mouseEvent.getSceneY() - cursorY);
                triangle.setCursor(Cursor.DEFAULT);
            }
        }
        triangle.setOnDragDetected(new MouseDraggedHandler());

        class MouseReleasedHandler implements EventHandler<MouseEvent>{
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        }
        triangle.setOnMouseReleased(new MouseReleasedHandler());

        stage.setTitle("Glowing Triangle");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Returns a linear gradient
     *
     */
    private static LinearGradient getGradient(Color start, Color end){
        Stop[] stops = new Stop[2];
        stops[0] = new Stop(0.0,start);
        stops[1] = new Stop(1,end);

        //(0,0) and (1,1) are scaled coordinates with respect to bounding box of shape to
        //which gradient is applied
        return new LinearGradient(0.0, 0.0, 1.0, 1.0, true,
                CycleMethod.REFLECT, stops);
    }

    /**
     * Returns a fade transition configured for the triangle
     */
    private FadeTransition getTransition() {
        Duration duration = Duration.millis(1500);
        FadeTransition ft = new FadeTransition(duration, triangle);
        ft.setFromValue(1.0); // Fully opaque
        ft.setToValue(0.5); // Half opaque
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        return ft;
    }
        public static void main (String[]args){
            launch(args);
        }

    }


