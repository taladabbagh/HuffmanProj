package com.example.huffmanproj;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {



    public HuffmanTree(HashMap<Character, Integer> frequency) {

    }

    // Create a Huffman coding tree from the given frequencies
    public static HuffmanNode createTree(int[] frequencies) {
        // Create a PriorityQueue to hold the nodes of the tree
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);

        // Add a node for each character to the queue
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                queue.offer(new HuffmanNode((char) i, frequencies[i]));
            }
        }

        // Keep combining the nodes with the lowest frequencies until there is only one node left
        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            queue.offer(parent);
        }

        // Return the root node of the Huffman coding tree
        return queue.poll();
    }

        public void getPrefixCodes(HuffmanNode node, Map<Character, String> prefixCodes, String code) {
            if (node.left == null && node.right == null) {
                // This is a leaf node, so it represents a character
                prefixCodes.put(node.character, code);
            } else {
                // This is an internal node, so recursively traverse the tree
                getPrefixCodes(node.left, prefixCodes, code + "0");
                getPrefixCodes(node.right, prefixCodes, code + "1");
            }
        }
    }


