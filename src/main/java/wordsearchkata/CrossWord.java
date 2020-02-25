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
        int r = 0; //For testing purposes only

        while(true){
            // This is the start of the actual search.

            int[][] firstLoc = firstLetter(allWords, numLines, inputWord);
            locX = firstLoc[r][0];
            locY = firstLoc[r][1];
            String firstCoords = "("+locX+","+locY+")";
            String[] h = null;
            String[] v = null;
            String[] dd = null;
            String[] da = null;
            String[] outPut = null;

            h = searchHorizontally(allWords, inputWord, locX, locY);
            if(h == null){
                v = searchVertically(allWords, inputWord, locX, locY);
                if(v==null){
                    dd = searchDiagonallyDesc(allWords, inputWord, locX, locY);
                    if(dd==null){
                        da = searchDiagonallyAsce(allWords, inputWord, locX, locY);
                        if(da==null){
                            r++; //For testing purposes only
                            continue;
                        } else {
                            outPut = da;
                        }
                    } else {
                        outPut = dd;
                    }
                } else {
                    outPut = v;
                }
            } else {
                outPut = h;
            }

            String outPutString = String.join(",", outPut);
            outPutString = inputWord+": "+firstCoords+","+outPutString;
            System.out.printf("%s\n", outPutString);
            outPutFinal = outPutString;
            break;
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
        while(true){
           if((firstY<14)&&(allWords[firstX][firstY+1].equals(inputWord.substring(1, 1+1)))){
               //Right
               if(((inputWord.length()-1)+firstY)>14){
                   outPut = null;
                   break;
               }
               for(int i = 1; i < inputWord.length();i++){
                   if(allWords[firstX][firstY+i].equals(inputWord.substring(i, i+1))){
                       outPut[i-1] = "("+(firstX)+","+(firstY+i)+")";
                   } else{
                       outPut = null;
                       break;
                   }
               }
           } else if((firstY>0)&&(allWords[firstX][firstY-1].equals(inputWord.substring(1, 1+1)))){
               //Left
               for(int i = 1; i < inputWord.length();i++){
                   if(allWords[firstX][firstY-i].equals(inputWord.substring(i, i+1))){
                       outPut[i-1] = "("+(firstX)+","+(firstY-i)+")";
                   } else{
                       outPut = null;
                       break;
                   }
               }
            } else {
               outPut = null;
           }
           break;
        }
        return outPut;
    }

    public String[] searchVertically(String[][] allWords, String inputWord, int firstX, int firstY){
        String[] outPut = new String[inputWord.length()-1];
        while(true){
            if((firstX<14)&&(allWords[firstX+1][firstY].equals(inputWord.substring(1, 1+1)))){
                //Down
                if(((inputWord.length()-1)+firstX)>14){
                    outPut = null;
                    break;
                }
                for(int i = 1; i < inputWord.length();i++){
                    if(allWords[firstX+i][firstY].equals(inputWord.substring(i, i+1))){
                        outPut[i-1] = "("+(firstX+i)+","+(firstY)+")";
                    } else{
                        outPut = null;
                        break;
                    }
                }
            } else if((firstX>0)&&(allWords[firstX-1][firstY].equals(inputWord.substring(1, 1+1)))){
                //Up
                if(((firstX-(inputWord.length()-1))<0)){
                    outPut = null;
                    break;
                }
                for(int i = 1; i < inputWord.length();i++){
                    if(allWords[firstX-i][firstY].equals(inputWord.substring(i, i+1))){
                        outPut[i-1] = "("+(firstX-i)+","+(firstY)+")";
                    } else{
                        outPut = null;
                        break;
                    }
                }
            } else {
                outPut = null;
            }
            break;
        }
        return outPut;
    }

    public String[] searchDiagonallyDesc(String[][] allWords, String inputWord, int firstX, int firstY){
        String[] outPut = new String[inputWord.length()-1];
        while(true){
            if((firstX<14)&&(firstY<14)&&(allWords[firstX+1][firstY+1].equals(inputWord.substring(1, 1+1)))){
                //DownRight
                if((((inputWord.length()-1)+firstY)>14)||(((inputWord.length()-1)+firstX)>14)){
                    outPut = null;
                    break;
                }
                for(int i = 1; i < inputWord.length();i++){
                    if(allWords[firstX+i][firstY+i].equals(inputWord.substring(i, i+1))){
                        outPut[i-1] = "("+(firstX+i)+","+(firstY+i)+")";
                    } else{
                        outPut = null;
                        break;
                    }
                }
            } else if((firstX>0)&&(firstY>0)&&(allWords[firstX-1][firstY-1].equals(inputWord.substring(1, 1+1)))){
                //UpLeft
                if(((firstY-(inputWord.length()-1))<0)||((firstX-(inputWord.length()-1))<0)){
                    outPut = null;
                    break;
                }
                for(int i = 1; i < inputWord.length();i++){
                    if(allWords[firstX-i][firstY-i].equals(inputWord.substring(i, i+1))){
                        outPut[i-1] = "("+(firstX-i)+","+(firstY-i)+")";
                    } else{
                        outPut = null;
                        break;
                    }
                }
            } else {
                outPut = null;
            }
            break;
        }
        return outPut;
    }

    public String[] searchDiagonallyAsce(String[][] allWords, String inputWord, int firstX, int firstY){
        String[] outPut = new String[inputWord.length()-1];
        while(true){
            if((firstX<14)&&(firstY>0)&&(allWords[firstX+1][firstY-1].equals(inputWord.substring(1, 1+1)))){
                //DownLeft
                for(int i = 1; i < inputWord.length();i++){
                    if(allWords[firstX+i][firstY-i].equals(inputWord.substring(i, i+1))){
                        outPut[i-1] = "("+(firstX+i)+","+(firstY-i)+")";
                    } else{
                        outPut = null;
                        break;
                    }
                }
            } else if((firstX>0)&&(firstY<14)&&(allWords[firstX-1][firstY+1].equals(inputWord.substring(1, 1+1)))){
                for(int i = 1; i < inputWord.length();i++){
                    if(allWords[firstX-i][firstY+i].equals(inputWord.substring(i, i+1))){
                        outPut[i-1] = "("+(firstX-i)+","+(firstY+i)+")";
                    } else{
                        outPut = null;
                        break;
                    }
                }
            } else {
                outPut = null;
            }
            break;
        }
        return outPut;
    }
}
