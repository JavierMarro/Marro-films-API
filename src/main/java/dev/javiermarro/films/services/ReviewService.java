package dev.javiermarro.films.services;

import dev.javiermarro.films.models.Film;
import dev.javiermarro.films.models.Review;
import dev.javiermarro.films.repositories.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    //this is the reference to the interface needed for the insert of a review
    @Autowired
    private ReviewRepository reviewRepo;
    @Autowired
    private MongoTemplate mongoTemplate; // another way to talk to the DB this helps with more complex operations (dynamic queries)
    @Autowired
    private FilmService filmService; // needed to fetch film data for getReviewsByFilm method

    public Review createReview(String reviewBody, String imdbId){
        //Creates and saves a new review in the reviews collection
        // then updates the film document that matches the given imdbId to include a reference to this review
        Review review = reviewRepo.insert(new Review(reviewBody));

        // 1st tell MongoDB which collection to update (Film.class)
        // 2nd Match the film document where the imdbId equals the one passed (line 17)
        // 3rd apply an update that pushes the new review reference into the reviewList array
        // 4th use .first() so that only the first matching film is updated
        mongoTemplate.update(Film.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewList").value(review))
                .first();

        return review;
    }

    //Deletes a review from both the reviews collection and removes its reference from the film's reviewList
    public boolean deleteReview(ObjectId reviewId, String imdbId) {
        // First, check if the review exists
        Optional<Review> reviewOptional = reviewRepo.findById(reviewId);

        if (reviewOptional.isEmpty()) {
            return false; // If review doesn't exist
        }

        // Delete the review from the reviews collection
        reviewRepo.deleteById(reviewId);

        // Remove the review reference from the film's reviewList
        // This uses pull operator to remove the review from the array
        mongoTemplate.update(Film.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().pull("reviewList", reviewOptional.get()))
                .first();

        return true;
    }

    //Retrieves all reviews for a specific film by querying the film's reviewList
    public List<Review> getReviewsByFilm(String imdbId) {
        // Fetch the film using the filmService
        Optional<Film> filmOptional = filmService.filmById(imdbId);

        // If film exists, return its reviewList; otherwise return an empty list
        return filmOptional.map(Film::getReviewList).orElse(List.of());
    }

}