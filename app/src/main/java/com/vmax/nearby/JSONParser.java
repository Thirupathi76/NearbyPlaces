package com.vmax.nearby;

import android.content.Context;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {
    private String kind;
    private List<PlaceBean> placeBeanList = new ArrayList();
    private String placesData;

    public JSONParser(String data, String kind) {
        this.placesData = data;
        this.kind = kind;
    }

    public JSONArray getJSONArray(String data) {
        JSONArray jsonArray = null;
        if (data != null) {
            try {
                jsonArray = new JSONObject(data).getJSONArray("results");
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error in parsing", e);
            }
        }
        return jsonArray;
    }

    public List<PlaceBean> getPlaceBeanList() throws Exception {
        boolean isOpen = false;
        if (this.placesData == null) {
            return null;
        }
        try {
            JSONArray jsonArray = getJSONArray(this.placesData);
            for (int i = 0; i < jsonArray.length(); i++) {
                double latitude;
                double longitude;
                String name, icon;
                float rating;
                String id;
                String vicinity;
                String type;
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                PlaceBean pb = new PlaceBean();
                if (jsonObject.has("geometry")) {
                    JSONObject geometry = jsonObject.getJSONObject("geometry");
                    if (geometry.has(Context.LOCATION_SERVICE)){
                        JSONObject location = geometry.getJSONObject(Context.LOCATION_SERVICE);
                        latitude = location.getDouble("lat");
                        longitude = location.getDouble("lng");
                    } else {
                        latitude = 0.0d;
                        longitude = 0.0d;
                    }
                } else {
                    latitude = 0.0d;
                    longitude = 0.0d;
                }
                if (jsonObject.has("icon")){
                    icon = jsonObject.getString("icon");
//                    Log.e("Icon Url ser", jsonObject.getString("icon"));
                } else icon = "";
                if (jsonObject.has("name")) {
                    name = jsonObject.getString("name");
                } else {
                    name = "Not available";
                }
                if (jsonObject.has("rating")) {
                    rating = (float) jsonObject.getDouble("rating");
                } else {
                    rating = 0.0f;
                }
                if (jsonObject.has("opening_hours")) {
                    JSONObject opening_hours = jsonObject.getJSONObject("opening_hours");
                    if (opening_hours.has("open_now")) {
                        isOpen = opening_hours.getBoolean("open_now");
                    } else {
                        isOpen = false;
                    }
                } else {
                    pb.setIsOpen(false);
                }
                if (jsonObject.has("place_id")) {
                    id = jsonObject.getString("place_id");
                } else {
                    id = "Not Available";
                }
                if (jsonObject.has("vicinity")) {
                    vicinity = jsonObject.getString("vicinity");
                } else {
                    vicinity = "Not Available";
                }
                if (jsonObject.has("types")) {
                    JSONArray types = jsonObject.getJSONArray("types");
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < types.length(); j++) {
                        if (j != types.length() - 1) {
                            sb.append(types.getString(j) + " | ");
                        } else {
                            sb.append(types.getString(j));
                        }
                    }
                    type = sb.toString();
                } else {
                    type = "Not Available";
                }
                pb.setLatitude(latitude);
                pb.setLongitude(longitude);
                pb.setPlaceref(id);
                pb.setIsOpen(isOpen);
                pb.setName(name);
                pb.setIcon(icon);
                pb.setRating(rating);
                pb.setVicinity(vicinity);
                pb.setType(type);
                pb.setKind(this.kind);
                this.placeBeanList.add(pb);
            }
            return this.placeBeanList;
        } catch (JSONException ex) {
            Log.e("JSON Parsing", "Not able to parse", ex);
            throw new Exception("Something went wrong on server.");
        }
    }
}
