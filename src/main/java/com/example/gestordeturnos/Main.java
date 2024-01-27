package com.example.gestordeturnos;

import com.example.gestordeturnos.api.service.JpaQuerySession;
import com.example.gestordeturnos.graphic.LoadViews;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        JpaQuerySession.getFactory().isOpen();
        LoadViews.load("main-menu.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}