/*
 * Jeremy Labate
 * 2/21/2020
 * WordSearchKata
 */
package wordsearchkata;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;


public class CrossWord {
    public String word(String inputWords) {
        String[] wordList = null; //Array of words
        String[][] allWords = null; //Crossword in Array
        String[] lineArray = null;
        String[] switcherList = null;
        String nextLine = null;
        String outPut = "";
        String inputW = "";
        String firstWord = "";
        int numLines = 0;
        boolean useCustomInput = false; //Default False, change to true if you want custom input for tests

        // This is the pulling of the text file and population of the wordList and actual crossword.
        String filePath = "/Users/labateje/wordsearchkata/src/main/java/wordsearchkata/WordList";

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String firstLine = lineReader.readLine();
            wordList = firstLine.split(","); // The list of words

            while (lineReader.readLine() != null) {
                numLines++;
            }

            allWords = new String[numLines][numLines];

            for (int i = 1; i <= numLines; i++) {
                nextLine = Files.readAllLines(Paths.get(filePath)).get(i);
                lineArray = nextLine.split(",");
                for (int j = 0; j < numLines; j++) {
                    allWords[i - 1][j] = lineArray[j]; // The actual crossword
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

        if(useCustomInput == false){
            switcherList = wordList;
        } else if(useCustomInput == true){
            String inputWordList[] = inputWords.split(",");
            switcherList = inputWordList;
        }

        firstWord = switcherList[0];
        outPut = crossWordSearch(allWords, numLines, firstWord);
        for(int i=1;i<switcherList.length;i++){
            inputW = switcherList[i];
            outPut += "\n" + crossWordSearch(allWords, numLines, inputW);
        }

        return outPut;
    }

    // This is the start of the actual search.
    public String crossWordSearch(String[][] allWords, int numLines, String inputWord){

        String outPutFinal = "";
        int r = 0; //For testing purposes only

        while(true){

            int[][] firstLoc = firstLetter(allWords, numLines, inputWord);
            int locY = firstLoc[r][0];
            int locX = firstLoc[r][1];
            String firstCoords = "("+locX+","+locY+")";
            String[] h = null;
            String[] v = null;
            String[] dd = null;
            String[] da = null;
            String[] outPut = null;

            h = searchHorizontally(allWords, inputWord, numLines, locX, locY);
            if(h == null){
                v = searchVertically(allWords, inputWord, numLines, locX, locY);
                if(v==null){
                    dd = searchDiagonallyDesc(allWords, inputWord, numLines, locX, locY);
                    if(dd==null){
                        da = searchDiagonallyAsce(allWords, inputWord, numLines, locX, locY);
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

    //This finds the first letter for each word
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

    //This searches for all other letters on the horizontal axis
    public String[] searchHorizontally(String[][] allWords, String inputWord, int numLines, int firstX, int firstY){
        String[] outPut = new String[inputWord.length()-1];
        while(true){
           if((firstX<(numLines-1))&&(allWords[firstY][firstX+1].equals(inputWord.substring(1, 1+1)))){
               //Right
               for(int i = 1; i < inputWord.length();i++){
                   if(((firstX+i)>(numLines-1))){
                       outPut = null;
                       break;
                   }
                   if(allWords[firstY][firstX+i].equals(inputWord.substring(i, i+1))){
                       outPut[i-1] = "("+(firstX+i)+","+(firstY)+")";
                   } else{
                       outPut = null;
                       break;
                   }
               }
           } else if((firstX>0)&&(allWords[firstY][firstX-1].equals(inputWord.substring(1, 1+1)))){
               //Left
               for(int i = 1; i < inputWord.length();i++){
                   if(((firstX-i)<0)){
                       outPut = null;
                       break;
                   }
                   if(allWords[firstY][firstX-i].equals(inputWord.substring(i, i+1))){
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

    //This searches for all other letters on the vertical axis
    public String[] searchVertically(String[][] allWords, String inputWord, int numLines, int firstX, int firstY){
        String[] outPut = new String[inputWord.length()-1];
        while(true){
            if((firstY<(numLines-1))&&(allWords[firstY+1][firstX].equals(inputWord.substring(1, 1+1)))){
                //Down
                for(int i = 1; i < inputWord.length();i++){
                    if(((firstY+i)>(numLines-1))){
                        outPut = null;
                        break;
                    }
                    if(allWords[firstY+i][firstX].equals(inputWord.substring(i, i+1))){
                        outPut[i-1] = "("+(firstX)+","+(firstY+i)+")";
                    } else{
                        outPut = null;
                        break;
                    }
                }
            } else if((firstY>0)&&(allWords[firstY-1][firstX].equals(inputWord.substring(1, 1+1)))){
                //Up
                for(int i = 1; i < inputWord.length();i++){
                    if(((firstY-i)<0)){
                        outPut = null;
                        break;
                    }
                    if(allWords[firstY-i][firstX].equals(inputWord.substring(i, i+1))){
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

    //This searches for all other letters on the diagonally descending axis
    public String[] searchDiagonallyDesc(String[][] allWords, String inputWord, int numLines, int firstX, int firstY){
        String[] outPut = new String[inputWord.length()-1];
        while(true){
            if((firstY<(numLines-1))&&(firstX<(numLines-1))&&(allWords[firstY+1][firstX+1].equals(inputWord.substring(1, 1+1)))){
                //DownRight
                for(int i = 1; i < inputWord.length();i++){
                    if(((firstY+i)>(numLines-1))){
                        outPut = null;
                        break;
                    }
                    if(((firstX+i)>(numLines-1))){
                        outPut = null;
                        break;
                    }
                    if(allWords[firstY+i][firstX+i].equals(inputWord.substring(i, i+1))){
                        outPut[i-1] = "("+(firstX+i)+","+(firstY+i)+")";
                    } else{
                        outPut = null;
                        break;
                    }
                }
            } else if((firstY>0)&&(firstX>0)&&(allWords[firstY-1][firstX-1].equals(inputWord.substring(1, 1+1)))){
                //UpLeft
                for(int i = 1; i < inputWord.length();i++){
                    if(((firstY-i)<0)){
                        outPut = null;
                        break;
                    }
                    if(((firstX-i)<0)){
                        outPut = null;
                        break;
                    }
                    if(allWords[firstY-i][firstX-i].equals(inputWord.substring(i, i+1))){
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

    //This searches for all other letters on the diagonally ascending axis
    public String[] searchDiagonallyAsce(String[][] allWords, String inputWord, int numLines, int firstX, int firstY){
        String[] outPut = new String[inputWord.length()-1];
        while(true){
            if((firstY<(numLines-1))&&(firstX>0)&&(allWords[firstY+1][firstX-1].equals(inputWord.substring(1, 1+1)))){
                //DownLeft
                for(int i = 1; i < inputWord.length();i++){
                    if(((firstX-i)<0)){
                        outPut = null;
                        break;
                    }
                    if(((firstY+i)>(numLines-1))){
                        outPut = null;
                        break;
                    }
                    if(allWords[firstY+i][firstX-i].equals(inputWord.substring(i, i+1))){
                        outPut[i-1] = "("+(firstX-i)+","+(firstY+i)+")";
                    } else{
                        outPut = null;
                        break;
                    }
                }
            } else if((firstY>0)&&(firstX<(numLines-1))&&(allWords[firstY-1][firstX+1].equals(inputWord.substring(1, 1+1)))){
                //UpRight
                for(int i = 1; i < inputWord.length();i++){
                    if(((firstY-i)<0)){
                        outPut = null;
                        break;
                    }
                    if(((firstX+i)>(numLines-1))){
                        outPut = null;
                        break;
                    }
                    if(allWords[firstY-i][firstX+i].equals(inputWord.substring(i, i+1))){
                        outPut[i-1] = "("+(firstX+i)+","+(firstY-i)+")";
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
