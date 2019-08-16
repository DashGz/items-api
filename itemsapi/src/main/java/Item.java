public class Item {
    private String id;
    private String name;
    private String user;
    private String category;

    public Item(){

    }

    public Item(String id, String name, String user, String category) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
