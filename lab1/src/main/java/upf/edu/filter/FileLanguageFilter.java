/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upf.edu.filter;

import upf.edu.filter.LanguageFilter;
import upf.edu.parser.SimplifiedTweet;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Optional;
import java.io.IOException;
import upf.edu.parser.SimplifiedTweet;


/**
 *
 * @author u136275
 */
public class FileLanguageFilter implements LanguageFilter {
    
        String inputFileName;
        String outputFileName;
        
        public FileLanguageFilter(String input, String output)
        {
            this.inputFileName = input;
            this.outputFileName = output;
        }
        
        @Override
        public void filterLanguage(String language)
        {
            try(PrintWriter outputFile = new PrintWriter(this.outputFileName, "UTF-8"))
            {
                try (BufferedReader bf = new BufferedReader(new FileReader(this.inputFileName));)
                {
                    String line;
                    while ((line = bf.readLine()) != null)
                    {
                        Optional<SimplifiedTweet> tweet = SimplifiedTweet.fromJson(line);

                        if (tweet.isPresent()) {
                            if (tweet.get().getLanguage().equals(language))
                            {
                                    outputFile.println(line);
                                    outputFile.println("");
                            }
                        }  
                        bf.readLine();
                    }
                }
                catch(IOException e)
                {
                    System.out.println("Error opening the input file");
                    e.printStackTrace();
                }
                outputFile.close();
            }
            catch(IOException e)
            {
                System.out.println("Error creating the output file");
                e.printStackTrace();
            }
            
            
        }
    
}
