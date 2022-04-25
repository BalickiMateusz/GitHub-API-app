import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class Server {

    public void runServer(){
        HttpGet getter = new HttpGet();
        JsonParser jsonParser = new JsonParser();
        InternalCSS internalCSS = new InternalCSS();

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            HttpContext context = server.createContext("/");
            server.setExecutor(Executors.newCachedThreadPool());
            HttpHandler handler = new HttpHandler(){
                public void handle(HttpExchange httpExchange) throws IOException{
                    Boolean stop = false;
                    String[] request = httpExchange.getRequestURI().toString().split("/");
                    StringBuilder content = new StringBuilder();
                    content.append("<html><head><style>");
                    content.append(internalCSS.getCSS());
                    content.append("</style></head><body>");
                    content.append("<h1>Welcome to my application!</h1><br>" +
                            "<h3>It communicates with GitHub API and allows to:</h3>" +
                            "<ul><li>list GitHub user's data (login, name, bio, programming languages used in repositories):</li>" +
                            "<ul><li>http://localhost:8080/user_name</li></ul>" +
                            "<li>list all repositories of a given GitHub user:</li><ul><li>http://localhost:8080/user_name/repositories</li></ul>" +
                            "<li>shutdown the running HTTP server:</li><ul><li>http://localhost:8080/serverShutdown</li></ul></ul>" +
                            "<h3>For example:</h3>" +
                            "<ul><li>http://localhost:8080/BalickiMateusz</li>" +
                            "<li>http://localhost:8080/BalickiMateusz/repositories</li></ul>");

                    if(request.length == 3 && request[2].equals("repositories")){
                        content.append("<h1>Repositories:</h1>");
                        List<HttpResponse> response = getter.getData(request[1]+"/repos");
                        String statusData = checkStatus(response.get(0));
                        if(statusData == ""){
                            for(int i=0; i<response.size(); i++) {
                                content.append(jsonParser.parseRepos(response.get(i).body().toString()));
                            }
                        }else{
                            content.append(statusData);
                        }
                    }

                    if(request.length == 2 && !request[1].equals("serverShutdown")){
                        content.append("<h1>User data:</h1>");
                        List<HttpResponse> response = getter.getData(request[1]+"/repos");
                        HttpResponse response2 = getter.getData(request[1]).get(0);
                        String statusData = checkStatus(response.get(0));
                        List<String> responseBodyList = new ArrayList<String>();

                        if(statusData == ""){
                            for(int i=0; i<response.size(); i++) {
                                responseBodyList.add(response.get(i).body().toString());
                            }
                            content.append(jsonParser.parseUser(responseBodyList, response2.body().toString()));
                        }else{
                            content.append(statusData);
                        }
                    }

                    if(request.length == 2 && request[1].equals("serverShutdown")){
                        stop = true;
                        System.out.println("Server shut down!");
                        content.append("<h1>The server has been shut down.</h1>");
                    }

                    content.append("</body></html>");
                    httpExchange.sendResponseHeaders(200, content.length());
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(content.toString().getBytes());
                    os.close();
                    if(stop == true){
                        server.stop(0);
                        System.exit(0);
                    }
                }
            };
            context.setHandler(handler);
            server.start();
            System.out.println("Server started!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String checkStatus(HttpResponse response){
        if (response.statusCode() == 404) {
            if (response.body().toString().contains("Not Found")) {
                return "<h2>User Not Found</h2>";
            } else {
                return "<h2>Something went wrong! Error code: 404</h2>";
            }
        } else if(response.statusCode()==403){
            return "<h2>GitHub API requests limit exceeded! Please enter GitHub Personal Access Token!</h2>";
        } else{
            return "";
        }
    }
}
