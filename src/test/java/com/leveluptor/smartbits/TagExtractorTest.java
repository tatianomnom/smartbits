package com.leveluptor.smartbits;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TagExtractorTest {

    private static final TagExtractor tagExtractor = new TagExtractor();

    @Test
    public void testExtractTags() throws Exception {
        assertEquals(Arrays.asList("wow"), tagExtractor.extractTags("#wow much stuff"));
        assertEquals(Arrays.asList("wow", "much", "stuff"), tagExtractor.extractTags("#wow #much #stuff"));
        assertEquals(Arrays.asList(), tagExtractor.extractTags("wow much stuff"));
        assertEquals(Arrays.asList("stuff"), tagExtractor.extractTags("Wow much #Stuff"));
        assertEquals(Arrays.asList(), tagExtractor.extractTags("wow#much#stuff"));
        assertEquals(Arrays.asList("much"), tagExtractor.extractTags("wow,#much.stuff?"));
//        assertEquals(Arrays.asList("wow#much#stuff"), tagExtractor.extractTags("wow #Wow#mUch#stuFF")); //todo think
    }
}