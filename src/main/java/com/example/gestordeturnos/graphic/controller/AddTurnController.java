package com.example.gestordeturnos.graphic.controller;

import com.example.gestordeturnos.api.Api;
import com.example.gestordeturnos.graphic.service.TextFieldDataHandler;
import com.example.gestordeturnos.utils.dto.TurnDTO;
import com.example.gestordeturnos.utils.enums.Speciality;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class AddTurnController {
    @FXML
    private Label responseMessage;
    @FXML
    TextField name;
    @FXML
    TextField dni;
    @FXML
    DatePicker date;
    @FXML
    ChoiceBox<String> speciality;
    @FXML
    Spinner<Integer> hourTime;
    @FXML
    Spinner<Integer> minutesTime;

    @FXML
    private void initialize() {
        SpinnerValueFactory<Integer> spinnerFactoryForHour = new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 18, 12);
        SpinnerValueFactory<Integer> spinnerFactoryForMinutes = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 30);
        hourTime.setValueFactory(spinnerFactoryForHour);
        minutesTime.setValueFactory(spinnerFactoryForMinutes);
    }

    @FXML
    protected void addTurn() {
        if(!TextFieldDataHandler.isName(name.getText())){
            responseMessage.setText("El nombre debe contener solo letras y espacios");
            responseMessage.setTextFill(Color.RED);
            return;
        }

        if(!TextFieldDataHandler.isDni(dni.getText())){
            responseMessage.setText("El dni debe contener 8 digitos numericos");
            responseMessage.setTextFill(Color.RED);
            return;
        }

        if(date.getValue() == null || date.getValue().isBefore(LocalDate.now())){
            responseMessage.setText("Debes elegir una fecha valida");
            responseMessage.setTextFill(Color.RED);
            return;
        }

        if(speciality.getValue() == null){
            responseMessage.setText("Debes elegir una especialidad");
            responseMessage.setTextFill(Color.RED);
            return;
        }

        String turnName = name.getText();
        Long turnDni = Long.parseLong(dni.getText());
        Speciality turnArea = Speciality.valueOf(speciality.getValue().toUpperCase());
        LocalDateTime turnDate = LocalDateTime.of(date.getValue().getYear(), date.getValue().getMonth(), date.getValue().getDayOfMonth(), hourTime.getValue(), minutesTime.getValue());

        TurnDTO newTurn = new TurnDTO(turnName, turnDni, turnArea, turnDate);
        TurnDTO savedTurn = Api.getTurnController().saveTurn(newTurn);

        responseMessage.setText(formatResponseMessage(savedTurn));
    }

    private String formatResponseMessage(TurnDTO savedTurn){
        LocalDateTime savedTurnDate = savedTurn.getDate();

        return "Turno Agregado \n" +
                "Nombre: " + savedTurn.getPatientName() + "\n"+
                "Dni: " + savedTurn.getDni() + "\n"+
                "Especialidad: " + savedTurn.getSpeciality().toString() + "\n"+
                "Fecha: " + savedTurnDate.getYear() + savedTurnDate.getMonth() + savedTurnDate.getDayOfMonth() + "\n"+
                "Hora: " + savedTurnDate.getHour() + ":" + savedTurnDate.getMinute();
    }
}
