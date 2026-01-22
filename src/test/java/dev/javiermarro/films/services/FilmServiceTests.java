package dev.javiermarro.films.services;

import dev.javiermarro.films.fixtures.FilmsTestFixture;
import dev.javiermarro.films.models.Film;
import dev.javiermarro.films.repositories.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Film Service")
class FilmServiceTests {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmService filmService;

    private Film avatarFilm;
    private Film interstellarFilm;
    private Film inceptionFilm;

    @BeforeEach
    void setUp() {
        avatarFilm = FilmsTestFixture.avatar();
        interstellarFilm = FilmsTestFixture.interstellar();
        inceptionFilm = FilmsTestFixture.inception();
    }

    @Test
    @DisplayName("should return all films when films exist")
    void shouldReturnAllFilms() {
        // Arrange
        List<Film> films = Arrays.asList(avatarFilm, interstellarFilm, inceptionFilm);
        when(filmRepository.findAll()).thenReturn(films);

        // Act
        List<Film> result = filmService.allFilms();

        // Assert
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(avatarFilm, interstellarFilm, inceptionFilm);
        verify(filmRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("should return empty list when no films exist")
    void shouldReturnEmptyListWhenNoFilms() {
        // Arrange
        when(filmRepository.findAll()).thenReturn(List.of());

        // Act
        List<Film> result = filmService.allFilms();

        // Assert
        assertThat(result).isEmpty();
        verify(filmRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("should call repository method exactly once for each service call")
    void shouldCallRepositoryMethodsCorrectly() {
        // Arrange
        String imdbId = "tt0816692";
        when(filmRepository.findFilmByImdbId(imdbId)).thenReturn(Optional.of(interstellarFilm));
        when(filmRepository.findAll()).thenReturn(Arrays.asList(avatarFilm, interstellarFilm));

        // Act
        filmService.filmById(imdbId);
        filmService.allFilms();

        // Assert
        verify(filmRepository, times(1)).findFilmByImdbId(imdbId);
        verify(filmRepository, times(1)).findAll();
        verifyNoMoreInteractions(filmRepository);
    }
}