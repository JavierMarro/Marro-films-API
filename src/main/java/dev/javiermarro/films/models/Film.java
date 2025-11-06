package dev.javiermarro.films.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "films") // It presents each document in the films collection (from MongoDB)
@Data // takes care of getters, setters and toString methods
@AllArgsConstructor // creates a constructor that takes all private fields as argument
@NoArgsConstructor // creates a constructor that takes no parameters
public class Film {
    // all keys from the collection in the MongoDB to be returned here as field properties for this class
    @Id // lets the framework know that this property should be treated as a unique identifier for each film inside the DB
    private ObjectId id;
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private List<String> backdrops;
    @DocumentReference // This makes the DB to store only the ids of the review while the reviews are in a separate collection (called manual reference relationships)
    private List<Review> reviewList;
}
