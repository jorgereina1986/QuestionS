package com.jorgereina;


import java.util.*;

public class Main {

    public static void main(String[] args) {

//        //Sort by String
//        System.out.println(sortByStrings("weather", "theeraw"));
//
        //Decode String
        System.out.println(decodeString("4[ab]"));
        System.out.println(decodeString("2[b3[a]]"));
//
//        //Change possibilities
//        System.out.println(changePossibilities(4, new int[]{1, 2, 3}, 0));


    }

    /**
     * Question 1
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

        Arrays.sort(arr, new CustomComparator(t));

        StringBuilder sb = new StringBuilder();
        for (Character ch : arr) {
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * Question 2
     * decodeString(s): Given an encoded string, return its corresponding decoded string.
     * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is repeated exactly k times.
     * Note: k is guaranteed to be a positive integer.
     * For s = "4[ab]", the output should be decodeString(s) = "abababab"
     * For s = "2[b3[a]]", the output should be decodeString(s) = "baaabaaa"
     */

    private static String decodeString(String s) {

        //push number to stack
        //push letters to char stack
        // if its a close bracket pop char from char stack until open bracket is found
        //Pop top element from integer stack, (n)
        //make string repeating the popped chars N numbers of time
        //push all chars of the string in the stack

        Stack<Integer> integerStack = new Stack<>();
        Stack<Character> characterStack = new Stack<>();

        String result = "";
        String temp = "";

        // iterate through the string
        for (int i = 0; i < s.length(); i++) {
            int count = 0;

            // If number push it to integer stack
            if (Character.isDigit(s.charAt(i))) {
                while (Character.isDigit(s.charAt(i))) {
                    count = count * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                integerStack.push(count);
            }
            // If closing bracket ']', pop element until opening bracket is found in the character stack.
            else if (s.charAt(i) == ']') {
                temp = "";
                count = 0;
                //if is not open bracket keep popping
                if (!integerStack.isEmpty()) {
                    count = integerStack.peek();
                    integerStack.pop();
                }


                while (!characterStack.isEmpty() && characterStack.peek() != '[') {
                    temp = characterStack.peek() + temp;
                    characterStack.pop();
                }

                //pop element until open bracket
                if (!characterStack.empty() && characterStack.peek() == '[') {
                    characterStack.pop();
                }
                // Repeating the popped string 'temp' count
                // number of times.
                for (int j = 0; j < count; j++)
                    result = result + temp;

                // Push it in the character stack.
                for (int j = 0; j < result.length(); j++)
                    characterStack.push(result.charAt(j));

                result = "";
            }
            // If '[' opening bracket, push it into character stack.
            else if (s.charAt(i) == '[') {
                if (Character.isDigit(s.charAt(i - 1))) {
                    characterStack.push(s.charAt(i));
                } else {
                    characterStack.push(s.charAt(i));
                    integerStack.push(1);
                }
            } else {
                characterStack.push(s.charAt(i));
            }
        }

        // return results
        while (!characterStack.isEmpty()) {
            result = characterStack.peek() + result;
            characterStack.pop();
        }

        return result;
    }


    /**
     * Question 3
     * <p>
     * changePossibilities(amount,amount): Your quirky boss collects rare, old coins.
     * They found out you're a programmer and asked you to solve something they've been wondering for a long time.
     * Write a function that, given an amount of money and an array of coin denominations,
     * computes the number of ways to make the amount of money with coins of the available denominations.
     * Example: for amount=4 (4¢) and denominations=[1,2,3] (1¢, 2¢ and 3¢),
     * your program would output 4—the number of ways to make 4¢ with those denominations:
     * <p>
     * 1¢, 1¢, 1¢, 1¢
     * 1¢, 1¢, 2¢
     * 1¢, 3¢
     * 2¢, 2¢
     */
    private static int changePossibilities(int amount, int[] denominations, int current) {

        int combinations = 0;

        if (amount == 0) {
            return 1;
        }

        if (amount < 0) {
            return 0;
        }

        for (int i = current; i < denominations.length; i++) {
            combinations = combinations + changePossibilities(amount - denominations[i], denominations, i);
        }

        return combinations;
    }

    /**
     * Custom comparator that sorts a Character array according to the given String
     */
    public static class CustomComparator implements Comparator<Character> {

        String t;

        public CustomComparator(String t) {
            this.t = t;
        }

        @Override
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
    }
}
