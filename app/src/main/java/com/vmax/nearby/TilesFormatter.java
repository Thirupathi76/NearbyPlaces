package com.vmax.nearby;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by welcome on 2/9/2018.
 */

public class TilesFormatter extends BaseAdapter {

    LayoutInflater inflater;
    private Context mContext;
    Constants places = new Constants();
    Typeface robotobold;

    public TilesFormatter(Context c) {
        this.mContext = c;
        this.inflater = (LayoutInflater)
                this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return this.places.places_list.length;
    }

    public Object getItem(int position) {
        return this.places.places_list[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        String place_id = this.places.places_list[position];
        String icon_id = (String) this.places.places.get(place_id);
        convertView = this.inflater.inflate(R.layout.grid_item, parent, false);
        if (convertView != null) {
            try {
                ImageView place_img =  convertView.findViewById(R.id.place_img);
                TextView place_text = convertView.findViewById(R.id.place_text);
                if (icon_id != null) {
                    place_img.setImageDrawable(this.mContext.getResources().getDrawable(getDrawable
                            (this.mContext, icon_id)));
                }
                if (place_id == "local_government_office") {
                    place_id = "government_office";
                }
                if (place_id == "grocery_or_supermarket") {
                    place_id = "supermarket";
                }
                place_text.setText(place_id.toUpperCase().replace("_", " "));
            } catch (Exception e) {
                Log.e("Places", place_id);
            }
        }
        return convertView;
    }

    public static int getDrawable(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}
