package com.julienchapron.backend.resolver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.julienchapron.backend.model.Geohash;
import com.julienchapron.backend.repository.GeohashRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class Mutation implements GraphQLMutationResolver {
    private GeohashRepository geohashRepository;

    @Autowired
    public Mutation(GeohashRepository geohashRepository) {
        this.geohashRepository = geohashRepository;
    }

    public Geohash createGeohash(String name, String longitude, String latitude) {
        Geohash geohash = new Geohash();
        geohash.setName(name);
        geohash.setLongitude(longitude);
        geohash.setLatitude(latitude);
        geohashRepository.save(geohash);
        return geohash;
    }

}