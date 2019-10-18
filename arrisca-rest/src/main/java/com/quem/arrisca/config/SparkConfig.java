package com.quem.arrisca.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Bean
    public SparkConf getSparkConfig() {
        return new SparkConf().setAppName("Arrisca").setMaster("local[*]");
    }

    @Bean
    public JavaSparkContext getSparkContext() {
        return new JavaSparkContext(getSparkConfig());
    }

    @Bean
    public SparkSession getSparkSession() {
        return SparkSession.builder().config(getSparkConfig()).getOrCreate();
    }
}
