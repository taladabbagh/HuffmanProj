package com.example.huffmanproj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Driver extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("Scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        stage.setTitle("Huffman");
        stage.setScene(scene);
        stage.show();
    }
//    public static void main(String[] args) {
//        launch(args);
//    }
}