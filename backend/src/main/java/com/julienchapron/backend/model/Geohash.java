package com.julienchapron.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "geohashs")
public class Geohash {
    @Id
    private String id;
    private String name;
    private String latitude;
    private String longitude;
    private String geohashValue;

    public Geohash() {
    }

    public Geohash(String id) {
        this.id = id;
    }

    public Geohash(String name, String latitude, String longitude, String geohashValue) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.geohashValue = geohashValue;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setGeohashValue(String geohashValue) {
        this.geohashValue = geohashValue;
    }

    @Override
    public String toString() {
        return "Geohash [id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude
                + ", geohash=" + geohashValue + "]";
    }
}