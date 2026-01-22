package dev.javiermarro.films.fixtures;

import dev.javiermarro.films.models.Film;
import dev.javiermarro.films.models.Review;
import org.bson.types.ObjectId;

import java.util.Map;

public class ReviewsTestFixture {

    public static Review positive() {
        return createReview("This is my favourite film!");
    }

    public static Review neutral() {
        return createReview("It is not that bad tbh!");
    }

    public static Review negative() {
        return createReview("Save your time please!");
    }

    // Helper method to be reused in ReviewTests files
    public static Review createReview(String body) {
        Review review = new Review();
        review.setId(new ObjectId());
        review.setBody(body);
        return review;
    }
}
