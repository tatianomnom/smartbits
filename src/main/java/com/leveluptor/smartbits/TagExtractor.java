package com.leveluptor.smartbits;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagExtractor {

    private static final Pattern PATTERN = Pattern.compile("(^|[^\\w])#(\\w+)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

    public List<String> extractTags(String source) {
        List<String> result = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(source);
        while (matcher.find()) {
            result.add(matcher.group(2).toLowerCase());
        }
        return result;
    }
}
