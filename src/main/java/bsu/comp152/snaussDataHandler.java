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

    // Creates new HTTP Request
    public snaussDataHandler() {
        dataGrabber = HttpClient.newHttpClient();

    }

    // Builds the "Connection" part of the project.
    public jokeFilter getData(String webLocation) {
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
        var theData = jsonInterpreter.fromJson(usefulData, jokeFilter.class);
        return theData;


    }
}
// The information we need from this website
class jokeFilter {
    String setup;
    String delivery;
    String joke;

    }



