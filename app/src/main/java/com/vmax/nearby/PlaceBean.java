package com.vmax.nearby;

import java.io.Serializable;

public class PlaceBean implements Serializable {
    private int id;
    private boolean isOpen;
    private String kind;
    private double latitude;
    private double longitude;
    private String name;
    private String placeref;
    private float rating;
    private String type;
    private String vicinity;
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public float getRating() {
        return this.rating;
    }

    public String getVicinity() {
        return this.vicinity;
    }

    public String getType() {
        return this.type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPlaceref() {
        return this.placeref;
    }

    public void setPlaceref(String pubref) {
        this.placeref = pubref;
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
