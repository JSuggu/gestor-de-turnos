package com.example.gestordeturnos.api.implement;

import com.example.gestordeturnos.api.entity.Turn;
import com.example.gestordeturnos.utils.dto.TurnDTO;
import com.example.gestordeturnos.utils.enums.Speciality;

import java.time.LocalDateTime;
import java.util.List;

public interface Service<T> {
    T saveTurn(T request);

    T getTurn(Long dni);

    List<T> getTurnsBySpeciality(Speciality speciality);

    List<T> getAllTurns();

    T updateDateTurn(LocalDateTime newDate, Long dni);

    String deleteTurn(Long dni);
}
