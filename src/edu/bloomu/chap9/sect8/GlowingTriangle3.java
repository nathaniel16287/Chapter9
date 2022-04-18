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
 * Displays a triangle with a linear gradient and a fade transition.
 * The corners are selected by the user through mouse clicks. The
 * triangle can be dragged with the mouse.
 *
 * @author Drue Coles
 */
public class GlowingTriangle3 extends Application {

    private final Polygon triangle = new Polygon();

    // offset of cursor location from start of drag operation
    private double cursorX;
    private double cursorY;

    @Override
    public void start(Stage stage) {

        // Create an empty black scene and add it to the stage.
        Pane root = new Pane();
        final int size = 300;
        Scene scene = new Scene(root, size, size, PALEGREEN);

        // Add a drop shadow
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetY(6.0);
        dropShadow.setOffsetX(6.0);
        dropShadow.setColor(GRAY);
        triangle.setEffect(dropShadow);

        class MouseClickHandler implements EventHandler<MouseEvent> {

            @Override
            public void handle(MouseEvent event) {

                // Add point to polygon at location of the mouse click.
                double x = event.getX();
                double y = event.getY();
                triangle.getPoints().addAll(x, y);

                // If fewer than three points have been added, draw a
                // dot at the location of the mouse click. Otherwise,
                // remove the two dots, remove this event handler, and
                // add the triangle to the scene.
                if (triangle.getPoints().size() < 6) {
                    Circle dot = new Circle(x, y, 4);
                    dot.setFill(BLACK);
                    root.getChildren().add(dot);
                } else {
                    root.getChildren().clear();
                    triangle.setFill(getLinearGradient(FIREBRICK,
                            YELLOW));
                    getFadeTransition().play();
                    root.setOnMouseClicked(null);
                    root.getChildren().add(triangle);
                }
            }
        }
        MouseClickHandler mouseHandler = new MouseClickHandler();
        root.setOnMouseClicked(mouseHandler);

        triangle.setOnMousePressed(e -> {
            cursorX = e.getSceneX() - cursorX;
            cursorY = e.getSceneY() - cursorY;
            triangle.setCursor(Cursor.CLOSED_HAND);
        });

        triangle.setOnMouseDragged(e -> {
            triangle.setTranslateX(e.getSceneX() - cursorX);
            triangle.setTranslateY(e.getSceneY() - cursorY);
        });

        triangle.setOnMouseReleased((e -> {
            cursorX = e.getSceneX() - cursorX;
            cursorY = e.getSceneY() - cursorY;
            triangle.setCursor(Cursor.DEFAULT);
        }));


        stage.setTitle("Glowing Triangle");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Returns a fade transition configured for the triangle.
     */
    private FadeTransition getFadeTransition() {
        Duration duration = Duration.millis(1500);
        FadeTransition ft = new FadeTransition(duration, triangle);
        ft.setFromValue(1.0); // fully opaque
        ft.setToValue(0.5); // half opaque
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        return ft;
    }

    /**
     * Returns a linear gradient.
     */
    private static LinearGradient getLinearGradient(Color startColor,
                                                    Color endColor) {

        Stop[] stops = new Stop[2];
        stops[0] = new Stop(0.0, startColor);
        stops[1] = new Stop(1.0, endColor);

        // (0, 0) and (1, 1) are scaled coordinates with respect to
        // bounding box of shape to which this gradient is applied.
        return new LinearGradient(0.0, 0.0, 1.0, 1.0, true,
                CycleMethod.NO_CYCLE, stops);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
