import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpGet {

    private static final String POSTS_API_URL = "https://api.github.com/users/";

    public List<HttpResponse> getData(String user){
        HttpClient client = HttpClient.newHttpClient();
        List<HttpResponse> response = new ArrayList<HttpResponse>();
        Boolean another_page = true;
        String nextlink = POSTS_API_URL+user;

        while(another_page) {
            HttpRequest request = HttpRequest.newBuilder()
                .GET()
                //If GitHub API requests limit exceeded, uncomment following header and insert personal access token:
                //.header("Authorization", "Bearer <Insert_Personal_Access_Token>")
                .header("accept", "application/json")
                .uri(URI.create(nextlink))
                .build();
            try {
                response.add(client.send(request, HttpResponse.BodyHandlers.ofString()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Map<String, List<String>> map = response.get(response.size()-1).headers().map();
            if(map.containsKey("link")) {
                String[] links = map.get("link").get(0).split("[<>]");
                for(String napis : links){
                    System.out.println(napis);
                }
                if (links[2].contains("next")) {
                    nextlink = links[1];
                }else if(links[4].contains("next")){
                    nextlink = links[3];
                }else{
                    another_page = false;
                }
            }else{
                another_page = false;
            }
        }
        return response;
    }
}

