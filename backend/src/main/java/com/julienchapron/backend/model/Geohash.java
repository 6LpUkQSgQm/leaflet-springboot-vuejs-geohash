
package com.julienchapron.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "geohashs")
public class Geohash {

    private String _id;
    private String geohash;

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }
}