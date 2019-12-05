package bsu.comp152;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class snaussDataHandler {

    private HttpClient dataGrabber;
    private String webLocation;

    public snaussDataHandler(String webLocation) {
        dataGrabber = HttpClient.newHttpClient();
        this.webLocation = webLocation;
    }

    public ArrayList<jokeType> getData() {
        var requestBuilder = HttpRequest.newBuilder();
        var dataRequest = requestBuilder.uri(URI.create(webLocation)).build();
        HttpResponse<String> response = null;
        try {
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            System.out.println("Error connecting to network or site");

        } catch (InterruptedException e) {
            System.out.println("Connection to site broken");
        }
        if (response == null) {
            System.out.println("Something went terribly wrong, ending program");
            System.exit(-1);
        }
        var usefulData = response.body();
        var jsonInterpreter = new Gson();
        var theData = jsonInterpreter.fromJson(usefulData, jokeType.class);
        return theData.results;


    }


    class jokeType {
        ArrayList<jokeType> results;
        String category;
        String type;
        String setup;
        String delivery;
        String joke;
        String id;

        public java.lang.String toString() {
            return "Joke: " + setup + "  Finish: " + delivery;
        }

    }


}
