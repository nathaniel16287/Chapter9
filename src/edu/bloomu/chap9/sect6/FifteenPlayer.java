package edu.bloomu.chap9.sect6;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * a GUI front end for the game of Fifteen
 *
 * @author Nathaniel Bladen
 */
public class FifteenPlayer extends Application {

    private static final int DIFFICULTY = 6;

    private final Fifteen game = new Fifteen(DIFFICULTY);


    private final Button[][] buttons = new Button[Fifteen.ROWS][Fifteen.COLS];

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root);

        //Arrange the buttons in a grid pane
        GridPane gridPane = new GridPane();
        final int hvGap = 3;
        gridPane.setVgap(hvGap);
        gridPane.setHgap(hvGap);
        gridPane.setStyle("-fx-background-color: BLACK");
        root.getChildren().add(gridPane);

        //
        class ButtonHandler implements EventHandler<ActionEvent>{
            @Override
            public void handle(ActionEvent actionEvent) {
                Button button = (Button) actionEvent.getSource();
                int tile = Integer.parseInt(button.getText());
                game.slide(tile);
                setButtonText();

                if (game.over()) {
                    stage.setAlwaysOnTop(false);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Game Over");
                    alert.setHeaderText("You have solved the puzzle! (pussy)");
                    alert.setContentText("See ya virgin!");
                    alert.showAndWait();
                    System.exit(8);
                }
            }
        }
        ButtonHandler buttonHandler = new ButtonHandler();

        //Creates the buttons, style them, and register event handlers
        final int fontSize = 28;
        final int buttonSize = 80;
        Font font = Font.font("Monotype", FontWeight.BOLD, fontSize);
        String buttonStyle = "-fx-border-color: SLATEGRAY; -fx-border-width: 3";

        for (int i = 0; i < buttons.length; i++){
            for (int j = 0; j <buttons[i].length; j++){
                buttons[i][j] = new Button();
                buttons[i][j].setOnAction(buttonHandler);
                buttons[i][j].setStyle(buttonStyle);
                buttons[i][j].setFont(font);
                buttons[i][j].setPrefSize(buttonSize,buttonSize);
                gridPane.add(buttons[i][j], j, i);
            }
        }
        setButtonText();


        stage.setTitle("Fifteen");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the text on each button to the corresponding tile number.
     *
     */
    private void setButtonText(){
        for (int i = 0; i < buttons.length; i++){
            for (int j = 0; j < buttons[i].length; j++) {
                int tile = game.tileAt(i, j);
                if (tile == Fifteen.EMPTY_SPACE) {
                    buttons[i][j].setVisible(false);
                } else {
                    buttons[i][j].setVisible(true);
                    buttons[i][j].setText("" + tile);
                }
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}
