package com.example.gestordeturnos.api.controller;

import com.example.gestordeturnos.api.implement.Implements;
import com.example.gestordeturnos.api.implement.Service;
import com.example.gestordeturnos.utils.enums.Speciality;
import com.example.gestordeturnos.utils.dto.TurnDTO;

import java.time.LocalDateTime;
import java.util.List;

public class TurnController {
    private final Service<TurnDTO> turnService;

    public TurnController(){
        this.turnService = Implements.getTurnService();
    }
    public TurnDTO saveTurn(TurnDTO request){
        return turnService.saveTurn(request);
    }

    public TurnDTO getTurn(Long dni){
        return turnService.getTurn(dni);
    }

    public List<TurnDTO> getTurnsBySpeciality(Speciality speciality){
        return turnService.getTurnsBySpeciality(speciality);
    }

    public List<TurnDTO> getAllTurns(){
        return turnService.getAllTurns();
    }

    public TurnDTO updateDateTurn(LocalDateTime newDate, Long dni){
        return turnService.updateDateTurn(newDate, dni);
    }

    public String deleteTurn(Long dni){
        return turnService.deleteTurn(dni);
    }
}
