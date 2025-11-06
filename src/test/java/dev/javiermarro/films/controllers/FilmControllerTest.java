package dev.javiermarro.films.controllers;

import dev.javiermarro.films.models.Film;
import dev.javiermarro.films.services.FilmService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(FilmController.class)
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FilmService filmService;

    @Nested
    class GetAllFilmsTests {

        @Test
        void testGetAllFilms() throws Exception {
            Film film = new Film();
            film.setTitle("Avatar: The Way of Water");
            film.setImdbId("tt1630029");
            film.setReleaseDate("2022-12-16");

            when(filmService.allFilms()).thenReturn(List.of(film));

            mockMvc.perform(get("/api/v1/films").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].title", is("Avatar: The Way of Water")));
        }

        @Test
        void testEmptyArrayWhenNoFilms() throws Exception {
            when(filmService.allFilms()).thenReturn(List.of());

            mockMvc.perform(get("/api/v1/films").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(0)));
        }

        @Test
        void testValidJsonStructure() throws Exception {
            Film film = new Film();
            film.setTitle("Interstellar");
            film.setImdbId("tt0816692");
            film.setReleaseDate("2014-11-07");
            film.setPoster("somePosterUrl");

            when(filmService.allFilms()).thenReturn(List.of(film));

            mockMvc.perform(get("/api/v1/films").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].imdbId").value("tt0816692"))
                    .andExpect(jsonPath("$[0].releaseDate").value("2014-11-07"))
                    .andExpect(jsonPath("$[0].poster").value("somePosterUrl"));
        }
    }
}
