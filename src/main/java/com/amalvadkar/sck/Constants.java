package com.amalvadkar.sck;

import java.util.List;

public class Constants {
    public static final List<Character> KNOWN_CHARACTERS_IN_NUMBERS = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.','-');
    public static final List<Character> PREDEFINED_SEPARATORS = List.of(',', '\n');
    public static final String ZERO = "0";
    public static final String COMMA = ",";
    public static final String NEW_LINE = "\n";
    public static final List<String> PREDEFINED_REGEX_KEYWORDS = List.of("|");
    public static final String CUSTOM_SEPARATOR_INDICATOR = "//";
    public static final String NUMBER_EXPECTED_BUT_EOF_FOUND_MSG = "Number expected but EOF found.";
    public static final String NEGATIVE_NOT_ALLOWED_MSG = "Negative not allowed : %s";
    public static final String MINUS_SYMBOL = "-";
    public static final String COMMA_WITH_SPACE_SUFFIX = ", ";
    public static final String NUMBER_EXPECTED_BUT_NON_NUMBER_FOUND_NSG = "Number expected but '%s' found at position %s.";
    public static final String UNKNOWN_CHARACTER_AT_POSITION_MSG = "'%s' expected but '%s' found at position %s.";
    public static final String EMPTY_STRING = "";
}
