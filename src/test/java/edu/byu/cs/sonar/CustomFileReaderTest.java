package edu.byu.cs.sonar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileReaderTest {

    private CustomFileReader sut;

    @BeforeEach
    void setUp() {
        sut = new CustomFileReader("readMe1.txt");
    }

    @Test
    void testHowManyWordsInFile() throws FileNotFoundException {
        assertEquals(4, sut.howManyWordsInFile(), "There should be 4 words in readMe1.txt");
    }

    @Test
    void testReturnThatWord() throws FileNotFoundException {
        assertEquals("I", sut.returnThatWord(1), "The first word should be I in readMe1.txt");
    }

    @Test
    void testFindNewWord() throws FileNotFoundException {
        sut.findNewWord("C");
        assertEquals("Computer ", sut.getNewSentence(), "Computer should be found when C queried");
    }

    @Test
    void testGetNewSentence() {
        assertEquals("", sut.getNewSentence(), "New sentence should be empty initially");
    }

    @Test
    void setNewSentence() {
        String betterSentence = "New Sentence.";
        sut.setNewSentence(betterSentence);
        assertEquals(betterSentence, sut.getNewSentence());
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(sut, sut, "A reader should equal itself");
    }

    @Test
    void testEqualsSameValues() {
        assertEquals(new CustomFileReader("readMe1.txt"), sut, "Readers with the same values should be equal");
    }

    @Test
    void testNotEqualsNull() {
        assertNotEquals(null, sut, "A reader should not equal null");
    }

    @Test
    void testNotEqualsOtherClass() {
        assertNotEquals("readMe1.txt", sut, "A reader should not equal an object of another class");
    }

    @Test
    void testNotEqualsDifferentSentence() {
        CustomFileReader other = new CustomFileReader("readMe1.txt");
        other.setNewSentence("Different");
        assertNotEquals(other, sut, "Readers with different sentences should not be equal");
    }

    @Test
    void testNotEqualsDifferentCount() throws FileNotFoundException {
        CustomFileReader other = new CustomFileReader("readMe1.txt");
        other.howManyWordsInFile();
        assertNotEquals(other, sut, "Readers with different counts should not be equal");
    }

    @Test
    void testNotEqualsDifferentPath() {
        assertNotEquals(new CustomFileReader("readMe2.txt"), sut, "Readers with different files should not be equal");
    }

    @Test
    void testHashCodeMatchesForEqualReaders() {
        assertEquals(new CustomFileReader("readMe1.txt").hashCode(), sut.hashCode(),
                "Equal readers should have the same hash code");
    }

    @Test
    void testToString() throws FileNotFoundException {
        sut.howManyWordsInFile();
        sut.setNewSentence("Hello");
        assertEquals("Hello 4", sut.toString(), "toString should show the sentence and the word count");
    }

    @Test
    void testMissingFileThrows() {
        CustomFileReader missing = new CustomFileReader("doesNotExist.txt");
        assertThrows(FileNotFoundException.class, missing::howManyWordsInFile);
    }
}