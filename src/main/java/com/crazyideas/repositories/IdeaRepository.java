package com.crazyideas.repositories;

import com.crazyideas.models.Idea;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IdeaRepository extends MongoRepository<Idea, String> {
}
