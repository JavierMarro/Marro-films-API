//package dev.javiermarro.films;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//@SpringBootTest
//class FilmsApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//}

// controller tests only load the web layer
// the annotation @SpringBootTest tries to load the full application context including the MongoDB connection, which fails
// TODO: look into TestContainers for this and ReviewServiceTests