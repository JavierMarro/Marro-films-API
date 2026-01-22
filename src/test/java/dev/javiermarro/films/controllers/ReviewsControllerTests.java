package dev.javiermarro.films.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.javiermarro.films.fixtures.ReviewsTestFixture;
import dev.javiermarro.films.models.Review;
import dev.javiermarro.films.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static dev.javiermarro.films.fixtures.ReviewsTestFixture.createReview;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
@DisplayName("Reviews Controller")
public class ReviewsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ReviewService reviewService;

    private Review positiveReview;
    private Review neutralReview;

    @BeforeEach
    void setUp() {
        positiveReview = ReviewsTestFixture.positive();
        neutralReview = ReviewsTestFixture.neutral();
    }

    @Nested
    @DisplayName("POST /api/v1/reviews")
    class CreateReviewTests {

        @Test
        @DisplayName("should create review and return 201 when valid data is provided")
        void shouldCreateReviewSuccessfully() throws Exception {
            // Arrange
            String imdbId = "tt1630029";
            Review newReview = createReview(String.valueOf(positiveReview));

            Map<String, String> payload = new HashMap<>();
            payload.put("reviewBody", positiveReview.getBody());
            payload.put("imdbId", imdbId);

            when(reviewService.createReview(String.valueOf(positiveReview), imdbId)).thenReturn(newReview);

            // Act & Assert
            mockMvc.perform(post("/api/v1/reviews")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(payload)))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.body").value(positiveReview.getBody()));

            verify(reviewService).createReview(String.valueOf(positiveReview), imdbId);
        }

        @Test
        @DisplayName("should return 400 when a review is submitted empty")
        void shouldRejectEmptyReviewBody() throws Exception {
            // Arrange
            Map<String, String> payload = new HashMap<>();
            payload.put("reviewBody", "");
            payload.put("imdbId", "tt1630029");

            // Act & Assert
            mockMvc.perform(post("/api/v1/reviews")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(payload)))
                    .andExpect(status().isBadRequest());

            verify(reviewService, never()).createReview(anyString(), anyString());
        }

        @Test
        @DisplayName("should return 400 when review body is null")
        void shouldRejectNullReviewBody() throws Exception {
            // Arrange
            Map<String, String> payload = new HashMap<>();
            payload.put("reviewBody", null);
            payload.put("imdbId", "tt1630029");

            // Act & Assert
            mockMvc.perform(post("/api/v1/reviews")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(payload)))
                    .andExpect(status().isBadRequest());

            verify(reviewService, never()).createReview(anyString(), anyString());
        }

        @Test
        @DisplayName("should return 400 when review body is only whitespace")
        void shouldRejectWhitespaceOnlyReview() throws Exception {
            // Arrange
            Map<String, String> payload = new HashMap<>();
            payload.put("reviewBody", "   ");
            payload.put("imdbId", "tt1630029");

            // Act & Assert
            mockMvc.perform(post("/api/v1/reviews")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(payload)))
                    .andExpect(status().isBadRequest());

            verify(reviewService, never()).createReview(anyString(), anyString());
        }

        @Test
        @DisplayName("should return 400 when imdbId is empty")
        void shouldRejectEmptyImdbId() throws Exception {
            // Arrange
            Map<String, String> payload = new HashMap<>();
            payload.put("reviewBody", "What a banger!");
            payload.put("imdbId", "");

            // Act & Assert
            mockMvc.perform(post("/api/v1/reviews")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(payload)))
                    .andExpect(status().isBadRequest());

            verify(reviewService, never()).createReview(anyString(), anyString());
        }

        @Test
        @DisplayName("should return 400 when imdbId is null")
        void shouldRejectNullImdbId() throws Exception {
            // Arrange
            Map<String, String> payload = new HashMap<>();
            payload.put("reviewBody", "Outstanding film!");
            payload.put("imdbId", null);

            // Act & Assert
            mockMvc.perform(post("/api/v1/reviews")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(payload)))
                    .andExpect(status().isBadRequest());

            verify(reviewService, never()).createReview(anyString(), anyString());
        }

        @Test
        @DisplayName("should call service with valid parameters")
        void shouldCallServiceWithValidParameters() throws Exception {
            // Arrange
            String imdbId = "tt0111161";
            Review createdReview = createReview(String.valueOf(neutralReview));

            Map<String, String> payload = new HashMap<>();
            payload.put("reviewBody", neutralReview.getBody());
            payload.put("imdbId", imdbId);

            when(reviewService.createReview(String.valueOf(neutralReview), imdbId)).thenReturn(createdReview);

            // Act
            mockMvc.perform(post("/api/v1/reviews")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(payload)))
                    .andExpect(status().isCreated());

            // Assert
            verify(reviewService, times(1)).createReview(String.valueOf(neutralReview), imdbId);
        }
    }
}
