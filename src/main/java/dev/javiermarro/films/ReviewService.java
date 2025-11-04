package dev.javiermarro.films;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    //this is the reference to the interface needed for the insert of a review
    @Autowired
    private ReviewRepository reviewRepo;
    @Autowired
    private MongoTemplate mongoTemplate; // another way to talk to the DB this helps with more complex operations (dynamic queries)

    public Review createReview(String reviewBody, String imdbId){
        //creates a new review and links (inserts) it to the passed imdbId film
        Review review = reviewRepo.insert(new Review(reviewBody));

        // 1st use the template to perform an update to the Film class (in the DB) pushing a new reviewId into it
        // 2nd update the film with an id in the DB that matches the id received from the user (line 17)
        // 3rd apply the update creating a new update definition that does (or push) the value (which is the review with its body) in the DB
        mongoTemplate.update(Film.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewList").value(review))
                .first();

        return review;

    }
}
