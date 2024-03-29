import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import java.net.URLConnection;
import com.google.gson.Gson;
import com.google.gson.JsonElement;


public class ItemServiceMapImpl implements IItemService {


    private Map<String, Item> itemMap;

    public ItemServiceMapImpl() {
        itemMap = new HashMap<String, Item>();
    }


    public Collection<Item> getItems() {
        return new ArrayList<Item>(itemMap.values());
    }

    public Collection<Item> getUserItems(String user){
        Collection<Item> item1 = itemMap.values();
        Map<String, Item> userItems = new HashMap<String, Item>();

        item1.forEach(i -> {
            if(i.getUser().equalsIgnoreCase(user)){
                userItems.put(i.getId(), i);
            }
        });


        return  userItems.values();
    }

    public int addItem(Item item){
        itemMap.put(item.getId(), item);
        return itemMap.size();
    }

    public String getToken(User user){

        try {
            URL url = new URL("http://localhost:8082/users");
            try {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestMethod("POST");
                Gson gson = new Gson();
                JsonElement jsonInputString = gson.toJsonTree(user);
                System.out.println(jsonInputString);
                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                wr.write(jsonInputString.toString());
                wr.flush();
                StringBuilder sb = new StringBuilder();
                int HttpResult = con.getResponseCode();
                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(con.getInputStream(), "utf-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
                } else {
                    System.out.println(con.getResponseMessage());
                }
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        } catch (MalformedURLException exception) {
            System.out.println(exception.getMessage());
        }
        return (null);
    }

    public Site[] getSites(String token){

        try {
            URL url2 = new URL("http://localhost:8082/users/sites");
            try {
                HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
                con2.setRequestProperty("Content-Type", "application/json");
                con2.setRequestProperty("Accept", "application/json");
                con2.setRequestProperty("token", token);


                HttpURLConnection connection = con2;
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                Gson gson = new Gson();
                Site[] sites =  gson.fromJson(in, Site[].class);


                return sites;


            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        } catch (MalformedURLException exception) {
            System.out.println(exception.getMessage());
        }
        return (null);



    }

    public Category[] getCategories(String id, String token){

        try {
            URL url3 = new URL("http://localhost:8082/users/sites/" + id + "/categories");
            try {
                HttpURLConnection con3 = (HttpURLConnection) url3.openConnection();
                con3.setRequestProperty("Content-Type", "application/json");
                con3.setRequestProperty("Accept", "application/json");
                con3.setRequestProperty("token", token);


                HttpURLConnection connection = con3;
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                Gson gson = new Gson();
                Category[] categories =  gson.fromJson(in, Category[].class);


                return categories;


            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        } catch (MalformedURLException exception) {
            System.out.println(exception.getMessage());
        }
        return (null);
    }

}
