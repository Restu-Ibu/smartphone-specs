package smartphone_specs.restuibu.com.smartphone_specs.model;

public class DeviceItem {
    private String title;
    private String description;

    public DeviceItem() {
    }

    public DeviceItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
