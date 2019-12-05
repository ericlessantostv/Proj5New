package bsu.comp152;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
@Override
    public void start(Stage primaryStage) {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        Scene windowContents = new Scene(box, 300, 400);
        primaryStage.setScene(windowContents);
        primaryStage.setTitle("Project 5");
        primaryStage.setResizable(false);
        primaryStage.show();
        Button button1 = new Button("Makeup");
        button1.setPrefSize(130,30);
        button1.setTranslateY(-100);
        box.getChildren().add(button1);
        Button button2 = new Button("Star Wars");
        button2.setPrefSize(130,30);
        button2.setTranslateY(-70);
        box.getChildren().add(button2);
        /*
        EventHandler<javafx.event.ActionEvent> handler = new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                VBox starBox = new VBox();
                Stage starWindow = new Stage();
                Scene newContents = new Scene(starBox, 300, 400);
                starWindow.setScene(newContents);
                starWindow.setTitle("Star Wars");
                starWindow.show();
            }
        };

         */
        button2.setOnAction(this::openStarsWarsWindow);

    }
    @FXML
    public  void openStarsWarsWindow(ActionEvent event){
        Parent root = null;
        var loc = getClass().getResource("StarWarsWindow.fxml");
        try {
            root = FXMLLoader.load(loc);
        }catch (IOException e){
            System.out.println("Couldn't Find FXML file!!!!!!");
        }
        Scene windowContents = new Scene(root, 900,400);
        Stage recipeWindow = new Stage();
        recipeWindow.setScene(windowContents);
        recipeWindow.setTitle("Star Wars");
        recipeWindow.show();
    }

}
