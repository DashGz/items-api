import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    public String getToken(){
        return null;
    };

}
