package smartphone_specs.restuibu.com.smartphone_specs.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import smartphone_specs.restuibu.com.smartphone_specs.R;
import smartphone_specs.restuibu.com.smartphone_specs.adapter.ProductAdapter;
import smartphone_specs.restuibu.com.smartphone_specs.model.PurchaseItem;
import smartphone_specs.restuibu.com.smartphone_specs.util.Constant;
import smartphone_specs.restuibu.com.smartphone_specs.util.MyAlert;
import smartphone_specs.restuibu.com.smartphone_specs.util_billing.IabHelper;
import smartphone_specs.restuibu.com.smartphone_specs.util_billing.IabResult;
import smartphone_specs.restuibu.com.smartphone_specs.util_billing.Inventory;
import smartphone_specs.restuibu.com.smartphone_specs.util_billing.Purchase;

public class ProductsActivity extends AppCompatActivity {

    //List<String> additionalSkuList = Arrays.asList("plafon_buy_50k");
    private ListView listProduct;
    public static IabHelper mHelper;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        init();
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Choose Product");

        listProduct = (ListView) findViewById(R.id.listView1);

        ButterKnife.bind(ProductsActivity.this);
        mHelper = new IabHelper(ProductsActivity.this, Constant.base64EncodedPublicKey);
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    Toast.makeText(ProductsActivity.this, "Problem setting up In-app Billing: " + result, Toast.LENGTH_LONG).show();
                } else {
                    loadProducts();
                }
            }
        });

    }

    private void loadProducts() {
        try {
            mHelper.queryInventoryAsync(true, Constant.additionalSkuList, null, new IabHelper.QueryInventoryFinishedListener() {
                @Override
                public void onQueryInventoryFinished(IabResult result, Inventory inv) {
                    ArrayList<PurchaseItem> items = new ArrayList<>();

                    for (String s : Constant.additionalSkuList) {
                        Purchase p = inv.getPurchase(s);
                        PurchaseItem item = new PurchaseItem();
                        item.setTitle(inv.getSkuDetails(s).getTitle());
                        item.setDescription(inv.getSkuDetails(s).getDescription());
                        item.setPrice(inv.getSkuDetails(s).getPrice());
                        item.setId(inv.getSkuDetails(s).getSku());
                        items.add(item);



                    }

                    productAdapter = new ProductAdapter(ProductsActivity.this, items);
                    listProduct.setAdapter(productAdapter);


                }
            });
        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Toast.makeText(ProductsActivity.this, "onActivityResult(" + requestCode + "," + resultCode + "," + data, Toast.LENGTH_LONG).show();

        // Pass on the activity result to the helper for handling
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            // not handled, so handle it ourselves (here's where you'd
            // perform any handling of activity results not related to in-app
            // billing...
            super.onActivityResult(requestCode, resultCode, data);
        } else {
            Log.d(Constant.TAG, "onActivityResult handled by IABUtil.");
        }
    }
}
