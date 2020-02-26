# wordsearchkata
/*
 * Jeremy Labate
 * 2/21/2020
 * WordSearchKata
 */

ABOUT:
 My program is using Java and JUnit4 for testing, I created it on IntelliJ IDEA with the textInput portion on Eclipse for readability.

 After inputting a textfile, it will start a search to locate the words and their locations. It starts with finding the first letter of the word. Once the first letter is found, it tries horizontal searching, then moves on the vertical, diagonal descending, and finally diagonal ascending. Each directional search will look on both side of the axis. For example, horizontal will first look to the right, then to the left. 

 The program will only work for crosswords identical in structure to the template one. This program is expandable for different sizes of crosswords, given that the crossword is a square.

HOW TO RUN:

 Download folder from GitHub: https://github.com/Labateje/wordsearchkata.git
 Open project with program of preference, for my case I used IntelliJ IDEA.
 Set filePath to your WordList's textfile location.
 Example: "/Users/labateje/wordsearchkata/src/main/java/wordsearchkata/WordList"

 The useCustomInput variable when left on false will only output the words found in the first line. For testing custom words or different quantity of words, set useCustomInput to true.

 On test cases, you must present the words with the correct locations for the left side of the assertEquals, and put the list of words you want to check on the right side. For above mentioned useCustomInput, if set to true the program will ignore the right side input, and just check the left locations with the first line words of the textfile.

 Press run on CrossWordTest.shouldReturnAllWords.
 
