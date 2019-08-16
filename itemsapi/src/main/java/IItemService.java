import java.util.Collection;

public interface IItemService {

    public Collection<Item> getUserItems(String user);
    public Collection<Item> getItems();
    public int addItem(Item item) throws ApiException;
    public String getToken();

}
