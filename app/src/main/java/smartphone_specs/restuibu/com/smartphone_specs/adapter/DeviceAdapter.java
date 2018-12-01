package smartphone_specs.restuibu.com.smartphone_specs.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import smartphone_specs.restuibu.com.smartphone_specs.R;
import smartphone_specs.restuibu.com.smartphone_specs.model.DeviceItem;

public class DeviceAdapter extends BaseAdapter implements Filterable {

	private Context context;
	private List<DeviceItem> DeviceItems;
	private List<DeviceItem> OriDeviceItems;

	private Filter planetFilter;

	public DeviceAdapter(Context context, List<DeviceItem> DeviceItems) {
		this.context = context;
		this.DeviceItems = DeviceItems;
		this.OriDeviceItems = DeviceItems;
	}

	public void resetData() {
		this.DeviceItems = this.OriDeviceItems;
	}

	public int getCount() {
		return DeviceItems.size();
	}

	public DeviceItem getItem(int position) {
		return DeviceItems.get(position);
	}

	private class ViewHolder {

		TextView tvTitle, tvDescription;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater
					.inflate(R.layout.layout_device_item, parent, false);

			viewHolder = new ViewHolder();

			viewHolder.tvTitle = (TextView) convertView
					.findViewById(R.id.textView1);
			viewHolder.tvDescription = (TextView) convertView
					.findViewById(R.id.textView2);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final DeviceItem row = DeviceItems.get(position);

		viewHolder.tvTitle.setText(row.getTitle().substring(0, 1).toUpperCase() + row.getTitle().substring(1));
		viewHolder.tvDescription.setText(row.getDescription().substring(0, 1).toUpperCase() + row.getDescription().substring(1));

//		final DeviceItem row = DeviceItems.get(position);
//
//		viewHolder.tvLabel.setTextSize(TypedValue.COMPLEX_UNIT_PT, 5);
//		viewHolder.tvLabel.setText(row.getName());
//
//		viewHolder.ivIcon.setImageResource(row.getImg());

		return convertView;
	}

	@Override
	public long getItemId(int position) {
		return DeviceItems.indexOf(getItem(position));
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
				results.values = OriDeviceItems;
				results.count = OriDeviceItems.size();
			} else {
				// We perform filtering operation
				List<DeviceItem> nPlanetList = new ArrayList<DeviceItem>();

				for (int i = 0; i < DeviceItems.size(); i++) {
					if (DeviceItems.get(i).getTitle().toUpperCase()
							.contains(constraint.toString().toUpperCase()) ||
							DeviceItems.get(i).getDescription().toUpperCase()
									.contains(constraint.toString().toUpperCase())) {
						nPlanetList.add(DeviceItems.get(i));

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

				DeviceItems = (ArrayList<DeviceItem>) results.values;
				notifyDataSetChanged();
			}

		}

	}
}
