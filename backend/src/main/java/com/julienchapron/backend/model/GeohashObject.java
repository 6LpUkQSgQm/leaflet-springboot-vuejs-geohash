package com.julienchapron.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "geohashs")
public class GeohashObject {

  @Id
  private String id;

  private String countryCode;
  private String name;
  private String latitude;
  private String longitude;
  private String geohashValue;
  private String createdDate;

  public GeohashObject() {
  }

  public GeohashObject(String id) {
    this.id = id;
  }

  public GeohashObject(String countryCode, String name, String latitude, String longitude, String geohashValue,
      String createdDate) {
    this.countryCode = countryCode;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
    this.geohashValue = geohashValue;
    this.createdDate = createdDate;
  }

  public String getId() {
    return id;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
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

  public String getGeohashValue() {
    return geohashValue;
  }

  public void setGeohashValue(String geohashValue) {
    this.geohashValue = geohashValue;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  @Override
  public String toString() {
    return ("Geohash [id=" + id + ", countryCode =" + countryCode + ", name=" + name + ", latitude=" + latitude
        + ", longitude=" + longitude + ", geohashValue=" + geohashValue + ", createdDate=" + createdDate + "]");
  }
}
