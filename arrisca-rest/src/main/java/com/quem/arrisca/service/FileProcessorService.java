package com.quem.arrisca.service;

import com.quem.arrisca.model.ExcelFile;
import com.quem.arrisca.repository.FileRepository;
import org.apache.commons.io.FileUtils;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class FileProcessorService {
    @Autowired
    private JavaSparkContext javaSparkContext;

    @Autowired
    private SparkSession sparkSession;

    private FileRepository fileRepository;

    public FileProcessorService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public Map<String, Long> countWordsInAListOfStringWithSeparator(List<String> inputStrArray) throws IOException {
        JavaRDD<String> wordsRDD = javaSparkContext.parallelize(inputStrArray);
        List<String> ls = wordsRDD.collect();
        tempOperations(wordsRDD);
        return wordsRDD.countByValue();
    }

    public ExcelFile saveFile(ExcelFile data) {
        return fileRepository.save(data);
    }

    public void queryFile(String filePath) {
        filePath = "file:///D:/BigDataProjects/data/hateful-users-on-twitter/users_neighborhood_anon.csv";
        Dataset<Row> fileDataSet = sparkSession.read().option("inferSchema", "true").option(
                "header", "true").csv(filePath);
        fileDataSet.repartition(5).write().format("com.databricks.spark.csv")
                .option("header","true").mode(SaveMode.Overwrite)
                .save("file:///D:/BigDataProjects/arrisca/src/main/resources/SampleRddData");
        Object l = fileDataSet.groupBy().max("followers_count").collect();
       // System.out.println(l[0].values()[0].value);
    }

    private void tempOperations(JavaRDD wordsRDD) throws IOException {
        File f = new File("D:/BigDataProjects/arrisca/src/main/resources/sampleRddData");
        if (f.exists()) {
            FileUtils.deleteDirectory(f);
        }
        wordsRDD.saveAsTextFile("file:///D:/BigDataProjects/arrisca/src/main/resources/sampleRddData");
    }
}
