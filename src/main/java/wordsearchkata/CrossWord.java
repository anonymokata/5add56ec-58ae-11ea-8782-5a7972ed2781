/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package wordsearchkata;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;

public class CrossWord {
    public String word(String inputWord) {
        String[] wordList = null; //Array of words
        String[][] allWords = null; //Crossword in Array
        String[] lineArray = null;
        String nextLine = null;
        String outPut = "";
        int numLines = 0;



        String filePath = "/Users/labateje/wordsearchkata/src/main/java/wordsearchkata/WordList";

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String firstLine = lineReader.readLine();
            wordList = firstLine.split(",");

            while (lineReader.readLine() != null){
                numLines++;
            }

            allWords = new String[numLines][numLines];


            for(int i=1;i<=numLines;i++) {
                nextLine = Files.readAllLines(Paths.get("/Users/labateje/tddwordsearch/src/main/java/tddwordsearch/WordList")).get(i);
                lineArray = nextLine.split(",");
                for(int j=0;j<numLines;j++) {
                    allWords[i-1][j] = lineArray[j];
                }
            }




            lineReader.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }

        outPut = inputWord;
        return outPut;
    }

}
