package com.example.huffmanproj;

import java.util.Arrays;

public class PriorityQueue {
    private HuffmanNode[] elements;
    private int size;

    public PriorityQueue(int maxSize) {
        elements = new HuffmanNode[maxSize];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(HuffmanNode node) {
        // Add the new element to the end of the array
        elements[size] = node;
        // Increment the size of the queue
        size++;
        // Reorder the elements based on their frequencies
        reorderElements();
    }

    public HuffmanNode remove() {
        // Remove the first element from the queue
        HuffmanNode removedElement = elements[0];
        // Shift all the other elements down one position
        for (int i = 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        // Decrement the size of the queue
        size--;
        // Reorder the elements based on their frequencies
        reorderElements();
        return removedElement;
    }

    private void reorderElements() {
        // Use the Java Arrays class to sort the array in ascending order based on the frequencies of the elements
        Arrays.sort(elements, 0, size, (a, b) -> a.getFrequency() - b.getFrequency());
    }
}
