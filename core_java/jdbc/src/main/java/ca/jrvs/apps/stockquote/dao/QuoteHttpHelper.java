package ca.jrvs.apps.stockquote.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class QuoteHttpHelper {

    private String apiKey;
    private OkHttpClient client;

    public QuoteHttpHelper(String apiKey, OkHttpClient client){
        this.apiKey = apiKey;
        this.client = client;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public String getApiKey() {
        return apiKey;
    }

    /**
     * Fetch latest quote data from Alpha Vantage endpoint
     * @param symbol
     * @return Quote with latest data
     * @throws IllegalArgumentException - if no data was found for the given symbol
     */
    public Quote fetchQuoteInfo(String symbol) throws IllegalArgumentException {

        JsonParser jsonParser = new JsonParser();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol="+symbol+"&datatype=json"))
                .header("X-RapidAPI-Key", getApiKey())
                .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
            String body = response.body();
            String jsonWell = body.substring(body.indexOf("{") + 22, body.length()-2);
            return jsonParser.toObjectFromJson(jsonWell, Quote.class);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        QuoteHttpHelper quoteHttpHelperelper = new QuoteHttpHelper("2ec0989014msh3a8b0229798ab6ap1a738ejsnf518524b3bce", null);

        try{
            System.out.println(quoteHttpHelperelper.fetchQuoteInfo("MSFT").toString());
        }
        catch (IllegalArgumentException e){
            e.getMessage();
        }


    }
}