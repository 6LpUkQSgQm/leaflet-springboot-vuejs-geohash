package com.julienchapron.backend.resolver;

import ch.hsr.geohash.GeoHash;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.julienchapron.backend.model.GeohashObject;
import com.julienchapron.backend.repository.GeohashRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

  private GeohashRepository geohashRepository;

  @Autowired
  public Mutation(GeohashRepository geohashRepository) {
    this.geohashRepository = geohashRepository;
  }

  // current date
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
    "dd.MM.yyyy HH:mm:ss"
  );
  LocalDateTime today = LocalDateTime.now();
  String timeString = today.format(formatter);

  // create
  public GeohashObject createGeohash(
    String countryCode,
    String name,
    String longitude,
    String latitude
  ) {
    // geohash
    double lat = Double.parseDouble(latitude);
    double lon = Double.parseDouble(longitude);
    int precision = 8;
    GeoHash geoHash = GeoHash.withCharacterPrecision(lat, lon, precision);
    String hashCode = geoHash.toBase32();
    GeohashObject geohashObject = new GeohashObject();
    geohashObject.setCountryCode(countryCode);
    geohashObject.setName(name);
    geohashObject.setLatitude(latitude);
    geohashObject.setLongitude(longitude);
    geohashObject.setGeohashValue(hashCode);
    geohashObject.setCreatedDate(timeString);
    geohashRepository.save(geohashObject);
    return geohashObject;
  }

  // delete
  public boolean deleteGeohash(String id) {
    geohashRepository.deleteById(id);
    return true;
  }
}
