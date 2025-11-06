package dev.javiermarro.films.repositories;

import dev.javiermarro.films.models.Film;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// this is the layer of the application that talks to the DB
// it normally extends JpaRepository, CrudRepository, or MongoRepository (depending on DB)
// defines the entity type, ID type and some built-in methods (but can also define custom query methods, for example findFilmByImdbId)
@Repository
public interface FilmRepository extends MongoRepository<Film, ObjectId> {
    Optional<Film> findFilmByImdbId(String imdbId);
}
