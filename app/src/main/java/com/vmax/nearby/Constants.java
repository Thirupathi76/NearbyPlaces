package com.vmax.nearby;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    Map<String, String> places = new HashMap();
    public String[] places_list = new String[]{"airport",  "aquarium", "atm", "bakery", "bank",
            "bar", "beauty_salon", "book_store", "bowling_alley", "bus_station", "cafe", "car_rental",
            "car_repair", "car_wash", "casino", "church", "convenience_store", "dentist", "department_store",
            "doctor", "electronics_store", "fire_station", "food", "gas_station", "gym", "hindu_temple",
            "hospital", "jewelry_store", "lawyer", "library", "liquor_store", "local_government_office",
            "lodging", "mosque", "movie_theater","amusement_park", "museum", "night_club", "park", "parking",
            "pet_store", "police", "post_office", "school", "shopping_mall", "spa", "stadium", "taxi_stand",
            "train_station", "veterinary_care", "zoo"};

    public Constants() {
        addPlaces();
    }

    private void addPlaces() {
        this.places.put("airport", "airport");
        this.places.put("atm", "atm");
        this.places.put("bakery", "bakery");
        this.places.put("bar", "bar");
        this.places.put("book_store", "book_store");
        this.places.put("bus_station", "bus_station");
        this.places.put("bowling_alley", "bowling_alley");
        this.places.put("cafe", "cafe");
        this.places.put("car_rental", "car_rental");
        this.places.put("car_wash", "car_wash");
        this.places.put("dentist", "dentist");
        this.places.put("department_store", "department_store");
        this.places.put("doctor", "doctor");
        this.places.put("electronics_store", "electronics_store");
        this.places.put("food", "restaurant");
        this.places.put("gas_station", "gas_station");
        this.places.put("taxi_stand", "taxi_stand");
        this.places.put("train_station", "train_station");
        this.places.put("parking", "parking");
        this.places.put("post_office", "postoffice");
        this.places.put("lodging", "lodging");
        this.places.put("hospital", "hospital");
        this.places.put("library", "library");
        this.places.put("liquor_store", "liquor_store");
        this.places.put("shopping_mall", "shopping_mall");
        this.places.put("movie_theater", "movie_theater");
        this.places.put("amusement_park", "amusement_park");
        this.places.put("aquarium", "aquarium");
        this.places.put("bank", "bank");
        this.places.put("beauty_salon", "beauty_salon");
        this.places.put("car_repair", "car_repair");
        this.places.put("church", "church");
        this.places.put("local_government_office", "local_government_office");
        this.places.put("school", "school");
        this.places.put("veterinary_care", "veterinary_care");
        this.places.put("night_club", "night_club");
        this.places.put("casino", "casino");
        this.places.put("church", "church");
        this.places.put("convenience_store", "convenience_store");
        this.places.put("fire_station", "fire_station");
        this.places.put("gym", "gym");
        this.places.put("hindu_temple", "hindu_temple");
        this.places.put("jewelry_store", "jewelry_store");
        this.places.put("lawyer", "lawyer");
        this.places.put("mosque", "mosque");
        this.places.put("museum", "museum");
        this.places.put("park", "park");
        this.places.put("pet_store", "pet_store");
        this.places.put("police", "police");
        this.places.put("spa", "spa");
        this.places.put("stadium", "stadium");
        this.places.put("zoo", "zoo");
    }
}
