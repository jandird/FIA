package com.dalipjandir.fiaandroid;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

//used to display the resulting flags in the results page (formatting)

public class SimpleArrayAdapter extends ArrayAdapter<ListObject> {
    private final Context context;
    private final List<ListObject> values;
    private int resourceID;

    public SimpleArrayAdapter(Context context, int resourceID, List<ListObject> values) {
        super(context, resourceID, values);
        this.context = context;
        this.resourceID = resourceID;
        this.values = values;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resourceID, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.countryName);
            holder.imageView = (ImageView) convertView.findViewById(R.id.countryImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ListObject rowItem = (ListObject) values.get(position);
        holder.textView.setText(rowItem.getCountry());
        holder.imageView.setImageBitmap(rowItem.getImage());

        return convertView;
    }

}