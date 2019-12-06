package bsu.comp152;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ericController implements Initializable {
    @FXML
    private ListView<ericDataHandler.starDataType> ListControl;
    private ListView<filmsDataHandler.peopleDataType> ListControl1;
    private ericDataHandler Model;
    private filmsDataHandler Model1;
    private String category;


    public void loadData(){
        var site = "https://swapi.co/api/";
        var params = getQueryParameters();
        var query = site + params;

        Model = new ericDataHandler(query);
        var starList = Model.getData();
        ObservableList<ericDataHandler.starDataType> dataToShow = FXCollections.observableArrayList(starList);
        ListControl.setItems(dataToShow);
    }
    public void loadAdditionalData(String site){
        var site2 = site;
        Model1 = new filmsDataHandler(site2);
        var list = Model1.getData1();
        ObservableList<filmsDataHandler.peopleDataType> dataToShow1 = FXCollections.observableArrayList(list);
        ListControl1.setItems(dataToShow1);
    }
    public String getQueryParameters(){
        var category = getCategory();
        return "" + category + "/";
    }

    private String getCategory() {
        TextInputDialog answer = new TextInputDialog("people");
        answer.setHeaderText("Gathering information..");
        answer.setContentText("What category would you like to learn about?");
        answer.setWidth(200);
        answer.setResizable(false);
        Optional<String> result = answer.showAndWait();
        if (result.isPresent())
            return result.get();
        else
            return "2";
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        category = "";
        ListControl.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<ericDataHandler.starDataType>() {
                    @Override
                    public void changed(ObservableValue<? extends ericDataHandler.starDataType> observable, ericDataHandler.starDataType oldValue, ericDataHandler.starDataType newValue) {
                        var name = ListControl.getSelectionModel().getSelectedItem();
                        if (name.gravity == null) {
                            Alert nameInfo = new Alert(Alert.AlertType.INFORMATION);
                            System.out.println(name.films.get(0));
                            nameInfo.setTitle(name.name);
                            nameInfo.setHeaderText("");
                            nameInfo.setHeaderText("Information about " + name.name + ":");
                            String film = name.films.get(0);
                            loadAdditionalData(film);
                            var filme = ListControl1.getSelectionModel().getSelectedItem();
                            nameInfo.setContentText("Name: " + name.name
                                    + "\nBirth year: " + name.birth_year +
                                    "\nHeight: " + name.height + "\nSpecies: " + name.species + "\nFilms: " + filme.name + "\nStarships: "+ name.starships);
                            nameInfo.showAndWait();
                        } else {
                            Alert nameInfo = new Alert(Alert.AlertType.INFORMATION);
                            nameInfo.setTitle(name.name);
                            nameInfo.setHeaderText("Information about " + name.name + ":");
                            nameInfo.setContentText("Name: " + name.name
                                     + "\nPopulation: " + name.population + "\nFilms: " + name.films + "\nResidents: "+ name.residents + "\nClimate: " + name.climate);
                            nameInfo.showAndWait();

                        }

                    }
                }
        );

    }
    @FXML
    public void selectMenuItem(javafx.event.ActionEvent actionEvent){
        var item = (MenuItem)actionEvent.getSource();
        category = item.getText();
    }
}
