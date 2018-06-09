package com.jorgereina;


import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println(sortByStrings("weather", "theeraw"));

    }

    /**
     * Question 1 --
     * sortByStrings(s,t): Sort the letters in the string s by the order they occur in the string t.
     * You can assume t will not have repetitive characters. For s = "weather" and t = "therapyw",
     * the output should be sortByString(s, t) = "theeraw". For s = "good" and t = "odg",
     * the output should be sortByString(s, t) = "oodg".
     */

    private static String sortByStrings(String s, String t) {

        Character[] arr = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }

        Arrays.sort(arr, new Comparator<Character>() {
            public int compare(Character a, Character b) {
                if (t.contains("" + a) && !t.contains("" + b)) {
                    return -1;
                } else if (!t.contains("" + a) && t.contains("" + b)) {
                    return 1;
                } else if (a == b) {
                    return 0;
                } else if (t.contains("" + a) && t.contains("" + b)) {
                    if (t.indexOf("" + a) > t.indexOf("" + b)) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                return 0;
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            sb.append(ch);
        }
        return sb.toString();
    }
}
