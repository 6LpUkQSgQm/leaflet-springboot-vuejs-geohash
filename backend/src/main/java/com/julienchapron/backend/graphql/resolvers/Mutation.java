package com.julienchapron.backend.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.julienchapron.backend.graphql.types.*;
import com.julienchapron.backend.model.*;
import com.julienchapron.backend.repository.*;

public class Mutation implements GraphQLMutationResolver {
   private final GeohashRepository geohashRepository;

    public Mutation(GeohashRepository geohashRepository) {
        this.geohashRepository = geohashRepository;
    }

    public AddGeohashPayload addGeohash(AddGeohashInput addGeohashInput) {
        Geohash geohash = new Geohash();
        geohash.setGeohash(addGeohashInput.getGeohash());
        geohashRepository.save(geohash);
        return new AddGeohashPayload(geohash);
    }
}

