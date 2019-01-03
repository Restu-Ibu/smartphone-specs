package smartphone_specs.restuibu.com.smartphone_specs.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import smartphone_specs.restuibu.com.smartphone_specs.R;
import smartphone_specs.restuibu.com.smartphone_specs.activity.ProductsActivity;

import static android.widget.ArrayAdapter.createFromResource;

public class MyAlert {
    public static void insertPassword(final Context c){
        LayoutInflater inflater = LayoutInflater.from(c);
        View dialogview = inflater.inflate(R.layout.alert_password,
                null);
        final AlertDialog alert = new AlertDialog.Builder(c)
                .create();
        //alert.setTitle("Login");
        alert.setView(dialogview);

        final EditText etPassword = (EditText) dialogview.findViewById(R.id.editText1);
        Button bLogin = (Button) dialogview.findViewById(R.id.button1);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPassword.getText().toString().equals("mrdngrn")){
//                    Toast.makeText(c, "sukses", Toast.LENGTH_SHORT).show();
                    c.startActivity(new Intent(c,
                            ProductsActivity.class));
                }
            }
        });


        alert.show();
    }
}
