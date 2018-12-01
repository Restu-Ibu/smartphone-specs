package smartphone_specs.restuibu.com.smartphone_specs.model;

import java.util.ArrayList;

public class ListDeviceItem {
    private String device;
    private ArrayList<DeviceItem> deviceItems = new ArrayList<DeviceItem>();

    public ListDeviceItem() {
    }

    public ListDeviceItem(String device, ArrayList<DeviceItem> deviceItems) {
        this.device = device;
        this.deviceItems = deviceItems;
    }

    public String getDevice() {
        return device;
    }

    public ArrayList<DeviceItem> getDeviceItems() {
        return deviceItems;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setDeviceItems(ArrayList<DeviceItem> deviceItems) {
        this.deviceItems = deviceItems;
    }
}
