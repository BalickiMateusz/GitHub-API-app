import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {

    public String parseRepos(String jsonData){
        StringBuilder data = new StringBuilder();
        JSONArray jsonArray = new JSONArray(jsonData);
        if (jsonArray.length() == 0) data.append("<h2>No repositories to list</h2>");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.get("name").toString();
            String language = jsonObject.get("language").toString();
            String size = jsonObject.get("size").toString();
            data.append("<dl><dt>Name</dt><dd>").append(name);
            if(language != "null"){
                data.append("</dd><dt>Language</dt><dd>").append(language);
            }else{
                data.append("</dd><dt>Language</dt><dd>").append("-");
            }
            data.append("</dd><dt>Size</dt><dd>").append(size);
            data.append(" bytes</dd></dl>");
        }
        return data.toString();
    }

    public String parseUser(List<String> jsonReposData, String jsonUserData){
        StringBuilder data = new StringBuilder();
        JSONObject jsonUserObject = new JSONObject(jsonUserData);
        JSONArray jsonReposArray = new JSONArray();
        for(String singleResponse : jsonReposData){
            JSONArray temp = new JSONArray(singleResponse);
            for(int i=0; i<temp.length(); i++){
                jsonReposArray.put(temp.getJSONObject(i));
            }
        }

        String login = jsonUserObject.get("login").toString();
        String name = jsonUserObject.get("name").toString();
        String bio = jsonUserObject.get("bio").toString();
        data.append("<dl><dt>Login</dt><dd>").append(login);
        if(name!="null"){
            data.append("</dd><dt>Name</dt><dd>").append(name);
        }else{
            data.append("</dd><dt>Name</dt><dd>").append("-");
        }

        if(bio!="null") {
            data.append("</dd><dt>Bio</dt><dd>").append(bio);
        }else{
            data.append("</dd><dt>Bio</dt><dd>").append("-");
        }

        if (jsonReposArray.length() == 0) {
            data.append("</dd><dt>Programming languages</dt><dd><br>No repositories to list, hence absence of programming languages</dd>");
        }else{
            data.append("</dd><dt>Programming languages</dt><br>");
        }

        Map<String, String> map = new HashMap<String, String>();

        for (int i = 0; i < jsonReposArray.length(); i++) {
            JSONObject jsonReposObject = jsonReposArray.getJSONObject(i);
            String language = jsonReposObject.get("language").toString();
            String size = jsonReposObject.get("size").toString();
            if(language=="null") language = "Not specified";
            if(map.containsKey(language)){
                map.put(language, String.valueOf(Integer.parseInt(map.get(language))+Integer.parseInt(size)));
            }else{
                map.put(language, size);
            }
        }
        for(String key : map.keySet()){
            data.append("<dd>").append(key).append(": ");
            data.append(map.get(key)).append(" bytes</dd>");
        }
        data.append("</dl>");
        return data.toString();
    }
}
