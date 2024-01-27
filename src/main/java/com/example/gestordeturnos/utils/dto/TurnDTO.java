package com.example.gestordeturnos.utils.dto;

import com.example.gestordeturnos.utils.enums.Speciality;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class TurnDTO {
    private String patientName;
    private Long dni;
    private Speciality speciality;
    private LocalDateTime date;
    private Boolean served;

    public TurnDTO(){}

    public TurnDTO(String patientName, Long dni, Speciality speciality, LocalDateTime date){
        this.patientName = patientName;
        this.dni = dni;
        this.speciality = speciality;
        this.date = date;
        this.served = false;
    }
    public TurnDTO(String patientName, Long dni, Speciality speciality, LocalDateTime date, Boolean served){
        this.patientName = patientName;
        this.dni = dni;
        this.speciality = speciality;
        this.date = date;
        this.served = served;
    }

    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    public SimpleStringProperty patientProperty(){return new SimpleStringProperty(this.patientName);}

    public Long getDni() {
        return dni;
    }
    public void setDni(Long dni) {
        this.dni = dni;
    }
    public SimpleLongProperty dniProperty(){return new SimpleLongProperty(this.dni);}

    public Speciality getSpeciality() {
        return speciality;
    }
    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
    public SimpleObjectProperty specialityProperty(){return new SimpleObjectProperty(this.speciality);}
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public SimpleObjectProperty dateProperty(){return new SimpleObjectProperty(this.date);}

    public Boolean getServed() {
        return served;
    }
    public void setServed(Boolean served) {
        this.served = served;
    }
    public SimpleBooleanProperty servedProperty(){return new SimpleBooleanProperty(this.served);}

    @Override
    public String toString() {
        return "TurnDTO{" +
                "patientName='" + patientName + '\'' +
                ", dni=" + dni +
                ", speciality=" + speciality +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof TurnDTO turnDTO)) return false;
        return Objects.equals(patientName, turnDTO.patientName) && Objects.equals(dni, turnDTO.dni) && speciality == turnDTO.speciality && Objects.equals(date, turnDTO.date) && Objects.equals(served, turnDTO.served);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientName, dni, speciality, date, served);
    }
}
