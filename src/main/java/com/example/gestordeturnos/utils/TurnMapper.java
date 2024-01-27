package com.example.gestordeturnos.utils;

import com.example.gestordeturnos.api.entity.Turn;
import com.example.gestordeturnos.utils.dto.TurnDTO;

public abstract class TurnMapper {
    public static TurnDTO toTurnDTO(Turn turn){
        TurnDTO turnDTO = new TurnDTO();
        turnDTO.setPatientName(turn.getPatientName());
        turnDTO.setDni(turn.getDni());
        turnDTO.setSpeciality(turn.getSpeciality());
        turnDTO.setDate(turn.getDate());
        turnDTO.setServed(turn.isServed());

        return turnDTO;
    }

    public static Turn toTurn(TurnDTO turnDTO){
        Turn turn = new Turn();
        turn.setPatientName(turnDTO.getPatientName());
        turn.setDni(turnDTO.getDni());
        turn.setSpeciality(turnDTO.getSpeciality());
        turn.setDate(turnDTO.getDate());
        turn.setServed(turnDTO.getServed());

        return turn;
    }
}
