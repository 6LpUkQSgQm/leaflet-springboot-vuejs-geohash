package com.julienchapron.backend.graphql.types;

public class AbstractGeohashPayload {

    private Integer typeId;
    private String geohash;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    @Override
    public String toString() {
        return "AbstractGeohashInput{" +
            "typeId=" + typeId +
            ", geohash='" + geohash +
            '}';
    }
}
