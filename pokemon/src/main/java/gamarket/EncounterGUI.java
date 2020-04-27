package gamarket;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.File;

public class EncounterGUI  implements EventHandler<ActionEvent> {
    private Button move1, move2, move3, move4, exit;
    private BorderPane ap;
    private Stage window;
    private boolean done;

    public BorderPane display() {
        //BorderPane ap = new BorderPane(FXMLLoader.load(getClass().getResource("sample.fxml")));
        window = new Stage();
        window.setResizable(false);
        ap = new BorderPane();
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.BOTTOM_LEFT);
        ap.setPadding(new Insets(0, 0, 50, 125));
        ap.setPrefSize(1250, 750);

        // Flareon gif source
        String path = "https://vignette.wikia.nocookie.net/pokemon/images/0/0c/Flareon-AttackAnimation-XY-3.gif/revision/latest?cb=20160822140919";

        // Gengar gif source
        //String path = "https://vignette.wikia.nocookie.net/pokemon/images/4/40/Gengar_XY.gif/revision/latest?cb=20140901010937";

        // Pikachu gif source
        String path2 = "https://vignette.wikia.nocookie.net/pokemon/images/1/18/Pikachu-F_SS.gif/revision/latest?cb=20200107220953";

        // Background source
        File file = new File("./pokemon/imgs/battle_background.jpg");
        String path3 = file.toURI().toString();

        Image image = new Image(path);
        Image image2 = new Image(path2);
        Image image3 = new Image(path3);

        // flareon
        ImageView myImage = new ImageView(image);
        myImage.setX(755);
        myImage.setY(30);

        // pikachu
        ImageView myImage2 = new ImageView(image2);
        myImage2.setY(425);
        myImage2.setX(225);

        // background
        ImageView myImage3 = new ImageView(image3);
        myImage.setFitHeight(450);
        myImage.setFitWidth(450);
        myImage2.setFitHeight(175);
        myImage2.setFitWidth(175);
        myImage3.setFitHeight(300);
        myImage3.setFitWidth(300);


        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

        ap.setBackground(new Background(new BackgroundImage(image3,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));

        //ap.setBackground(background2);

        // BUttons
        move1 = new Button("Electric Ball");
        move1.setOnAction(this::handle);
        move2 = new Button("Thunder");
        move2.setOnAction(this::handle);
        move3 = new Button("Quick Attack");
        move3.setOnAction(this::handle);
        move4 = new Button("Slam");
        move4.setOnAction(this::handle);
        exit = new Button("Exit");

        hbox.getChildren().addAll(move1, move2, move3, move4,exit);
        ap.setBottom(hbox);
        ap.getChildren().add(myImage);
        ap.getChildren().add(myImage2);

        done = false;
        // Create a scene and place it in the stage

        return ap;

    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     * @param
     */

    @Override
    public void handle(ActionEvent event)
    {
        if(event.getSource() == move1)
        {
            // move 1 yea...
            System.out.println("Electro Ball clicked.");
            File file = new File("C:\\Users\\etern\\Desktop\\test.gif");
            String path = file.toURI().toString();
            Image image = new Image(path);
            ImageView iv = new ImageView(image);
            //iv.setBlendMode(BlendMode.SOFT_LIGHT);
            //iv.setBlendMode(BlendMode.);


            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.seconds(3));
            transition.setFromX(225);
            transition.setFromY(425);
            transition.setToX(885);
            transition.setToY(225);
            transition.setAutoReverse(true);
            transition.setNode(iv);
            transition.play();
            ap.getChildren().add(iv);

            //iv.setBlendMode(BlendMode.SCREEN);


        }
        else if(event.getSource() == move2)
        {
            // move 2 yea...
            System.out.println("Thunder clicked.");
        }
        else if(event.getSource() == move3)
        {
            // move 3 yea...
            System.out.println("Quick Attack clicked.");
        }
        else if(event.getSource() == move4)
        {
            // move 4 yea...
            System.out.println("Slam clicked.");
        }
        else if(event.getSource() == exit){
            System.out.println("exit pressed");
            done = true;
        } // do nothing
    }

    public boolean getDone() {
        return done;
    }
}