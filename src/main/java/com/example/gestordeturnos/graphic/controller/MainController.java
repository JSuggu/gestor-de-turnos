package com.example.gestordeturnos.graphic.controller;

import com.example.gestordeturnos.api.Api;
import com.example.gestordeturnos.graphic.LoadViews;
import com.example.gestordeturnos.graphic.service.CheckBoxCellCustom;
import com.example.gestordeturnos.utils.dto.TurnDTO;
import com.example.gestordeturnos.utils.enums.Speciality;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class MainController {
    @FXML
    public Label currentDate;
    @FXML
    public FlowPane turnsContainer;

    @FXML
    protected void seeTurns(){
        Speciality[] specialities = Speciality.values();
        ObservableList<TurnDTO> turns;

        if(!turnsContainer.getChildren().isEmpty()) turnsContainer.getChildren().clear();

        for (Speciality speciality : specialities) {
            turns = getTurnsBySpeciality(speciality.name());
            VBox table = createTable(speciality.name(), turns);
            turnsContainer.getChildren().add(table);
        }
    }

    @FXML
    protected void addTurn() throws IOException {
        LoadViews.load("add-turn.fxml", 800, 600);
    }

    @FXML
    protected void deleteTurn() throws IOException {
        LoadViews.load("delete-turn.fxml", 800, 600);
    }

    private VBox createTable(String specialityName, ObservableList<TurnDTO> turns){
        Label tableTitle = new Label(specialityName);
        tableTitle.getStyleClass().add("tableTitle");

        TableView<TurnDTO> table = new TableView<>();
        table.getStyleClass().add("table");

        TableColumn<TurnDTO, Long> codeColumn = new TableColumn<>("Dni");
        codeColumn.getStyleClass().add("codeColumn");

        TableColumn<TurnDTO, String> patientColumn = new TableColumn<>("Nombre");
        patientColumn.getStyleClass().add("patientColumn");

        TableColumn<TurnDTO, LocalDateTime> dateColumn = new TableColumn<>("Fecha");
        dateColumn.getStyleClass().add("dateColumn");

        TableColumn<TurnDTO, Boolean> servedColumn = new TableColumn<>("Atendido");
        servedColumn.getStyleClass().add("servedColumn");

        codeColumn.setCellValueFactory(cell -> cell.getValue().dniProperty().asObject());
        patientColumn.setCellValueFactory(cell -> cell.getValue().patientProperty());
        dateColumn.setCellValueFactory(cell -> cell.getValue().dateProperty());
        servedColumn.setCellValueFactory(cell -> cell.getValue().servedProperty());
        servedColumn.setCellFactory(column -> new CheckBoxCellCustom<>());

        table.getColumns().addAll(codeColumn,patientColumn,dateColumn, servedColumn);
        table.setItems(turns);

        VBox tableContainer = new VBox(tableTitle,table);
        tableContainer.getStyleClass().add("tableContainer");

        return tableContainer;
    }

    private ObservableList<TurnDTO> getTurnsBySpeciality(String specialityName){
        List<TurnDTO> turns = new ArrayList<>(Api.getTurnController().getTurnsBySpeciality(Speciality.valueOf(specialityName.toUpperCase())));
        return FXCollections.observableList(turns);
    }

    public void searchTurn() throws IOException {
        LoadViews.load("turn.fxml", 800,600);
    }
}