package dev.javiermarro.films;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// this is the layer of the application that talks to the DB
// it normally extends JpaRepository, CrudRepository, or MongoRepository (depending on DB)
// defines the entity type and ID type (but can also define custom query methods)
@Repository
public interface FilmRepository extends MongoRepository<Film, ObjectId> {
}
