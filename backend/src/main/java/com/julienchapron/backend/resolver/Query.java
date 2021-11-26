package com.julienchapron.backend.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.julienchapron.backend.model.GeohashObject;
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

  public Iterable<GeohashObject> findAllGeohashs() {
    return geohashRepository.findAll();
  }
}
