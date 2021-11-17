package com.julienchapron.backend.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.TypeClassMatcher.Location;
import com.julienchapron.backend.helpers.GeoHashHelper;
import com.julienchapron.backend.model.Geohash;
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

  public Geohash createGeohash(
    String countryCode,
    String name,
    String longitude,
    String latitude
  ) {
    // geohash
    final String locationGeohash = GeoHashHelper.getGeohash(latitude, longitude);
    Geohash geohash = new Geohash();
    geohash.setCountryCode(countryCode);
    geohash.setName(name);
    geohash.setLatitude(latitude);
    geohash.setLongitude(longitude);
    geohash.setGeohashValue("56567");
    geohash.setCreatedDate(timeString);
    geohashRepository.save(geohash);
    return geohash;
  }
}
