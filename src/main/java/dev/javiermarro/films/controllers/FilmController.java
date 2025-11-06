package dev.javiermarro.films.controllers;

import dev.javiermarro.films.Film;
import dev.javiermarro.films.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/films") //any request made to the specified endpoint in brackets will be handled by this controller
public class FilmController {

    @Autowired // needed to instantiate the service class
    private FilmService filmService;

    @GetMapping
    public ResponseEntity<List<Film>> getAllFilms(){
        // ResponseEntity<> helps return whichever data along with a full HTTP response (more in NOTES)
        return new ResponseEntity<List<Film>>(filmService.allFilms(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Film>> getFilmById(@PathVariable String imdbId){
        return new ResponseEntity<Optional<Film>>(filmService.filmById(imdbId), HttpStatus.OK);
    }
}


