package com.example.huffmanproj;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SceneController {
    @FXML
    TextField inputFileTF = new TextField();

    @FXML
    TextField outputFileTF = new TextField();

    @FXML
    Button inputBtn = new Button();

    @FXML
    Button outputBtn = new Button();

    @FXML
    Button encodeBtn = new Button();

    @FXML
    Button decodeBtn = new Button();

    Stage primaryStage;

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }

    public void browseForInputFile() {
        // Show the file chooser and update the input file field with the selected file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Input File");
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            inputFileTF.setText(file.getPath());
        }
    }

    public void browseForOutputFile() {
        // Show the file chooser and update the output file field with the selected file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Output File");
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            outputFileTF.setText(file.getPath());
        }
    }

    public void encode() {
        // Get the input and output files from the text fields
        File inputFile = new File(inputFileTF.getText());
        File outputFile = new File(outputFileTF.getText());

        // Encode the input file and write the encoded data to the output file
        try {
            HashMap<Character, Integer> frequency = FrequencyCounter.countFrequency(String.valueOf(inputFile));
HuffmanEncoder.encode(inputFile.getPath(), outputFile.getPath(),frequency);
       //  HuffmanNode root = HuffmanEncoder.readHuffmanTree()
        } catch (IOException e) {
            System.out.println("An I/O error occurred: " + e.getMessage());
        }
    }

    public void decode() {
        // Get the input and output files from the text fields
        File inputFile = new File(inputFileTF.getText());
        File outputFile = new File(outputFileTF.getText());

        // Read the encoded data from the input file and decode it
        try {
            HuffmanNode root = HuffmanEncoder.readHuffmanTree(String.valueOf(outputFile));

            HuffmanEncoder.decode(inputFile.getPath(),root ,outputFile.getPath());
        } catch (IOException e) {
            System.out.println("An I/O error occurred: " + e.getMessage());
        }
    }


}
