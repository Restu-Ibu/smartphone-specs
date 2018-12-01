package smartphone_specs.restuibu.com.smartphone_specs.Util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.aafanasev.fonoapi.DeviceEntity;
import com.aafanasev.fonoapi.retrofit.FonoApiFactory;
import com.aafanasev.fonoapi.retrofit.FonoApiService;

import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class DeviceAPI {

    // TODO: https://fonoapi.freshpixl.com/token/generate
    private final String TOKEN = "ccc87f6d43d7d513e24092f1599a1a11d7b1af828c1565bb";
    private Context context;

    public DeviceAPI(Context c) {
        this.context = c;
    }

    public Response<List<DeviceEntity>> getResponse(String brand) {
        FonoApiService api = new FonoApiFactory().create();

        Response<List<DeviceEntity>> response = null;

        try {
            //response = api.getLatest(TOKEN, brand, 100).execute();
            response = api.getDevice(TOKEN, brand,null,null).execute();
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
//        response.body().forEach(
//                device -> Toast.makeText(context, "First device: " + device.getDeviceName(), Toast.LENGTH_SHORT).show()
//        );

//        for(int i=0; i< response.body().size();i++){
//            DeviceEntity device = response.body().get(i);
//            Toast.makeText(context, "First device: " + device.getDeviceName(), Toast.LENGTH_SHORT).show();
//        }

        return response;

    }

}
