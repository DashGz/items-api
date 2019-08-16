import com.google.gson.Gson;

import java.util.Collection;

import static spark.Spark.*;


public class Server {
    public static void main(String[] args) {
        final IItemService service = new ItemServiceMapImpl();


        get("/items", (req, res) -> {
            res.type("application/json");
            Collection<Item> items = service.getItems();
            return new Gson().toJson(new Gson().toJsonTree(items));
        });

        post("/items/new", (req, res) -> {
            res.type("application/json");
            Item item = new Gson().fromJson(req.body(), Item.class);
            int id = service.addItem(item);
            return new Gson().toJson(new Gson().toJsonTree(item));
        });

        get("/items/:user", (req, res) -> {
            res.type("application/json");
            Collection<Item> userItems = service.getUserItems(req.params(":user"));
            return new Gson().toJson(new Gson().toJsonTree(userItems));
        });
    }


}
