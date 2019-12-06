package bsu.comp152;

import com.google.gson.Gson;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class ericDataHandler {
    private HttpClient dataGrabber;
    private String webLocation;

    public ericDataHandler(String webLocation){
        dataGrabber = HttpClient.newHttpClient();
        this.webLocation = webLocation;
    }

    public ArrayList<starDataType> getData(){
        var requestBuilder = HttpRequest.newBuilder();
        var dataRequest = requestBuilder.uri(URI.create(webLocation)).build();
        //Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //alert.setHeaderText(webLocation);
        //alert.showAndWait();
        HttpResponse<String> response = null;
        try{
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            System.out.println("The connection was broken :(");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error connecting to the website :(");
            e.printStackTrace();
        }
        if(response == null){
            System.out.println("Something went wrong. Exiting..");
            System.exit(-1);
        }
        var usefulData = response.body();
        var jsonInterpreter = new Gson();
        var starData = jsonInterpreter.fromJson(usefulData, responseDataType.class);
        return starData.results;
    }

    class responseDataType{
        String title;
        float version;
        String href;
        ArrayList<starDataType> results;
    }

    class starDataType {
        String title;
        String href;
        String name;
        String people;
        String planet;
        String height;
        String birth_year;
        String gravity;
        String climate;
        String population;
        ArrayList<String> residents;
        ArrayList<String> species;
        ArrayList<String> films;
        ArrayList<String> starships;

        @Override
        public String toString() {
            return "Title: " + name;
        }
    }

}

