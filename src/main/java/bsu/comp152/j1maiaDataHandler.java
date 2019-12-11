package bsu.comp152;

import com.google.gson.Gson;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

class DataHandler {
    private HttpClient dataGrabber;
    private String webLocation;

    public DataHandler(String webLocation) {
        dataGrabber = HttpClient.newHttpClient();
        this.webLocation = webLocation;
    }

    public ArrayList<makeupDataType> getData(){
        var requestBuilder = HttpRequest.newBuilder();
        var dataRequest = requestBuilder.uri(URI.create(webLocation)).build();
        HttpResponse<String> response = null;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(webLocation);
        alert.showAndWait();

        try {
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }catch(IOException e){
            System.out.println("Error connecting to network or site");
        }
        catch (InterruptedException e){
            System.out.println("Connection to site broken");
        }
        if (response == null ){
            System.out.println("Something went terribly wrong, ending program");
            System.exit(-1);
        }
        var usefulData = response.body();
        var jsonInterpreter = new Gson();
        var makeupData = jsonInterpreter.fromJson(usefulData, responseDataType.class);
        //System.out.println(makeupData.results);
        return makeupData.results;

    }

    class responseDataType {
        String product_type;
        float version;
        String href;
        ArrayList<makeupDataType> results;
    }

    class makeupDataType {
        String product_type;
        String product_category;
        String name;
        String brand;
        String product_tags;
        String price;
        String price_less_than;

        @Override
        public String toString() {
            return "Type: " + product_type;
        }
    }
}
