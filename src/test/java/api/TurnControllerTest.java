package api;

import com.example.gestordeturnos.api.Api;
import com.example.gestordeturnos.api.entity.Turn;
import com.example.gestordeturnos.utils.TurnMapper;
import com.example.gestordeturnos.utils.dto.TurnDTO;
import com.example.gestordeturnos.utils.enums.Speciality;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class TurnControllerTest {


    @BeforeEach
    public void createTurn(){
        Turn turn = new Turn("franco", 41100188L, Speciality.CARDIOLOGIA, LocalDateTime.of(2024,2,10,10,30));
        Turn turn1 = new Turn("camila", 25152367L, Speciality.ODONTOLOGIA, LocalDateTime.of(2024,2,9,10,0));
        Turn turn2 = new Turn("ruben", 4512628L, Speciality.NUTRICION, LocalDateTime.of(2024,2,8,10,0));
        Turn turn3 = new Turn("camila", 20222367L, Speciality.ODONTOLOGIA, LocalDateTime.of(2024,2,20,10,0));
        Turn turn4 = new Turn("ruben", 4810028L, Speciality.ODONTOLOGIA, LocalDateTime.of(2024,2,15,10,0));
        Api.getTurnController().saveTurn(TurnMapper.toTurnDTO(turn));
        Api.getTurnController().saveTurn(TurnMapper.toTurnDTO(turn1));
        Api.getTurnController().saveTurn(TurnMapper.toTurnDTO(turn2));
        Api.getTurnController().saveTurn(TurnMapper.toTurnDTO(turn3));
        Api.getTurnController().saveTurn(TurnMapper.toTurnDTO(turn4));
    }

    @Test
    public void saveTurn(){
        Turn newTurn = new Turn("franco", 41000153L, Speciality.CARDIOLOGIA, LocalDateTime.of(2024,2,15,10,0));
        TurnDTO newTurnDTO = TurnMapper.toTurnDTO(newTurn);

        TurnDTO result = Api.getTurnController().saveTurn(newTurnDTO);

        Assertions.assertEquals(newTurnDTO, result);
    }

    @Test
    public void getTurn(){
        TurnDTO result = Api.getTurnController().getTurn(41100188L);

        Assertions.assertEquals(result.getDni(), 41100188L);
        Assertions.assertEquals(result.getDate(), LocalDateTime.of(2024,2,10,10,30));
        Assertions.assertEquals(result.getSpeciality(), Speciality.CARDIOLOGIA);
    }

    @Test
    public void deleteTurn(){
        String result = Api.getTurnController().deleteTurn(41100188L);

        Assertions.assertEquals(result, "Turno eliminado");
        Assertions.assertThrows(NoResultException.class, () -> {
            Api.getTurnController().getTurn(41100188L);
        });
    }

    @Test
    public void getTurnBySpeciality(){
        List<TurnDTO> turns = Api.getTurnController().getTurnsBySpeciality(Speciality.ODONTOLOGIA);

        Assertions.assertEquals(3, turns.size());
        turns.forEach(turn -> {
            Assertions.assertEquals(Speciality.ODONTOLOGIA, turn.getSpeciality());
        });
    }

    @Test
    public void getAllTurns(){
        List<TurnDTO> turns = Api.getTurnController().getAllTurns();

        Assertions.assertEquals(5, turns.size());
    }

    @Test
    public void updateTurnDate(){
        TurnDTO turn = Api.getTurnController().getTurn(41100188L);

        LocalDateTime newDate = LocalDateTime.of(2024,2,15,17,0);
        TurnDTO updatedTurn = Api.getTurnController().updateDateTurn(newDate, 41100188L);

        Assertions.assertNotEquals(turn, updatedTurn);
        Assertions.assertEquals(newDate, updatedTurn.getDate());
    }

    @Test
    public void getTurnNotExist(){
        Assertions.assertThrows(NoResultException.class, () -> {
           Api.getTurnController().getTurn(12684798L);
        });
    }


}
