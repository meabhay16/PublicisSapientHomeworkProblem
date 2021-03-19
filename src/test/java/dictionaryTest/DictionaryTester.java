package dictionaryTest;
import dictionarySevices.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class DictionaryTester {

    private Dictionary dictionary;
    private DictionaryService dictService;
    List<String> dictionaryList;

    @Before
    public void setUp() {
        dictionary = new Dictionary();
        dictService = mock(DictionaryService.class);
        dictionary.setDictionaryService(dictService);
        // I'm adding a mocked dictionary here
        when(dictService.getDictionary()).thenReturn(createDictionaryArray());
        dictionaryList = dictService.getDictionary();
    }


    @Test
    public void validateAbhayWord() {
        when(dictService.isEnglishWord("Abhay")).thenReturn(isThisEnglish("Abhay"));
        Assert.assertTrue(dictionary.isEnglishWord("Abhay"));
    }

    @Test
    public void validateNoWord() {
        when(dictService.isEnglishWord("NO")).thenReturn(isThisEnglish("NO"));
        Assert.assertTrue(dictionary.isEnglishWord("NO"));
        Assert.assertEquals(Arrays.asList("n", "no", "o", "on"),(dictionary.findPossibleWords("NO")));
    }

    @Test
    public void validateWorkingWord() {
        when(dictService.isEnglishWord("WORKING")).thenReturn(isThisEnglish("WORKING"));
        Assert.assertTrue(dictionary.isEnglishWord("WORKING"));
        // Print all possible words
        dictionary.findPossibleWords("WORKING");
    }
    
    /**
     * Create String list based on the Dictionary file (EnglishWords in this case) to mock the dictionary service
     * @return String list with the dictionary content
     */
	static List<String> createDictionaryArray() {
        List<String> listDictionary = new ArrayList<String>();
        BufferedReader reader;

        try {
            //ClassLoader loader = DictionaryTester.class.getClassLoader();///target/test-classes/EnglishWords
            File file = new File(System.getProperty("user.dir") + "/target/test-classes/EnglishWords");
            reader = new BufferedReader(new FileReader(file));
            //reader = new BufferedReader(new FileReader("EnglishWords"));
            String line = reader.readLine();
            while (line != null) {
                listDictionary.add(line);
                line = reader.readLine(); // read next line
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listDictionary;
    }
	
	 /**
     * Validate if given word exists in the dictionary (EnglishWords in this case) to mock the isEnglishWord function
     * @param word
     * @return boolean based if the word was found in the dictionary
     */
    public boolean isThisEnglish(String word) {
        for (String w : dictionaryList) {
            if (w.equals(word.toLowerCase())) {
                System.out.println(word + " is a valid english word");
                return true;
            }
        }
        System.out.println(word + " is *not* a valid english word");
        return false;
    }
}
