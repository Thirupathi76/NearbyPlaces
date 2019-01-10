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
        places.put("airport", "airport");
        places.put("atm", "atm");
        places.put("bakery", "bakery");
        places.put("bar", "bar");
        places.put("book_store", "book_store");
        places.put("bus_station", "bus_station");
        places.put("bowling_alley", "bowling_alley");
        places.put("cafe", "cafe");
        places.put("car_rental", "car_rental");
        places.put("car_wash", "car_wash");
        places.put("dentist", "dentist");
        places.put("department_store", "department_store");
        places.put("doctor", "doctor");
        places.put("electronics_store", "electronics_store");
        places.put("food", "restaurant");
        places.put("gas_station", "gas_station");
        places.put("taxi_stand", "taxi_stand");
        places.put("train_station", "train_station");
        places.put("parking", "parking");
        places.put("post_office", "postoffice");
        places.put("lodging", "lodging");
        places.put("hospital", "hospital");
        places.put("library", "library");
        places.put("liquor_store", "liquor_store");
        places.put("shopping_mall", "shopping_mall");
        places.put("movie_theater", "movie_theater");
        places.put("amusement_park", "amusement_park");
        places.put("aquarium", "aquarium");
        places.put("bank", "bank");
        places.put("beauty_salon", "beauty_salon");
        places.put("car_repair", "car_repair");
        places.put("church", "church");
        places.put("local_government_office", "local_government_office");
        places.put("school", "school");
        places.put("veterinary_care", "veterinary_care");
        places.put("night_club", "night_club");
        places.put("casino", "casino");
        places.put("convenience_store", "convenience_store");
        places.put("fire_station", "fire_station");
        places.put("gym", "gym");
        places.put("hindu_temple", "hindu_temple");
        places.put("jewelry_store", "jewelry_store");
        places.put("lawyer", "lawyer");
        places.put("mosque", "mosque");
        places.put("museum", "museum");
        places.put("park", "park");
        places.put("pet_store", "pet_store");
        places.put("police", "police");
        places.put("spa", "spa");
        places.put("stadium", "stadium");
        places.put("zoo", "zoo");
    }
}
