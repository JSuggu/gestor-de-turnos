package com.example.gestordeturnos.graphic;

import com.example.gestordeturnos.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class LoadViews {
    public static void load(String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load(), 1366, 720);
        Stage stage = new Stage();
        stage.setTitle("Gestor de Turnos");
        stage.setScene(scene);
        stage.show();
    }

    public static void load(String fxmlFileName, Integer width, Integer height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        Stage stage = new Stage();
        stage.setTitle("Gestor de Turnos");
        stage.setScene(scene);
        stage.show();
    }
}
