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
        int numLines = 0;
        int locX = 0;
        int locY = 0;

        // This is the pulling of the text file and population of the wordList and actual crossword.
        String filePath = "/Users/labateje/wordsearchkata/src/main/java/wordsearchkata/WordList";

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String firstLine = lineReader.readLine();
            wordList = firstLine.split(","); // The list of words

            while (lineReader.readLine() != null){
                numLines++;
            }

            allWords = new String[numLines][numLines];

            for(int i=1;i<=numLines;i++) {
                nextLine = Files.readAllLines(Paths.get("/Users/labateje/tddwordsearch/src/main/java/tddwordsearch/WordList")).get(i);
                lineArray = nextLine.split(",");
                for(int j=0;j<numLines;j++) {
                    allWords[i-1][j] = lineArray[j]; // The actual crossword
                }
            }

            lineReader.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
        /* To test print crossword
        for (int l = 0; l < numLines; l++) {
            for (int r = 0; r < numLines; r++) {

                System.out.print(allWords[l][r] + " ");
                if(r==14){
                    System.out.println();
                }
            }
        }
        */
        String outPutFinal = "";
        int r = 0;



        boolean inputTest = true;
        while(inputTest == true){
            // This is the start of the actual search.

            int[][] firstLoc = firstLetter(allWords, numLines, inputWord);
            locX = firstLoc[r][0];
            locY = firstLoc[r][1];
            //System.out.printf("%s\n", inputWord.substring(0, 1));
            //System.out.printf("(%s,%s)\n", locX, locY);

            String firstCoords = "("+locX+","+locY+")";
            String[] otherLocs = searchHorizontally(allWords, inputWord, locX, locY);
            if(otherLocs != null){
                String outPutString = String.join(",", otherLocs);
                outPutString = inputWord+": "+firstCoords+","+outPutString;
                System.out.printf("%s\n", outPutString);
                outPutFinal = outPutString;
                inputTest = false;
                break;
            }
            //outPutString = inputWord+": "+firstCoords+","+outPutString;
            //System.out.printf("%s\n", outPutString);
            //outPutFinal = outPutString;

            r++;
        }
        return outPutFinal;
    }


    public int[][] firstLetter(String[][] allWords, int numLines, String inputWord){
        int[][] firstLoc = new int[numLines][numLines];
        int p =0;
        for (int l = 0; l < numLines; l++) {
            for (int r = 0; r < numLines; r++) {
                if (allWords[l][r].equals(inputWord.substring(0, 1))) {
                    //System.out.printf("(%s,%s)\n", l, r);
                    firstLoc[p][0] = l;
                    firstLoc[p][1] = r;
                    p++;
                }
            }
        }
        return firstLoc;
    }

    public String[] searchHorizontally(String[][] allWords, String inputWord, int firstX, int firstY){
        String[] outPut = new String[inputWord.length()-1];

        boolean inputTest = true;
        while(inputTest == true){
           if(inputWord.length()>(14-firstY)){
                outPut = null;
                inputTest = false;
                break;
            }
           if(allWords[firstX][firstY+1].equals(inputWord.substring(1, 1+1))){
               //Forwards
               for(int i = 1; i < inputWord.length();i++){
                   if(allWords[firstX][firstY+i].equals(inputWord.substring(i, i+1))){
                       //System.out.printf("(%s,%s)\n", firstX, firstY+i);
                       outPut[i-1] = "("+firstX+","+(firstY+i)+")";
                   }
               }
           } else if(allWords[firstX][firstY-1].equals(inputWord.substring(1, 1+1))){
               //Backwards
               for(int i = 1; i < inputWord.length();i++){
                   if(allWords[firstX][firstY-i].equals(inputWord.substring(i, i+1))){
                       //System.out.printf("(%s,%s)\n", firstX, firstY-i);
                       outPut[i-1] = "("+firstX+","+(firstY-i)+")";
                   }
               }
            } else {
               outPut = null;
           }
           break;

           /*
            //Forwards
            for(int i = 1; i < inputWord.length();i++){
                if(allWords[firstX][firstY+i].equals(inputWord.substring(i, i+1))){
                    //System.out.printf("(%s,%s)\n", firstX, firstY+i);
                    outPut[i-1] = "("+firstX+","+(firstY+i)+")";
                }
                else {
                    outPut = null;
                }
            }
            //Backwards
            for(int i = 1; i < inputWord.length();i++){
                if(allWords[firstX][firstY-i].equals(inputWord.substring(i, i+1))){
                    //System.out.printf("(%s,%s)\n", firstX, firstY-i);
                    outPut[i-1] = "("+firstX+","+(firstY-i)+")";
                }
                else {
                    outPut = null;
                }
            }
            break;
            //System.out.printf("%s", outPut[1]);
            */
        }

        return outPut;
    }




}
