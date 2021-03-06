/*
 * Jeremy Labate
 * 2/21/2020
 * WordSearchKata
 */
package wordsearchkata;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CrossWordTest {


    CrossWord crossWord;

    @Before
    public void setUp() {
        crossWord = new CrossWord();
    }


    @Test public void shouldReturnAllWords() { //Only works with WordList

        assertEquals("BONES: (0,6),(0,7),(0,8),(0,9),(0,10)\n" +
                "KHAN: (5,9),(5,8),(5,7),(5,6)\n" +
                "KIRK: (4,7),(3,7),(2,7),(1,7)\n" +
                "SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)\n" +
                "SPOCK: (2,1),(3,2),(4,3),(5,4),(6,5)\n" +
                "SULU: (3,3),(2,2),(1,1),(0,0)\n" +
                "UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)", crossWord.word("BONES,KHAN,KIRK,SCOTTY,SPOCK,SULU,UHURA"));
    }

    @Test public void shouldReturnAllWordsPuzzleList() { //Only works with PuzzleList

        assertEquals("BABY: (8,3),(7,2),(6,1),(5,0)\n" +
                "DOOR: (13,3),(13,2),(13,1),(13,0)\n" +
                "EAR: (2,14),(3,13),(4,12)\n" +
                "FLOWER: (0,9),(0,10),(0,11),(0,12),(0,13),(0,14)\n" +
                "GARAGE: (5,3),(4,4),(3,5),(2,6),(1,7),(0,8)\n" +
                "HOUSE: (10,7),(9,7),(8,7),(7,7),(6,7)", crossWord.word("BABY,DOOR,EAR,FLOWER,GARAGE,HOUSE"));
    }

    @Test public void shouldReturnAllWordsThirdList() { //Only works with ThirdList

        assertEquals("BLANKET: (7,6),(6,6),(5,6),(4,6),(3,6),(2,6),(1,6)\n" +
                "DOCTOR: (5,5),(4,4),(3,3),(2,2),(1,1),(0,0)\n" +
                "FISH: (7,3),(8,3),(9,3),(10,3)\n" +
                "GLASS: (6,7),(6,6),(6,5),(6,4),(6,3)\n" +
                "ROMEO: (1,11),(2,10),(3,9),(4,8),(5,7)", crossWord.word("BLANKET,DOCTOR,FISH,GLASS,ROMEO"));
    }

    //Following tests only work when useCustomInput is set to true on WordList
    @Test public void shouldReturnSomeWords() {

        assertEquals("BONES: (0,6),(0,7),(0,8),(0,9),(0,10)\n" +
                "KHAN: (5,9),(5,8),(5,7),(5,6)\n" +
                "SULU: (3,3),(2,2),(1,1),(0,0)\n" +
                "UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)", crossWord.word("BONES,KHAN,SULU,UHURA"));
    }

    //SingleWordTests
    @Test public void shouldReturnBONES() {

        assertEquals("BONES: (0,6),(0,7),(0,8),(0,9),(0,10)", crossWord.word("BONES"));
    }

    @Test public void shouldReturnKHAN() {

        assertEquals("KHAN: (5,9),(5,8),(5,7),(5,6)", crossWord.word("KHAN"));
    }

    @Test public void shouldReturnKIRK() {

        assertEquals("KIRK: (4,7),(3,7),(2,7),(1,7)", crossWord.word("KIRK"));
    }

    @Test public void shouldReturnSCOTTY() {

        assertEquals("SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)", crossWord.word("SCOTTY"));
    }

    @Test public void shouldReturnSPOCK() {

        assertEquals("SPOCK: (2,1),(3,2),(4,3),(5,4),(6,5)", crossWord.word("SPOCK"));
    }

    @Test public void shouldReturnSULU() {

        assertEquals("SULU: (3,3),(2,2),(1,1),(0,0)", crossWord.word("SULU"));
    }

    @Test public void shouldReturnUHURA() {

        assertEquals("UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)", crossWord.word("UHURA"));
    }
}



