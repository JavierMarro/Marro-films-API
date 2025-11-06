package dev.javiermarro.films.services;

import dev.javiermarro.films.models.Film;
import dev.javiermarro.films.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // it contains the DB access methods (from MongoRepository: insert / findAll)
public class FilmService {

    @Autowired // rather than using a constructor, the annotation instantiates the class
    private FilmRepository filmRepo; // referencing the repository as a private field

    public List<Film> allFilms() {
        return filmRepo.findAll();
    }

    // Optional helps return null if the id requested does not exist
    // initially the film ObjectId was being passed in, but we want to avoid that so we do not expose the id to the public
    // instead, a custom method was defined in the FilmRepository interface and now a String with a reference is used
    public Optional<Film> filmById(String imdbId){
        // return filmRepo.findById(id); // this was the initial implementation using the MongoRepository built-in method
        return filmRepo.findFilmByImdbId(imdbId);
    }
}