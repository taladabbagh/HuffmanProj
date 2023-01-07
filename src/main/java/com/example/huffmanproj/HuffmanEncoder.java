package com.example.huffmanproj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class HuffmanEncoder {

    public static void encode(String inputFileName, String outputFileName) throws IOException {
        // Read the input file and count the frequency of each character
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        int ch;
        int[] frequencies = new int[256];
        while ((ch = reader.read()) != -1) {
            frequencies[ch]++;
        }
        reader.close();

        // Create the Huffman coding tree
        HuffmanNode root = HuffmanTree.createTree(frequencies);

        // Create a table of encodings for each character
        HashMap<Character, String> encodings = new HashMap<>();
        createEncodings(root, "", encodings);

        // Open the output file for writing
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));

        // Encode the input file and write the encoded data to the output file
        reader = new BufferedReader(new FileReader(inputFileName));
        while ((ch = reader.read()) != -1) {
            char c = (char) ch;
            String encoding = encodings.get(c);
            writer.write(encoding);
        }
        reader.close();
        writer.close();
    }

    // Recursively create the encodings for each character in the tree
    private static void createEncodings(HuffmanNode node, String encoding, HashMap<Character, String> encodings) {
        if (node.character != '\0') {
            encodings.put(node.character, encoding);
        } else {
            createEncodings(node.left, encoding + "0", encodings);
            createEncodings(node.right, encoding + "1", encodings);
        }
    }
    public static void decode(String inputFileName, String outputFileName, HuffmanNode root) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
        int c;
        HuffmanNode current = root;
        while ((c = reader.read()) != -1) {
            char ch = (char) c;
            if (ch == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            if (current.character != '\0') {
                writer.write(current.character);
                current = root;
            }
        }
        reader.close();
        writer.close();
    }
}