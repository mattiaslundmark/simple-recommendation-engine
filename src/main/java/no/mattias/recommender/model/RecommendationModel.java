package no.mattias.recommender.model;

public class RecommendationModel {
    
    int movieId;
    
    Double rating;
    
    public RecommendationModel() {
        
    };

    public RecommendationModel(int movieId, Double rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    
    
}
