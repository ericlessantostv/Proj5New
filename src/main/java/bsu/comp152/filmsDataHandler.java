package bsu.comp152;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

    public class filmsDataHandler {
        private HttpClient dataGrabber;
        private String webLocation;

        public filmsDataHandler(String webLocation){
            dataGrabber = HttpClient.newHttpClient();
            this.webLocation = webLocation;
        }
        public ArrayList<filmsDataHandler.peopleDataType> getData1(){
            var requestBuilder = HttpRequest.newBuilder();
            var dataRequest = requestBuilder.uri(URI.create(webLocation)).build();
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
            var usefulData1 = response.body();
            var jsonInterpreter1 = new Gson();
            var peopleData1 = jsonInterpreter1.fromJson(usefulData1, filmsDataHandler.responseDataType.class);
            return peopleData1.results1;

        }
        class responseDataType{
            String title;
            float version;
            String href;
            ArrayList<filmsDataHandler.peopleDataType> results1;

        }
        class peopleDataType{
            String title;

            @Override
            public String toString(){
                return "Title: " + title;
            }
        }

}
