package edu.bloomu.chap9.sect6;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/**
 * Displays a picture of a bird song selected by the user and plays an audio clip of the
 * bird's song
 *
 * Border pane is used in the homework
 *
 * Different way of doing the button handling for picking which picture is displayed
 *             public void handle(ActionEvent actionEvent) {
 *                 for (int i = 0; i < birds.length; i++){
 *                     if (radioButtons[i].isSelected()){
 *                         currentImage.setImage(images[i]);
 *
 *                     } else {
 *                         clips[i].stop();
 *                     }
 *                 }
 *             }
 *
 * @author Nathaniel Bladen
 */
public class BirdSong extends Application {

    private static final String[] birds = {
            "American_Goldfinch", "American_Robin", "Baltimore_Oriole",
            "Black-Capped_Chickadee", "Carolina_Wren", "Eastern_Bluebird",
            "Field_Sparrow", "White-Breasted_Nuthatch"

    };

    private static final Image[] images = new Image[birds.length];
    private static final AudioClip[] clips = new AudioClip[birds.length];


    // initialize images and audio clips
    static {
        for (int i = 0; i < birds.length; i++){
            images[i] = new Image("images/" + birds[i] + ".jpg");
            // for audio clips you have to use a specific format
            clips[i] = new AudioClip("file:resources/audio/" + birds[i] + ".mp3");
        }
    }

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);

        // Control nodes
        ImageView currentImage = new ImageView(images[0]);
        RadioButton[] radioButtons = new RadioButton[birds.length];
        ToggleGroup toggleGroup = new ToggleGroup();
        Button playButton = new Button("Play song");

        // Two children of the root node
        FlowPane playButtonFlowPane = new FlowPane();
        HBox hbox = new HBox(10);

        //Initialize the flow plane containing the play button
        playButtonFlowPane.getChildren().add(playButton);
        playButtonFlowPane.setAlignment(Pos.CENTER);
        //padding looks in  clockwise starting from the top
        playButtonFlowPane.setPadding(new Insets(10, 0, 10, 0));
        root.setBottom(playButtonFlowPane);

        //Initialize a hBox to hold image and vBox with radio buttons
        VBox radioButtonVBox = new VBox(22);
        hbox.getChildren().addAll(currentImage, radioButtonVBox);
        hbox.setPadding(new Insets(10));
        root.setLeft(hbox);

        class RadioButtonHandler implements EventHandler<ActionEvent>{

            private final int index;

            public RadioButtonHandler (int index) {
                this.index = index;
            }
            @Override
            public void handle(ActionEvent actionEvent){
                currentImage.setImage(images[index]);
                for (AudioClip clip: clips){
                    clip.stop();
                }
            }

        }
        //RadioButtonHandler handler = new RadioButtonHandler();

        //Create radio buttons and register the event handlers.
        for (int i = 0; i < images.length; i++){
            radioButtons[i] = new RadioButton(birds[i].replaceAll("_", " "));
            radioButtons[i].setOnAction(new RadioButtonHandler(i));
            toggleGroup.getToggles().add(radioButtons[i]);
            radioButtonVBox.getChildren().add(radioButtons[i]);

        }
        // A lot of people miss this on homework
        radioButtons[0].setSelected(true);

        class ButtonHandler implements EventHandler<ActionEvent>{
            @Override
            public void handle(ActionEvent actionEvent) {
                for (int i = 0; i < birds.length; i++){
                    if (radioButtons[i].isSelected()) {
                        clips[i].play();
                    } else {
                        clips[i].stop();
                    }
                }
            }
        }

        playButton.setOnAction(new ButtonHandler());

        stage.setTitle("Bird Song");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
