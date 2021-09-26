package com.julienchapron.backend.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.julienchapron.backend.graphql.types.*;
import com.julienchapron.backend.model.*;
import com.julienchapron.backend.repository.*;

@Component
public class Mutation implements GraphQLMutationResolver {
   private final GeohashRepository geohashRepository;
   private final GeohashTypeRepository geohashTypeRepository;

    public Mutation(GeohashRepository geohashRepository) {
        this.geohashRepository = geohashRepository;
        this.geohashTypeRepository = geohashTypeRepository;
    }

    public AddGeohashPayload addGeohash(AddGeohashInput addGeohashInput) {

        final GeohashType type = geohashTypeRepository.findById(addGeohashInput.getTypeId());

        Geohash geohash = new Geohash();
        geohash.setGeohash(addGeohashInput.getGeohash());
        geohash.setLongitude(addLongitudeInput.getLongitude());
        geohash.setLatitude(addLatitudeInput.getLatitude());
        geohashRepository.save(geohash);
        return new AddGeohashPayload(geohash);
    }

    public UpdateGeohashPayload updateGeohash(UpdateGeohashInput updateGeohashInput) {
        final Geohash geohash = geohashRepository.findById(updateGeohashInput.getPetId());
        if (updateGeohashInput.getBirthDate() != null) {
            geohash.setBirthDate(updateGeohashInput.getBirthDate());
        }

        if (updateGeohashInput.getName() != null) {
            geohash.setName(updateGeohashInput.getName());
        }

        if (updateGeohashInput.getTypeId() != null) {
            GeohashType type = geohashTypeRepository.findById(updateGeohashInput.getTypeId());
            geohash.setType(type);
        }

        geohashRepository.save(geohash);

        return new UpdateGeohashInput(geohash);
    }
}
