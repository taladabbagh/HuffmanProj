package com.example.huffmanproj;

public class HuffmanNode {
    public char character;
    public int frequency;
    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode(char character, int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public int getFrequency() {
        return frequency;
    }


    public boolean isLeaf() {
        return left == null && right == null;
    }
}
