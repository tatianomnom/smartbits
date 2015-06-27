package com.leveluptor.smartbits

import spock.lang.Specification

class TagExtractorTest extends Specification {

    def tagExtractor = new TagExtractor();

    def "should extract hashtags as lowercase"() {

        expect:
        tagExtractor.extractTags(sourceString) == foundHashtags

        where:
        sourceString         || foundHashtags
        '#wow much stuff'    || ['wow']
        '#wow #much\n#stuff' || ['wow', 'much', 'stuff']
        'wow much stuff'     || []
        'Wow much #Stuff'    || ['stuff']
        'wow#much#stuff'     || []
        'wow,#much.stuff?'   || ['much']

    }
}