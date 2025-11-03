package dev.javiermarro.films;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films") //any request made to the specified endpoint in brackets will be handled by this controller
public class FilmController {

    @GetMapping
    public ResponseEntity<String> getAllFilms(){
        // ResponseEntity<> helps return whichever data along with a full HTTP response (more in NOTES)
        return new ResponseEntity<String>("All films!", HttpStatus.OK);
    }
}


