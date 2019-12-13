package bsu.comp152;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class snaussController implements Initializable {
    @FXML
    // TextFields - Results
    public TextField setupField;
    public TextField deliveryField;
    public TextField urlDisplay;
    public TextField linkDisplay;
    // DataHandler
    private snaussDataHandler dataModel;
    // Strings needed
    private String jokeType;
    private String blacklistType;
    // ArrayList
    private ArrayList<CheckBox> blacklistBoxes;
    //RadioButtons
    private RadioButton NSFW, Religious, Political;



    // Connects DataHandler to Controller
    public void initialize(URL location, ResourceBundle resources) {
        dataModel = new snaussDataHandler();

    }
    // What happens when the button is clicked
    public void loadData() {
        var site = "https://sv443.net/jokeapi/category";
        var params = getQueryParameters();
        site = site + params;
        jokeFilter data = dataModel.getData(site);
        displayData(data);



    }
    // Builds the URL -- Takes what we get from two other functions and makes it into one string
    public String getQueryParameters() {
        var joke = getJokeType();
        var blacklist = getBlacklist();
        return "/" + joke + "?blacklistFlags=" + blacklist;
    }


    //This function was used to check what url was returning when I was creating the Project
    //public String urlChecker() {
        //return getQueryParameters();
    //}

    // Returns the Joke Type
    public String getJokeType() { return jokeType;}

    //Returns the Blacklist
    public String getBlacklist() {
        return blacklistType;

    }
    // Responsible for getting the result of the Joke Type Menu
    public void selectMenuItem(javafx.event.ActionEvent actionEvent) {
        var item = (MenuItem)actionEvent.getSource();
        jokeType = item.getText();
    }

    // Responsible for getting the result of whatever buttons are clicked for the filter
    public void selectRadioItem(javafx.event.ActionEvent actionEvent) {
        var source = (RadioButton)actionEvent.getSource();
        if (source.isSelected()) {
            blacklistType += source.getText();
        }

    }
    // Calls information and displays it when the button is pressed
    public void displayData(jokeFilter data) {
        setupField.setText(data.setup);
        deliveryField.setText(data.delivery);
        urlDisplay.setText(data.joke);
        //linkDisplay.setText(urlChecker());

    }

}
