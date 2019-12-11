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
import java.util.Optional;
import java.util.ResourceBundle;

public class j1maiaController implements Initializable {
    @FXML
    private ListView<DataHandler.makeupDataType> ListControl;
    private DataHandler Model;
    private String category;

    public void loadData(){
        var site = "http://makeup-api.herokuapp.com/api/v1/products.json";
        var params = getQueryParameters();
        var query = site+params;

        Model = new DataHandler(query);
        var makeupList = Model.getData();
        ObservableList<DataHandler.makeupDataType> dataToShow = FXCollections.observableArrayList(makeupList);
        ListControl.setItems(dataToShow);
    }

    public String getQueryParameters(){
        var makeup = getCategory();
        return "?product_type="+ makeup;
    }

    private String getCategory(){
        return category;
//        TextInputDialog answer = new TextInputDialog("blush");
//        answer.setHeaderText("Gathering Information");
//        answer.setContentText("What kind of makeup do you want?");
//        Optional<String> result = answer.showAndWait();
//        if (result.isPresent())
//            return result.get();
//        else
//            return "";
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //loadData();
        category = "";
        ListControl.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<DataHandler.makeupDataType>() {
                    @Override
                    public void changed(ObservableValue<? extends DataHandler.makeupDataType> observable, DataHandler.makeupDataType oldValue, DataHandler.makeupDataType newValue) {
                        var makeUp = ListControl.getSelectionModel().getSelectedItem();
                        Alert makeupInfo = new Alert(Alert.AlertType.INFORMATION);
                        makeupInfo.setTitle("Information about "+makeUp.product_type);
                        makeupInfo.setHeaderText("Ingredients: "+ makeUp.price);
                        makeupInfo.setContentText("Here: "+makeUp.product_category);
                        makeupInfo.showAndWait();
                    }
                }
        );
    }

    @FXML
    public void selectMenuItem(javafx.event.ActionEvent actionEvent) {
        var item =(MenuItem)actionEvent.getSource();
        category = item.getText();
    }


}
