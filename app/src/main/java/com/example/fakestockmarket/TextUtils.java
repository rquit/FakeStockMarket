package com.example.fakestockmarket;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextUtils {
    public final static String LETTERS = "abcdefghijklmnopqrstuvwxyz";

    public static Set<String> levenshteinDistanceOnce(String s) {
        // Possible optimization in this function?
        Set<String> allDistance1Strings = new HashSet<>();
        allDistance1Strings.add(s);
        for(int k = 0; k < LETTERS.length(); k++) {
            for(int i = 0; i < s.length(); i++) {
                // Remove a character at that point
                String removedChar = s.substring(0, i) + s.substring(i+1);
                // Replace the character
                String replacedChar = s.substring(0, i) + LETTERS.charAt(k) + s.substring(i+1);
                // Add a character
                String addedChar = s.substring(0, i) + LETTERS.charAt(k) + s.substring(i);
                allDistance1Strings.addAll(Arrays.asList(removedChar, replacedChar, addedChar));
            }
        }
        // Get all the words but swapped two letters
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < s.length(); j++) {
                String swappedChar = swap(s, i, j);
                allDistance1Strings.add(swappedChar);
            }
        }
        return allDistance1Strings;
    }

    public static String swap(String str, int i, int j)
    {
        char ch[] = str.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return new String(ch);
    }
}
