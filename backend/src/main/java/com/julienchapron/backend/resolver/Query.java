package com.julienchapron.backend.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.julienchapron.backend.model.Geohash;
import com.julienchapron.backend.repository.GeohashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

  private GeohashRepository geohashRepository;

  @Autowired
  public Query(GeohashRepository geohashRepository) {
    this.geohashRepository = geohashRepository;
  }

  public Iterable<Geohash> findAllGeohashs() {
    return geohashRepository.findAll();
  }

  public java.util.Optional<Geohash> findGeohash(String id) {
    return geohashRepository.findById(id);
  }
}
