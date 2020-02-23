/*
 * This Java source file was generated by the Gradle 'init' task.
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

    @Test public void shouldReturnJO() {

        assertEquals("JO: (0,10),(0,11)", crossWord.word("JO"));
    }

    @Test public void shouldReturnJOC() {

        assertEquals("JOC: (0,10),(0,11),(0,12)", crossWord.word("JOC"));
    }

    @Test public void shouldReturnJOCWE() {

        assertEquals("JOCWE: (0,10),(0,11),(0,12),(0,13),(0,14)", crossWord.word("JOCWE"));
    }

    @Test public void shouldReturnJVN() {

        assertEquals("JVN: (0,10),(0,9),(0,8)", crossWord.word("JVN"));
    }

    @Test public void shouldReturnKIN() {

        assertEquals("KIN: (0,6),(0,7),(0,8)", crossWord.word("KIN"));
    }

    @Test public void shouldReturnBRJS() {

        assertEquals("BRJS: (3,0),(3,1),(3,2),(3,3)", crossWord.word("BRJS"));
    }

    @Test public void shouldReturnZZ() {

        assertEquals("ZZ: (1,5),(1,6)", crossWord.word("ZZ"));
    }

    @Test public void shouldReturnXT() {

        assertEquals("XT: (2,12),(2,13)", crossWord.word("XT"));
    }

    @Test public void shouldReturnETIK() {

        assertEquals("ETIK: (3,7),(3,8),(3,9),(3,10)", crossWord.word("ETIK"));
    }

    @Test public void shouldReturnLLSH() {

        assertEquals("LLSH: (1,0),(1,1),(1,2),(1,3)", crossWord.word("LLSH"));
    }

    @Test public void shouldReturnCOTTYK() {

        assertEquals("COTTYK: (5,1),(5,2),(5,3),(5,4),(5,5),(5,6)", crossWord.word("COTTYK"));
    }

    @Test public void shouldReturnSCOTTY() {

        assertEquals("SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)", crossWord.word("SCOTTY"));
    }
}

