package com.example.gestordeturnos.api.entity;
import com.example.gestordeturnos.utils.enums.Speciality;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "turns",
        uniqueConstraints = @UniqueConstraint(columnNames = {"speciality", "date"})
)
public class Turn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String patientName;
    @Column(nullable = false, unique = true)
    private Long dni;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private boolean served;

    public Turn(){}
    public Turn(String patientName, Long dni, Speciality speciality, LocalDateTime date){
        this.patientName = patientName;
        this.dni = dni;
        this.speciality = speciality;
        this.date = date;
        this.served = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isServed() {
        return served;
    }

    public void setServed(boolean served) {
        this.served = served;
    }

    @Override
    public String toString() {
        return "Turn{" +
                "id=" + id +
                ", patientName='" + patientName + '\'' +
                ", dni=" + dni +
                ", speciality=" + speciality +
                ", date=" + date +
                ", served=" + served +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Turn turn)) return false;
        return served == turn.served && Objects.equals(id, turn.id) && Objects.equals(patientName, turn.patientName) && Objects.equals(dni, turn.dni) && speciality == turn.speciality && Objects.equals(date, turn.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientName, dni, speciality, date, served);
    }
}
