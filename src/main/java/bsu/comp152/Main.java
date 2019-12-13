package bsu.comp152;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
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
        Button button3 = new Button("JokeAPI");
        button3.setPrefSize(130,30);
        button3.setTranslateY(-70);
        box.getChildren().add(button3);
        button3.setOnAction(this::openJokeAPI);



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
        Scene windowContents = new Scene(root, 900,400);
        Stage recipeWindow = new Stage();
        recipeWindow.setScene(windowContents);
        recipeWindow.setTitle("Star Wars");
        recipeWindow.show();
    }




    public void openJokeAPI(ActionEvent event) {
        Parent root = null;
        var loc = getClass().getResource("snaussMain.fxml");
        try {
            root = FXMLLoader.load(loc);

        }
        catch (IOException e) {
            System.out.println("No FXML File");
        }
        Scene jokeContents = new Scene(root, 1000, 400);
        Stage jokeWindow = new Stage();
        jokeWindow.setScene(jokeContents);
        jokeWindow.setTitle("Showing Data");
        jokeWindow.show();
    }

}
