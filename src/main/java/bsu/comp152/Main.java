package bsu.comp152;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
        Parent root = null;
        var loc = getClass().getResource("Main.fxml");
        try{
            root = FXMLLoader.load(loc);
        } catch (IOException e) {
            System.out.println("Unable to find the FXML file");
            e.printStackTrace();
        }
        Scene windowContents = new Scene(box, 300, 400);
        //var color = Paint.valueOf("blue");
        //windowContents.setFill(color);
        primaryStage.setScene(windowContents);
        primaryStage.setTitle("Project 5");
        primaryStage.setResizable(false);
        primaryStage.show();
        Button button1 = new Button("Makeup");
        button1.setPrefSize(130,30);
        button1.setTranslateY(-100);
        box.getChildren().add(button1);
        button1.setOnAction(this::openMakeupWindow);
        Button button2 = new Button("Star Wars");
        button2.setPrefSize(130,30);
        button2.setTranslateY(-70);
        box.getChildren().add(button2);
        button2.setOnAction(this::openStarWarsWindow);

}
    @FXML
    public  void openMakeupWindow(ActionEvent event){
        Parent root = null;
        var loc = getClass().getResource("MakeupWindow.fxml");
        try {
            root = FXMLLoader.load(loc);
        }catch (IOException e){
            System.out.println("Couldn't Find FXML file!!!!!!");
        }
        Scene windowContents = new Scene(root, 900,400);
        Stage recipeWindow = new Stage();
        recipeWindow.setScene(windowContents);
        recipeWindow.setTitle("Makeup");
        recipeWindow.show();
    }
    @FXML
    public  void openStarWarsWindow(ActionEvent event){
        Parent root = null;
        var loc = getClass().getResource("StarWarsWindow.fxml");
        try {
            root = FXMLLoader.load(loc);
        }catch (IOException e){
            System.out.println("Couldn't Find FXML file!!!!!!");
        }
        Scene windowContents = new Scene(root, 760,550);
        Stage recipeWindow = new Stage();
        var color = Paint.valueOf("blue");
        windowContents.setFill(color);
        recipeWindow.setResizable(false);

        recipeWindow.setScene(windowContents);
        recipeWindow.setTitle("Star Wars");
        recipeWindow.show();
    }



}
