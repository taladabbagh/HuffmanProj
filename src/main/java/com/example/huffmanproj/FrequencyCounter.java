package com.example.huffmanproj;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FrequencyCounter {

    public static HashMap<Character, Integer> countFrequency(String fileName) throws IOException {
        // Create a HashMap to store the frequency of each character
        HashMap<Character, Integer> frequency = new HashMap<>();

        // Use a BufferedReader to read the file line by line
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int ch;
        while ((ch = reader.read()) != -1) {
            // Increment the frequency of the current character in the HashMap
            char c = (char) ch;
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }
        reader.close();

        // Return the HashMap containing the frequency of each character
        return frequency;
    }
}

//    One way that a hashmap might be used
//    in Huffman coding is to store the mapping from each
//    character to its corresponding prefix code once the
//    Huffman tree has been constructed.This mapping can then
//    be used to encode the input string efficiently.
//
//        Another use for a hashmap in Huffman coding is to store the frequency
//        of each character in the input string,so that it can be used to construct
//        the initial set of nodes for the Huffman tree.This can be done in O(n)time,
//        where n is the number of characters in the input,by using a hashmap to count
//        the occurrences of each character.