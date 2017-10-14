package no.mattias.recommender.model;

public class MovieRating {
    private int userId;
    private int movieId;
    private Double rating;
    
    public MovieRating(String rdd) {
        String[] split = rdd.split(",");
        this.userId = Integer.parseInt(split[0]);
        this.movieId = Integer.parseInt(split[1]);
        this.rating = Double.parseDouble(split[2]);
        
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
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
}
