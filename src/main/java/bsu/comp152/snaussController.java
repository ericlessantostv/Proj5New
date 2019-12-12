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
    public TextField setupField;
    public TextField deliveryField;
    private snaussDataHandler dataModel;
    private String jokeType;
    private ArrayList<CheckBox> blacklistBoxes;
    public TextField urlDisplay;
    private CheckBox NSFW, Religious, Political;



    public void initialize(URL location, ResourceBundle resources) {
        blacklistBoxes = new ArrayList<CheckBox>();
        blacklistBoxes.add(NSFW);
        blacklistBoxes.add(Religious);
        blacklistBoxes.add(Political);
        dataModel = new snaussDataHandler();

    }

    public void loadData() {
        var site = "https://sv443.net/jokeapi/category";
        var params = getQueryParameters();
        site = site + params;
        jokeFilter data = dataModel.getData(site);
        displayData(data);



    }

    public String getQueryParameters() {
        var joke = getJokeType();
        var blacklist = getBlacklist();
        return "/" + joke + "?blacklistFlags=" + blacklist;
    }

    public String getJokeType() { return jokeType;}

    public void selectMenuItem(javafx.event.ActionEvent actionEvent) {
        var item = (MenuItem)actionEvent.getSource();
        jokeType = item.getText();
    }

    public void displayData(jokeFilter data) {
        setupField.setText(data.setup);
        deliveryField.setText(data.delivery);
        urlDisplay.setText(data.joke);
    }

    public String getBlacklist() {
        var thyList = "";
        for (var box: blacklistBoxes) {
            if(box.isSelected()) {
                thyList += box.getText();
            }

        }
        return thyList;
    }
}
