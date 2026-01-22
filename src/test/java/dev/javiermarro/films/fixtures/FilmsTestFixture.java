package dev.javiermarro.films.fixtures;

import dev.javiermarro.films.models.Film;

public class FilmsTestFixture {

    public static Film avatar() {
        return createFilm("Avatar: The Way of Water", "tt1630029", "2022-12-16");
    }

    public static Film interstellar() {
        return createFilm("Interstellar", "tt0816692", "2014-11-07");
    }

    public static Film inception() {
        return createFilm("Inception", "tt1375666", "2010-07-16");
    }

    // Helper method to be reused in FilmTest files
    public static Film createFilm(String title, String imdbId, String releaseDate) {
        Film film = new Film();
        film.setTitle(title);
        film.setImdbId(imdbId);
        film.setReleaseDate(releaseDate);
        return film;
    }

}
