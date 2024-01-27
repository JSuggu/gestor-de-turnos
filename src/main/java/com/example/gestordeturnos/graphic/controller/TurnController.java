package com.example.gestordeturnos.graphic.controller;

import com.example.gestordeturnos.api.Api;
import com.example.gestordeturnos.graphic.service.TextFieldDataHandler;
import com.example.gestordeturnos.utils.dto.TurnDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.lang.reflect.Field;

public class TurnController {
    @FXML
    public TextField searchField;
    @FXML
    public HBox responseContainer;

    @FXML
    protected void searchTurn() {
        responseContainer.getChildren().clear();

        if(!TextFieldDataHandler.isDni(searchField.getText())){
            Label errorMessage = new Label("El dni no existe o no es valido");
            errorMessage.setTextFill(Color.RED);
            responseContainer.getChildren().add(errorMessage);
            return;
        }

        TurnDTO turn = Api.getTurnController().getTurn(Long.valueOf(searchField.getText()));
        turnResponse(turn);
    }

    private void turnResponse (TurnDTO turn){
        VBox mainDataContainer = new VBox();
        Class<?> classTurn = turn.getClass();
        Field[] turnField = classTurn.getDeclaredFields();

        Object value;
        String fieldName;

        for(Field field: turnField){
            field.setAccessible(true); // Hacer el campo accesible aunque sea privado
            try {
                value = field.get(turn);
                fieldName = field.getName();

                Label labelForData = new Label(obtainLabelName(fieldName));
                labelForData.getStyleClass().add("labelForData");
                Label data = new Label(value.toString());
                HBox dataContainer = new HBox(labelForData, data);
                dataContainer.getStyleClass().add("dataContainer");
                mainDataContainer.getChildren().add(dataContainer);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        responseContainer.getChildren().add(mainDataContainer);
    }

    private String obtainLabelName(Object fieldName){
        if(fieldName.equals("patientName")) return "Nombre: ";
        if(fieldName.equals("dni")) return "Dni: ";
        if(fieldName.equals("speciality")) return "Especialidad: ";
        if(fieldName.equals("date")) return "Fecha: ";
        return "Atendido: ";
    }
}
