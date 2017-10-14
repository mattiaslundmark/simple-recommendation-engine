package no.mattias.recommender.service;

import javax.servlet.ServletContext;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.ml.recommendation.ALS;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.mattias.recommender.model.MovieRating;


@Service
public class SparkService {

    @Autowired
    SparkSession spark;
    @Autowired
    ServletContext context;
    
    public String train()  {
        
        JavaRDD<String> javaRDD = spark
                .read()
                .textFile("ratings.csv")
                .javaRDD();
        
        JavaRDD<MovieRating> movieRatings = removeHeader(javaRDD);
        
        Dataset<Row> trainingSet = createTrainingSet(movieRatings);
        
        ALSModel alsModel = performTraining(trainingSet);
        
        Dataset<Row> recommendForAllUsers = alsModel.recommendForAllUsers(5);
        context.setAttribute("model", recommendForAllUsers);
        
        return "Training complete";
    }

    /**
     * Create a training set.
     * TODO Should not use complete dataset for training
     * @param movieRatings
     * @return
     */
    
    private Dataset<Row> createTrainingSet(JavaRDD<MovieRating> movieRatings) {
        Dataset<Row> trainingSet = spark.createDataFrame(movieRatings, MovieRating.class);
        return trainingSet;
    }

    /**
     * Do the actual training. Default parameters so far.
     * @param trainingSet
     * @return
     */
    private ALSModel performTraining(Dataset<Row> trainingSet) {
        ALS als = new ALS()
                .setMaxIter(5)
                .setRegParam(0.01)
                .setUserCol("userId")
                .setItemCol("movieId")
                .setRatingCol("rating");
        
        ALSModel alsModel = als.fit(trainingSet);
        return alsModel;
    }

    /**
     * Remove the cvs header
     * @param javaRDD
     * @return
     */
    private JavaRDD<MovieRating> removeHeader(JavaRDD<String> javaRDD) {
        // Maybe a bit ineffective
        String first = javaRDD.first();
        JavaRDD<MovieRating> movieRatings = javaRDD
                .filter(row -> first.compareTo(row) != 0)
                .map(rdd -> new MovieRating(rdd));
        return movieRatings;
    }
}
