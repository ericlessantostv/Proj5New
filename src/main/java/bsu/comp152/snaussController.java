package bsu.comp152;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class snaussController implements Initializable {
    @FXML
    private ListView<snaussDataHandler.jokeType> ListControl;
    private snaussDataHandler Model;
    private String jokeType;

    public void loadData() {
        var site = "https://sv443.net/jokeapi/category";
        var params = getQueryParameters();
        var query = site + params;

        Model = new snaussDataHandler(query);
        var resultJoke = Model.getData();
        ObservableList<snaussDataHandler.jokeType> theData = FXCollections.observableArrayList(resultJoke);
        ListControl.setItems(theData);

    }



    public String getBlackList() {
        return null;
    }

    public String getQueryParameters() {
        var joke = getJokeType();
        var blacklist = getBlackList();
        return "/" + joke;
    }

    public String getJokeType() { return jokeType;}

    public void selectMenuItem(javafx.event.ActionEvent actionEvent) {
        var item = (MenuItem)actionEvent.getSource();
        jokeType = item.getText();
    }

    public void initialize(URL location, ResourceBundle resources) {
        jokeType = "";
        ListControl.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<snaussDataHandler.jokeType>() {
                    @Override
                    public void changed(ObservableValue<? extends snaussDataHandler.jokeType> observableValue, snaussDataHandler.jokeType jokeType, snaussDataHandler.jokeType t1) {
                        var joke = ListControl.getSelectionModel() .getSelectedItem();
                        Alert jokePresent = new Alert(Alert.AlertType.INFORMATION);
                        jokePresent.setHeaderText("SetUp: " + joke.setup );
                        jokePresent.setContentText("Joke: " + joke.delivery);

                    }
                }
        );

    }
}
