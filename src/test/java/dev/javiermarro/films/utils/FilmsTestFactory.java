package dev.javiermarro.films.utils;

import dev.javiermarro.films.models.Film;

public class FilmsTestFactory {
    // Helper method to be reused in FilmTest files
    public static Film createFilm(String title, String imdbId, String releaseDate) {
        Film film = new Film();
        film.setTitle(title);
        film.setImdbId(imdbId);
        film.setReleaseDate(releaseDate);
        return film;
    }

}
