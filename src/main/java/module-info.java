module com.example.gestordeturnos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens com.example.gestordeturnos to javafx.fxml;
    exports com.example.gestordeturnos;
    exports com.example.gestordeturnos.graphic;
    opens com.example.gestordeturnos.graphic to javafx.fxml;
    opens com.example.gestordeturnos.api.entity to org.hibernate.orm.core;
    exports com.example.gestordeturnos.graphic.controller;
    opens com.example.gestordeturnos.graphic.controller to javafx.fxml;
    exports com.example.gestordeturnos.utils;
    opens com.example.gestordeturnos.utils to javafx.fxml;
    exports com.example.gestordeturnos.api;
    opens com.example.gestordeturnos.api to javafx.fxml;
    exports com.example.gestordeturnos.utils.dto;
    opens com.example.gestordeturnos.utils.dto to javafx.base;
    exports com.example.gestordeturnos.utils.enums;
    opens com.example.gestordeturnos.utils.enums to javafx.base;
}