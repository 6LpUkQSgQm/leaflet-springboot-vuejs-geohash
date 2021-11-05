package com.julienchapron.backend.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.julienchapron.backend.model.Geohash;
import com.julienchapron.backend.repository.GeohashRepository;

import java.io.OptionalDataException;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.base.Optional;
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

