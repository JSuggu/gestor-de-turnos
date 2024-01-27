package com.example.gestordeturnos.graphic.controller;

import com.example.gestordeturnos.api.Api;
import com.example.gestordeturnos.graphic.service.TextFieldDataHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class DeleteTurnController {
    @FXML
    public TextField dni;
    @FXML
    public Label responseMessage;

    @FXML
    protected void deleteTurn() {
        if(!TextFieldDataHandler.isDni(dni.getText())){
            responseMessage.setText("El dni debe contener 8 digitos numericos");
            responseMessage.setTextFill(Color.RED);
            return;
        }

        Long enteredDni = Long.valueOf(dni.getText());
        String response = Api.getTurnController().deleteTurn(enteredDni);
        responseMessage.setText(response);
    }
}
