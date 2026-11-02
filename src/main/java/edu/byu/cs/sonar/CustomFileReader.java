package edu.byu.cs.sonar;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * This is a class that will handle the file reading
 */
class CustomFileReader {
    /**
     * The scanner that will read the dictionary
     */
    private Scanner s;

    /**
     * The sentence that will be constructed
     */
    private String newSentence;
    /**
     * The number of words read in by the scanner
     */
    private int count;

    /**
     * Constructor for our class
     *
     * @param fileName the file to be read in
     */
    CustomFileReader(final String fileName) {
        newSentence = "";
        try {
            s = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException c) {
            newSentence = "This isn't going to work out";
        } finally {
            count = 0;
        }
    }

    /**
     * This will find how many words are in the text file provided
     *
     * @return how many words in the file
     */
    int howManyWordsInFile() {

        while (s.hasNext()) {
            s.next();
            count++;
        }
        return count;
    }

    /**
     * This will return the word at the given index in the text file
     *
     * @param index which number word should be taken back
     * @return correct word
     */
    String returnThatWord(final int index) {
        String returnWord = "";
        for (int i = 0; i < index; i++) {
            returnWord = s.next();
        }
        return returnWord;
    }

    /**
     * This will search for a word that contains the same letter as the
     * one provided in the argument and return that word
     *
     * @param letter eventually will be the character we are looking for in the word
     */
    void findNewWord(final CharSequence letter) {
        String word = s.next();

        while (!word.contains(letter)) {
            word = s.next();
        }

        newSentence = newSentence + word + " ";
    }

    /**
     * This is a standard getter
     *
     * @return the sentence created by the reader
     */
    String getNewSentence() {
        return newSentence;
    }

    /**
     * This is standard setter
     *
     * @param betterSentence The new sentence for the reader
     */
    void setNewSentence(final String betterSentence) {
        newSentence = betterSentence;
    }

    /**
     * This is a private getter since only this class will
     * make use of it
     *
     * @return the number of words in the dictionary
     */
    private int getCount() {
        return count;
    }

    /**
     * This is another private getter
     *
     * @return the number of words in the dictionary
     */
    private Scanner getScanner() {
        return s;
    }

    /**
     * Returns a hash number so this data type can be used for
     * bucketing and stored in a Hash implementation like a
     * HashMap, HashTable, HashSet, etc.
     *
     * @return hashcode for this specific instance of a reader
     */
    @Override
    public int hashCode() {
        return Integer.parseInt(newSentence) * count * 3;
    }

    /**
     * Returns information: of the sentence this reader holds,
     * as well as the number of words in the dictionary from
     * which this reader reads.
     *
     * @return String for this specific instance of a reader
     */
    @Override
    public String toString() {
        return newSentence + " " + count;
    }

    /**
     * Checks if the object being processed is an
     * instance of this exact class. That includes
     * type and member variables.
     *
     * @param object data type sent to be processed
     * @return true or false based on the processed object
     */
    @Override
    public boolean equals(final Object object) {
        if (object.getClass() != this.getClass()) {
            return false;
        }

        final CustomFileReader comparedReader = (CustomFileReader) object;

        if (!comparedReader.getNewSentence().equals(newSentence)) {
            return false;
        }

        if (comparedReader.getCount() != count) {
            return false;
        }

        return comparedReader.getScanner() == s;
    }
}
