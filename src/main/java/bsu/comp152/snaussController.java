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
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;

    public void loadData() {
        var site = "https://sv443.net/jokeapi/category";
        var params = getQueryParameters();
        var query = site + params;

        Model = new snaussDataHandler(query);
        var resultJoke = Model.getData();
        ObservableList<snaussDataHandler.jokeType> theData = FXCollections.observableArrayList(resultJoke);
        ListControl.setItems(theData);

    }



    public String getJoke(){

        String result = "";

        if (rb1.isSelected()) {
            result = rb1.getText() ;

        }
        if (rb2.isSelected()) {
            result = rb2.getText() ;

        }
        if (rb3.isSelected()) {
            result = rb3.getText() ;

        }
        if (rb4.isSelected()) {
            result = rb4.getText() ;

        }
        return result;

    }

    public String getBlackList() {
        return null;
    }

    public String getQueryParameters() {
        var joke = getJoke();
        var blacklist = getBlackList();
        return "";
    }

    public void initialize(URL location, ResourceBundle resources) {
        loadData();

    }
}
