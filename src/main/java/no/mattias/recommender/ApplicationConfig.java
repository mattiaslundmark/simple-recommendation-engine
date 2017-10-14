package no.mattias.recommender;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public SparkConf sparkConf() {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[4]");
        sparkConf.setAppName("Recommender");
        return sparkConf;
    }
    
    @Bean
    public SparkContext sparkContext() {
        SparkContext sc = new SparkContext(sparkConf());
        return sc;
    }
    
    @Bean
    public SparkSession sparkSession() {
        return SparkSession
                .builder()
                .sparkContext(sparkContext())
                .appName("Recommender")
                .getOrCreate();
    }
}
