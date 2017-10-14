package no.mattias.recommender.model;

import java.util.List;

public class UserRecommendationModel {
    
    int userId;
    
    List<RecommendationModel> recommendations;
    
    

    public UserRecommendationModel() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<RecommendationModel> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<RecommendationModel> recommendations) {
        this.recommendations = recommendations;
    }

    
    
}
