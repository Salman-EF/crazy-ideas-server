package com.crazyideas.repositories;

import com.crazyideas.models.Thinker;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThinkerRepository extends MongoRepository<Thinker, Long> {

    public Thinker findByName(String name);
}
