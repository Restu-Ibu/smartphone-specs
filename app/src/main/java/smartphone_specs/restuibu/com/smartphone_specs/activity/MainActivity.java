package smartphone_specs.restuibu.com.smartphone_specs.activity;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.aafanasev.fonoapi.DeviceEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import smartphone_specs.restuibu.com.smartphone_specs.R;
import smartphone_specs.restuibu.com.smartphone_specs.Util.DeviceAPI;
import smartphone_specs.restuibu.com.smartphone_specs.adapter.DeviceAdapter;
import smartphone_specs.restuibu.com.smartphone_specs.adapter.ListDeviceAdapter;
import smartphone_specs.restuibu.com.smartphone_specs.model.DeviceItem;
import smartphone_specs.restuibu.com.smartphone_specs.model.ListDeviceItem;

public class MainActivity extends AppCompatActivity {

    private EditText etFind;
    private Button bFind, bBack;
    public static ListView list;
    public static ListDeviceAdapter adapter1;
    public static DeviceAdapter adapter2;
    public static int flag_adapter;
    private LinearLayout llSearch;
    private ArrayList<ListDeviceItem> ListDeviceItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


    }

    public void init() {
        etFind = (EditText) findViewById(R.id.editText1);
        bFind = (Button) findViewById(R.id.button1);
        bBack = (Button) findViewById(R.id.button2);
        list = (ListView) findViewById(R.id.listView1);
        llSearch = (LinearLayout) findViewById(R.id.linearLayout1);

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag_adapter == 1){
                    list.setVisibility(View.GONE);
                    bBack.setVisibility(View.GONE);
                    llSearch.setVisibility(View.VISIBLE);
                } else if(flag_adapter == 2){
                    list.setAdapter(adapter1);
                    flag_adapter = 1;
                }
            }
        });

        bFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeviceAPI deviceAPI = new DeviceAPI(MainActivity.this);
                Response<List<DeviceEntity>> response = deviceAPI.getResponse(etFind.getText().toString());



                if (response != null) {

                    list.setVisibility(View.VISIBLE);
                    bBack.setVisibility(View.VISIBLE);
                    llSearch.setVisibility(View.GONE);

                    if(response.body().size() == 100){
                        Toast.makeText(MainActivity.this, "We have limit from getting the results\nplease specify your search", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Found "+response.body().size()+" results", Toast.LENGTH_SHORT).show();
                    }

                    ListDeviceItems = new ArrayList<ListDeviceItem>();

                    for (int i = 0; i < response.body().size(); i++) {
                        DeviceEntity device = response.body().get(i);
                        ArrayList<DeviceItem> deviceItems = new ArrayList<DeviceItem>();
                        if (device.getDeviceName() != null)
                            deviceItems.add(new DeviceItem("DeviceName", device.getDeviceName()));
                        if (device.getBrand() != null)
                            deviceItems.add(new DeviceItem("Brand", device.getBrand()));
                        if (device.getTechnology() != null)
                            deviceItems.add(new DeviceItem("technology", device.getTechnology()));
                        if (device.getGprs() != null)
                            deviceItems.add(new DeviceItem("gprs", device.getGprs()));
                        if (device.getEdge() != null)
                            deviceItems.add(new DeviceItem("edge", device.getEdge()));
                        if (device.getAnnounced() != null)
                            deviceItems.add(new DeviceItem("announced", device.getAnnounced()));
                        if (device.getStatus() != null)
                            deviceItems.add(new DeviceItem("status", device.getStatus()));
                        if (device.getDimensions() != null)
                            deviceItems.add(new DeviceItem("dimensions", device.getDimensions()));
                        if (device.getWeight() != null)
                            deviceItems.add(new DeviceItem("weight", device.getWeight()));
                        if (device.getSim() != null)
                            deviceItems.add(new DeviceItem("sim", device.getSim()));
                        if (device.getType() != null)
                            deviceItems.add(new DeviceItem("type", device.getType()));
                        if (device.getSize() != null)
                            deviceItems.add(new DeviceItem("size", device.getSize()));
                        if (device.getResolution() != null)
                            deviceItems.add(new DeviceItem("resolution", device.getResolution()));
                        if (device.getCard_slot() != null)
                            deviceItems.add(new DeviceItem("card_slot", device.getCard_slot()));
                        if (device.getPhonebook() != null)
                            deviceItems.add(new DeviceItem("phonebook", device.getPhonebook()));
                        if (device.getCall_records() != null)
                            deviceItems.add(new DeviceItem("call_records", device.getCall_records()));
                        if (device.getCamera_c() != null)
                            deviceItems.add(new DeviceItem("camera_c", device.getCamera_c()));
                        if (device.getAlert_types() != null)
                            deviceItems.add(new DeviceItem("alert_types", device.getAlert_types()));
                        if (device.getLoudspeaker_() != null)
                            deviceItems.add(new DeviceItem("loudspeaker_", device.getLoudspeaker_()));
                        if (device.get_3_5mm_jack_() != null)
                            deviceItems.add(new DeviceItem("_3_5mm_jack_", device.get_3_5mm_jack_()));
                        if (device.getSound_c() != null)
                            deviceItems.add(new DeviceItem("sound_c", device.getSound_c()));
                        if (device.getWlan() != null)
                            deviceItems.add(new DeviceItem("wlan", device.getWlan()));
                        if (device.getBluetooth() != null)
                            deviceItems.add(new DeviceItem("bluetooth", device.getBluetooth()));
                        if (device.getGps() != null)
                            deviceItems.add(new DeviceItem("gps", device.getGps()));
                        if (device.getInfrared_port() != null)
                            deviceItems.add(new DeviceItem("infrared_port", device.getInfrared_port()));
                        if (device.getRadio() != null)
                            deviceItems.add(new DeviceItem("radio", device.getRadio()));
                        if (device.getUsb() != null)
                            deviceItems.add(new DeviceItem("usb", device.getUsb()));
                        if (device.getMessaging() != null)
                            deviceItems.add(new DeviceItem("messaging", device.getMessaging()));
                        if (device.getBrowser() != null)
                            deviceItems.add(new DeviceItem("browser", device.getBrowser()));
                        if (device.getClock() != null)
                            deviceItems.add(new DeviceItem("clock", device.getClock()));
                        if (device.getAlarm() != null)
                            deviceItems.add(new DeviceItem("alarm", device.getAlarm()));
                        if (device.getGames() != null)
                            deviceItems.add(new DeviceItem("games", device.getGames()));
                        if (device.getLanguages() != null)
                            deviceItems.add(new DeviceItem("languages", device.getLanguages()));
                        if (device.getJava() != null)
                            deviceItems.add(new DeviceItem("java", device.getJava()));
                        if (device.getFeatures_c() != null)
                            deviceItems.add(new DeviceItem("features_c", device.getFeatures_c()));
                        if (device.getBattery_c() != null)
                            deviceItems.add(new DeviceItem("battery_c", device.getBattery_c()));
                        if (device.getStand_by() != null)
                            deviceItems.add(new DeviceItem("stand_by", device.getStand_by()));
                        if (device.getTalk_time() != null)
                            deviceItems.add(new DeviceItem("talk_time", device.getTalk_time()));
                        if (device.getColors() != null)
                            deviceItems.add(new DeviceItem("colors", device.getColors()));
                        if (device.getSensors() != null)
                            deviceItems.add(new DeviceItem("sensors", device.getSensors()));
                        if (device.getCpu() != null)
                            deviceItems.add(new DeviceItem("cpu", device.getCpu()));
                        if (device.getInternal() != null)
                            deviceItems.add(new DeviceItem("internal", device.getInternal()));
                        if (device.getOs() != null)
                            deviceItems.add(new DeviceItem("os", device.getOs()));
                        if (device.getBody_c() != null)
                            deviceItems.add(new DeviceItem("body_c", device.getBody_c()));
                        if (device.getKeyboard() != null)
                            deviceItems.add(new DeviceItem("keyboard", device.getKeyboard()));
                        if (device.getPrimary_() != null)
                            deviceItems.add(new DeviceItem("primary_", device.getPrimary_()));
                        if (device.getVideo() != null)
                            deviceItems.add(new DeviceItem("video", device.getVideo()));
                        if (device.getSecondary() != null)
                            deviceItems.add(new DeviceItem("secondary", device.getSecondary()));
                        if (device.getSpeed() != null)
                            deviceItems.add(new DeviceItem("speed", device.getSpeed()));
                        if (device.getNetwork_c() != null)
                            deviceItems.add(new DeviceItem("network_c", device.getNetwork_c()));
                        if (device.getChipset() != null)
                            deviceItems.add(new DeviceItem("chipset", device.getChipset()));
                        if (device.getFeatures() != null)
                            deviceItems.add(new DeviceItem("features", device.getFeatures()));
                        if (device.getMusic_play() != null)
                            deviceItems.add(new DeviceItem("music_play", device.getMusic_play()));
                        if (device.getProtection() != null)
                            deviceItems.add(new DeviceItem("protection", device.getProtection()));
                        if (device.getGpu() != null)
                            deviceItems.add(new DeviceItem("gpu", device.getGpu()));
                        if (device.getMultitouch() != null)
                            deviceItems.add(new DeviceItem("multitouch", device.getMultitouch()));
                        if (device.getLoudspeaker() != null)
                            deviceItems.add(new DeviceItem("loudspeaker", device.getLoudspeaker()));
                        if (device.getAudio_quality() != null)
                            deviceItems.add(new DeviceItem("audio_quality", device.getAudio_quality()));
                        if (device.getNfc() != null)
                            deviceItems.add(new DeviceItem("nfc", device.getNfc()));
                        if (device.getCamera() != null)
                            deviceItems.add(new DeviceItem("camera", device.getCamera()));
                        if (device.getDisplay() != null)
                            deviceItems.add(new DeviceItem("display", device.getDisplay()));
                        if (device.getBattery_life() != null)
                            deviceItems.add(new DeviceItem("battery_life", device.getBattery_life()));
                        if (device.get_2g_bands() != null)
                            deviceItems.add(new DeviceItem("_2g_bands", device.get_2g_bands()));
                        if (device.get_3g_bands() != null)
                            deviceItems.add(new DeviceItem("_3g_bands", device.get_3g_bands()));
                        if (device.get_4g_bands() != null)
                            deviceItems.add(new DeviceItem("_4g_bands", device.get_4g_bands()));

                        ListDeviceItems.add(new ListDeviceItem(device.getDeviceName(),deviceItems));

                    }

                    adapter1 = new ListDeviceAdapter(MainActivity.this, ListDeviceItems);
                    list.setAdapter(adapter1);
                    flag_adapter = 1;
                } else {
                    Toast.makeText(MainActivity.this, "0 Result", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
