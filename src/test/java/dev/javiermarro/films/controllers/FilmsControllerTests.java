package dev.javiermarro.films.controllers;

import dev.javiermarro.films.models.Film;
import dev.javiermarro.films.services.FilmService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static dev.javiermarro.films.utils.FilmsTestFactory.createFilm;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FilmController.class)
@DisplayName("Film Controller")
class FilmControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FilmService filmService;

    @Nested
    @DisplayName("GET /api/v1/films")
    class GetAllFilmsTests {

        @Test
        @DisplayName("should return all films when films exist")
        void shouldReturnAllFilms() throws Exception {
            Film film = createFilm("Avatar: The Way of Water", "tt1630029", "2022-12-16");
            when(filmService.allFilms()).thenReturn(List.of(film));

            mockMvc.perform(get("/api/v1/films")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].title", is("Avatar: The Way of Water")));
        }

        @Test
        @DisplayName("should return empty array when no films exist")
        void shouldReturnEmptyArrayWhenNoFilms() throws Exception {
            when(filmService.allFilms()).thenReturn(List.of());

            mockMvc.perform(get("/api/v1/films")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(0)));
        }

        @Test
        @DisplayName("should return valid JSON structure with all fields")
        void shouldReturnValidJsonStructure() throws Exception {
            Film film = createFilm("Interstellar", "tt0816692", "2014-11-07");
            film.setPoster("somePosterUrl");
            when(filmService.allFilms()).thenReturn(List.of(film));

            mockMvc.perform(get("/api/v1/films")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].imdbId").value("tt0816692"))
                    .andExpect(jsonPath("$[0].releaseDate").value("2014-11-07"))
                    .andExpect(jsonPath("$[0].poster").value("somePosterUrl"));
        }
    }

    @Nested
    @DisplayName("GET /api/v1/films/{id}")
    class GetFilmByIdTests {

        @Test
        @DisplayName("should return film when ID exists")
        void shouldReturnFilmWhenIdExists() throws Exception {
            Film film = createFilm("Avatar: The Way of Water", "tt1630029", "2022-12-16");
            when(filmService.filmById("tt1630029")).thenReturn(Optional.of(film));

            mockMvc.perform(get("/api/v1/films/tt1630029"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.title").value("Avatar: The Way of Water"))
                    .andExpect(jsonPath("$.imdbId").value("tt1630029"));

            verify(filmService).filmById("tt1630029");
        }

        @Test
        @DisplayName("should return 404 when ID does not exist")
        void shouldReturn404WhenIdDoesNotExist() throws Exception {
            when(filmService.filmById("nonexistent")).thenReturn(Optional.empty());

            mockMvc.perform(get("/api/v1/films/nonexistent"))
                    .andExpect(status().isNotFound());

            verify(filmService).filmById("nonexistent");
        }
    }
}