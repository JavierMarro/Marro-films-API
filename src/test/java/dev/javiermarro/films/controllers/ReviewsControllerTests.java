package dev.javiermarro.films.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.javiermarro.films.models.Review;
import dev.javiermarro.films.services.ReviewService;
import org.bson.types.ObjectId;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

    @Nested
    @DisplayName("POST /api/v1/reviews")
    class CreateReviewTests {

        @Test
        @DisplayName("should create review and return 201 when valid payload is provided")
        void shouldCreateReviewSuccessfully() throws Exception {
            // Arrange
            String reviewBody = "This is my favourite film!";
            String imdbId = "tt1630029";
            Review newReview = createReview(reviewBody);

            Map<String, String> payload = new HashMap<>();
            payload.put("reviewBody", reviewBody);
            payload.put("imdbId", imdbId);

            when(reviewService.createReview(reviewBody, imdbId)).thenReturn(newReview);

            // Act & Assert
            mockMvc.perform(post("/api/v1/reviews")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(payload)))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.body").value(reviewBody));

            verify(reviewService).createReview(reviewBody, imdbId);
        }

        // Helper method
        private Review createReview(String body) {
            Review review = new Review();
            review.setId(new ObjectId());
            review.setBody(body);
            return review;
        }
    }
}
