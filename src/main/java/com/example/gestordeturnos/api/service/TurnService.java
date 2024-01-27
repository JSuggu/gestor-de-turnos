package com.example.gestordeturnos.api.service;

import com.example.gestordeturnos.api.entity.Turn;
import com.example.gestordeturnos.api.implement.Service;
import com.example.gestordeturnos.utils.enums.Speciality;
import com.example.gestordeturnos.utils.TurnMapper;
import com.example.gestordeturnos.utils.dto.TurnDTO;

import java.time.LocalDateTime;
import java.util.List;

public class TurnService implements Service<TurnDTO> {
    public TurnDTO saveTurn(TurnDTO request){
        Turn newTurn = TurnMapper.toTurn(request);

        return JpaQuerySession.inSession(entityManager -> {
            entityManager.persist(newTurn);
            return TurnMapper.toTurnDTO(newTurn);
        });
    }

    public TurnDTO getTurn(Long dni){
        return JpaQuerySession.inSession(entityManager -> {
           Turn dbTurn = entityManager.createQuery("SELECT t FROM Turn t WHERE t.dni = :dni", Turn.class).setParameter("dni", dni).getSingleResult();
           return TurnMapper.toTurnDTO(dbTurn);
        });
    }

    public List<TurnDTO> getTurnsBySpeciality(Speciality speciality){
        return JpaQuerySession.inSession(entityManager -> {
            List<Turn> dbTurns = entityManager.createQuery("SELECT t FROM Turn t WHERE t.speciality = :speciality", Turn.class)
                    .setParameter("speciality", speciality).getResultList();
            return dbTurns.stream().map(turn -> TurnMapper.toTurnDTO(turn)).toList();
        });
    }

    public List<TurnDTO> getAllTurns(){
        return JpaQuerySession.inSession(entityManager -> {
           List<Turn> dbAllTurns = entityManager.createQuery("SELECT t FROM Turn t", Turn.class).getResultList();
           return dbAllTurns.stream().map(turn -> TurnMapper.toTurnDTO(turn)).toList();
        });
    }

    public TurnDTO updateDateTurn(LocalDateTime newDate, Long dni){
        return JpaQuerySession.inSession(entityManager -> {
            Turn dbTurn = entityManager.createQuery("SELECT t FROM Turn t WHERE t.dni = :dni", Turn.class).setParameter("dni", dni).getSingleResult();
            dbTurn.setDate(newDate);
            Turn updatedTurn = entityManager.merge(dbTurn);
            return TurnMapper.toTurnDTO(updatedTurn);
        });
    }

    public String deleteTurn(Long dni){
        return JpaQuerySession.inSession(entityManager -> {
            entityManager.createQuery("DELETE FROM Turn t WHERE t.dni = :dni").setParameter("dni", dni).executeUpdate();
            return "Turno eliminado";
        });
    }
}
