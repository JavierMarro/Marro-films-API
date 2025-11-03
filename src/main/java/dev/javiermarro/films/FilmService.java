package dev.javiermarro.films;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // it contains the DB access methods (from MongoRepository: insert / findAll)
public class FilmService {

    @Autowired // rather than using a constructor, the annotation instantiates the class
    private FilmRepository filmRepo; // referencing the repository as a private field

    public List<Film> allFilms() {
        return filmRepo.findAll();
    }
}