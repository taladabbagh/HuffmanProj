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