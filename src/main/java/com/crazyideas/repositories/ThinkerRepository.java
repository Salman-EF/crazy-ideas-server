package com.crazyideas.repositories;

import com.crazyideas.models.Thinker;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThinkerRepository extends MongoRepository<Thinker, ObjectId> {

    Thinker findByName(String name);
}
