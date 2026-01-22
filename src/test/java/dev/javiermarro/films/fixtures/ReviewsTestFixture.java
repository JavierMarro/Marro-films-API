package dev.javiermarro.films.fixtures;

import dev.javiermarro.films.models.Review;
import org.bson.types.ObjectId;

public class ReviewsTestFixture {

    // Helper method to be reused in ReviewTests files
    public static Review createReview(String body) {
        Review review = new Review();
        review.setId(new ObjectId());
        review.setBody(body);
        return review;
    }
}
