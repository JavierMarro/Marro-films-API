package dev.javiermarro.films.controllers;

import dev.javiermarro.films.models.Review;
import dev.javiermarro.films.services.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // annotation @RequestBody: whatever we get as the request body we want to convert it into a Map which takes String as a key value pair
    // HttpStatus.Created for 201 http results
    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody Map<String, String> payload){
        String reviewBody = payload.get("reviewBody");
        String imdbId = payload.get("imdbId");

        // Performing manual validation for now
        // TODO: Look into DTO https://www.baeldung.com/java-dto-pattern https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
        if (reviewBody == null || reviewBody.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("A review cannot be blank");
        }

        if (imdbId == null || imdbId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Film IMDB ID is required");
        }

        Review review = reviewService.createReview(reviewBody, imdbId);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable String id, @RequestBody Map<String, String> payload) {
        String imdbId = payload.get("imdbId");

        // Validate that imdbId is provided
        if (imdbId == null || imdbId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Film IMDB ID is required");
        }

        // Validate that the id is a valid ObjectId format
        ObjectId reviewId;
        try {
            reviewId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid review ID format");
        }

        // Attempt to delete the review
        boolean deletedReview = reviewService.deleteReview(reviewId, imdbId);

        if (deletedReview) {
            // 204 No Content is the standard response for successful DELETE
            return ResponseEntity.noContent().build();
        } else {
            // 404 Not Found if the review doesn't exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
        }
    }

}