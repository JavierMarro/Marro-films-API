package dev.javiermarro.films;

import dev.javiermarro.films.repositories.FilmRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


@SpringBootTest
class FilmsApplicationTests {

	@MockitoBean
	private FilmRepository filmRepository;  // this mocks out the DB

	@Test
	void contextLoads() {}
}

