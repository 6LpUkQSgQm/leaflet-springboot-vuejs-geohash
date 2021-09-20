package com.ljulienchapron.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "geohash")
public class Geohash {
  @Id
  private String id;
  private String latitude;
  private String longitude;
  private boolean geohash;
}
