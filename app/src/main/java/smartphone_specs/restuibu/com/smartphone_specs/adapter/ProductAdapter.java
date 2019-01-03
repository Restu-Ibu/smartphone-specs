package smartphone_specs.restuibu.com.smartphone_specs.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import smartphone_specs.restuibu.com.smartphone_specs.R;
import smartphone_specs.restuibu.com.smartphone_specs.activity.ProductsActivity;
import smartphone_specs.restuibu.com.smartphone_specs.model.PurchaseItem;
import smartphone_specs.restuibu.com.smartphone_specs.util.Constant;
import smartphone_specs.restuibu.com.smartphone_specs.util_billing.IabHelper;
import smartphone_specs.restuibu.com.smartphone_specs.util_billing.IabResult;
import smartphone_specs.restuibu.com.smartphone_specs.util_billing.Purchase;

import static smartphone_specs.restuibu.com.smartphone_specs.activity.ProductsActivity.mHelper;

public class ProductAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private List<PurchaseItem> PurchaseItems;
    private List<PurchaseItem> OriPurchaseItems;

    private Filter planetFilter;

    public ProductAdapter(Context context, List<PurchaseItem> PurchaseItems) {
        this.context = context;
        this.PurchaseItems = PurchaseItems;
        this.OriPurchaseItems = PurchaseItems;
    }

    public void resetData() {
        this.PurchaseItems = this.OriPurchaseItems;
    }

    public int getCount() {
        return PurchaseItems.size();
    }

    public PurchaseItem getItem(int position) {
        return PurchaseItems.get(position);
    }

    private class ViewHolder {

        TextView tvTitle;
        TextView tvPrice;
        TextView tvDesc;
        LinearLayout llBody;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater
                    .inflate(R.layout.layout_product, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.tvTitle = (TextView) convertView
                    .findViewById(R.id.textView1);
            viewHolder.tvPrice = (TextView) convertView
                    .findViewById(R.id.textView3);
            viewHolder.tvDesc = (TextView) convertView
                    .findViewById(R.id.textView2);
            viewHolder.llBody = (LinearLayout) convertView.findViewById(R.id.linearLayout1);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final PurchaseItem row = PurchaseItems.get(position);

        //viewHolder.tvTitle.setText(row.getTitle().split(" ")[0]+ " " + row.getTitle().split("")[1]);
        viewHolder.tvPrice.setText(row.getPrice());
        viewHolder.tvDesc.setText(row.getDescription());
        viewHolder.llBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mHelper.launchPurchaseFlow((Activity)context, row.getId(), Constant.RC_REQUEST, mPurchaseFinishedListener, "");
                    //mHelper.consumeAsync(p, mConsumeFinishedListener);


                } catch (IabHelper.IabAsyncInProgressException e) {
                    Log.d(Constant.TAG, "onProductClick: " + e.getMessage());
                }
            }
        });

        return convertView;
    }

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        @Override
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            Log.d(Constant.TAG, "Purchase finished: " + result + ", purchase: " + purchase);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            if (result.isFailure()) {
                Log.d(Constant.TAG,"Error purchasing: " + result);
                return;
            }

            Log.d(Constant.TAG, "Purchase successful." + purchase);

            try {
                mHelper.consumeAsync(purchase, mConsumeFinishedListener);
            } catch (IabHelper.IabAsyncInProgressException e) {
                e.printStackTrace();
            }
        }
    };

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        @Override
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            Log.d(Constant.TAG, "Consumption finished. Purchase: " + purchase + ", result: " + result);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            if (result.isSuccess()) {
                Log.d(Constant.TAG, "Consumption successful. Provisioning." + purchase.getSku());
            } else {
                Log.d(Constant.TAG, "Error while consuming: " + result);
            }
            Log.d(Constant.TAG, "End consumption flow.");
        }
    };

    @Override
    public long getItemId(int position) {
        return PurchaseItems.indexOf(getItem(position));
    }

    @Override
    public Filter getFilter() {
        if (planetFilter == null)
            planetFilter = new PlanetFilter();

        return planetFilter;
    }

    private class PlanetFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = OriPurchaseItems;
                results.count = OriPurchaseItems.size();
            } else {
                // We perform filtering operation
                List<PurchaseItem> nPlanetList = new ArrayList<PurchaseItem>();

                for (int i = 0; i < PurchaseItems.size(); i++) {
                    if (PurchaseItems.get(i).getId().toUpperCase()
                            .contains(constraint.toString().toUpperCase())) {
                        nPlanetList.add(PurchaseItems.get(i));

                    }
                }

                results.values = nPlanetList;
                results.count = nPlanetList.size();

            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {

                PurchaseItems = (ArrayList<PurchaseItem>) results.values;
                notifyDataSetChanged();
            }

        }

    }
}
