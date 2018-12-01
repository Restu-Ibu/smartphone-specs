package smartphone_specs.restuibu.com.smartphone_specs.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import smartphone_specs.restuibu.com.smartphone_specs.R;
import smartphone_specs.restuibu.com.smartphone_specs.model.ListDeviceItem;

import static smartphone_specs.restuibu.com.smartphone_specs.activity.MainActivity.list;
import static smartphone_specs.restuibu.com.smartphone_specs.activity.MainActivity.adapter2;
import static smartphone_specs.restuibu.com.smartphone_specs.activity.MainActivity.flag_adapter;

public class ListDeviceAdapter extends BaseAdapter implements Filterable {

	private Context context;
	private List<ListDeviceItem> ListDeviceItems;
	private List<ListDeviceItem> OriListDeviceItems;

	private Filter planetFilter;

	public ListDeviceAdapter(Context context, List<ListDeviceItem> ListDeviceItems) {
		this.context = context;
		this.ListDeviceItems = ListDeviceItems;
		this.OriListDeviceItems = ListDeviceItems;
	}

	public void resetData() {
		this.ListDeviceItems = this.OriListDeviceItems;
	}

	public int getCount() {
		return ListDeviceItems.size();
	}

	public ListDeviceItem getItem(int position) {
		return ListDeviceItems.get(position);
	}

	private class ViewHolder {

		TextView tvDevice;
		LinearLayout llLayout;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater
					.inflate(R.layout.layout_list_device_item, parent, false);

			viewHolder = new ViewHolder();

			viewHolder.tvDevice = (TextView) convertView
					.findViewById(R.id.textView1);
			viewHolder.llLayout = (LinearLayout) convertView
					.findViewById(R.id.linearLayout1);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final ListDeviceItem row = ListDeviceItems.get(position);
		String no = Integer.toString(position + 1);

		viewHolder.tvDevice.setText(no + ". "+ row.getDevice().substring(0, 1).toUpperCase() + row.getDevice().substring(1));

		viewHolder.llLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				adapter2 = new DeviceAdapter(context, row.getDeviceItems());
				list.setAdapter(adapter2);
				flag_adapter = 2;
			}
		});


		//Toast.makeText(MainActivity.this, "First device: " + device.getDeviceName(), Toast.LENGTH_SHORT).show();

		return convertView;
	}

	@Override
	public long getItemId(int position) {
		return ListDeviceItems.indexOf(getItem(position));
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
				results.values = OriListDeviceItems;
				results.count = OriListDeviceItems.size();
			} else {
				// We perform filtering operation
				List<ListDeviceItem> nPlanetList = new ArrayList<ListDeviceItem>();

				for (int i = 0; i < ListDeviceItems.size(); i++) {
					if (ListDeviceItems.get(i).getDevice().toUpperCase()
							.contains(constraint.toString().toUpperCase())) {
						nPlanetList.add(ListDeviceItems.get(i));

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

				ListDeviceItems = (ArrayList<ListDeviceItem>) results.values;
				notifyDataSetChanged();
			}

		}

	}
}
