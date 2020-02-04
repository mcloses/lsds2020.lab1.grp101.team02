package upf.edu;

import upf.edu.parser.SimplifiedTweet;

//import upf.edu.filter.FileLanguageFilter;
//import upf.edu.filter.FilterException;
//import upf.edu.uploader.S3Uploader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Optional;

public class TwitterFilter {
    public static void main( String[] args ) throws IOException {
        /*List<String> argsList = Arrays.asList(args);
        String language = argsList.get(0);
        String outputFile = argsList.get(1);
        String bucket = argsList.get(2);*/

        BufferedReader bf = new BufferedReader(new FileReader("/home/u136275/Desktop/LSDS2020-master/lab1/src/main/java/upf/edu/test.txt"));
                
        String test = bf.readLine();
	Optional<SimplifiedTweet> testTweet = SimplifiedTweet.fromJson(test);
	
	
        //System.out.println("Language: " + language + ". Output file: " + outputFile + ". Destination bucket: " + bucket);

	/*
        for(String inputFile: argsList.subList(3, argsList.size())) {
            System.out.println("Processing: " + inputFile);
            final FileLanguageFilter filter = new FileLanguageFilter(inputFile, outputFile);
            filter.filterLanguage(language);
        }
	*/

        //final S3Uploader uploader = new S3Uploader(bucket, "prefix", "upf");
        //uploader.upload(Arrays.asList(outputFile));
	
    }
}
