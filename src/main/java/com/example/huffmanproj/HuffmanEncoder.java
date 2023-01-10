package com.example.huffmanproj;

import java.io.*;
import java.util.*;

public class HuffmanEncoder {

    public static void encode(String inputFileName, String outputFileName, HashMap<Character, Integer> frequency) throws IOException {
        // Create an array of character frequencies
        int[] frequencies = new int[256];
        for (char c : frequency.keySet()) {
            frequencies[c] = frequency.get(c);
        }

        // Construct a Huffman tree based on the character frequencies
        HuffmanTree tree = new HuffmanTree(frequency);
        HuffmanNode root = tree.createTree(frequencies);

        // Use the Huffman tree to create a map of character -> prefix code mappings
        Map<Character, String> prefixCodes = new HashMap<>();
        tree.getPrefixCodes(root, prefixCodes, "");

        // Use a BufferedReader to read the input file character by character
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        int ch;

        // Use a BufferedWriter to write the output file
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
        while ((ch = reader.read()) != -1) {
            // Look up the prefix code for the current character
            char c = (char) ch;
            String code = prefixCodes.get(c);

            // Write the prefix code to the output file
            writer.write(code);
        }
        reader.close();
        writer.close();

        // Write the Huffman tree to the output file
        writeHuffmanTree(root, outputFileName);
    }

    // Method to write the Huffman tree to a file
    private static void writeHuffmanTree(HuffmanNode node, String fileName) throws IOException {
        // Use a BufferedWriter to write the tree to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

        if (node.isLeaf()) {
            // This node is a leaf, so write a "L" followed by the character and its frequency
            writer.write("L " + node.character + " " + node.frequency);
            writer.newLine();
        } else {
            // This node is an internal node, so write an "I" followed by the frequency
            writer.write("I " + node.frequency);
            writer.newLine();

            // Write the left and right subtrees
            writeHuffmanTree(node.left, fileName);
            writeHuffmanTree(node.right, fileName);
        }
        writer.close();
    }


    public static void decode(String inputFileName, HuffmanNode root, String outputFileName) throws IOException {
        // Use a BufferedReader to read the encoded file
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        int ch;

        // Use a BufferedWriter to write the output file
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));

        // Traverse the Huffman tree to decode the encoded file
        HuffmanNode node = root;
        while ((ch = reader.read()) != -1) {
            char c = (char) ch;
            if (c == '0') {
                // Move to the left child
                node = node.left;
            } else if (c == '1') {
                // Move to the right child
                node = node.right;
            }
            if (node.left == null && node.right == null) {
                // This is a leaf node, so it represents a character
                writer.write(node.character);
                node = root;
            }
        }
    }

    // Method to read the Huffman tree from a file
    public static HuffmanNode readHuffmanTree(String fileName) throws IOException {
        // Use a BufferedReader to read the tree from the file
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // Read the first character of the line
        int ch = reader.read();
        if (ch == 'L') {
            // This line represents a leaf node, so read the character and frequency
            char c = (char) reader.read();
            int frequency = reader.read();
            return new HuffmanNode(c, frequency);
        } else {
            // This line represents an internal node, so read the frequency
            int frequency = reader.read();
            HuffmanNode node = new HuffmanNode('\0', frequency);

            // Read the left and right subtrees
            node.left = readHuffmanTree(fileName);
            node.right = readHuffmanTree(fileName);
            return node;
        }
    }
}
