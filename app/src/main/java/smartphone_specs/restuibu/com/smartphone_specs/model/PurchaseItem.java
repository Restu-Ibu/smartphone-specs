package smartphone_specs.restuibu.com.smartphone_specs.model;

public class PurchaseItem {
    String title;
    String id;
    String price;
    String description;

    public PurchaseItem(String title, String id, String price, String description) {
        this.title = title;
        this.id = id;
        this.price = price;
        this.description = description;
    }

    public PurchaseItem() {
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
