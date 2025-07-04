package com.example.packminigames.Controllers.REST.DAO.GameController;

import com.example.packminigames.Controller.REST_Controllers.GameController.GameREST_Controller_impl;
import com.example.packminigames.Controllers.REST.DAO.AbstractControllerRESTDaoTest;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Service.DAO.GameService.GameService_impl;
import com.example.packminigames.Service.DAO.GameService.IGameService;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DisplayName("Game REST Controller Integration Tests")
@WebMvcTest(GameREST_Controller_impl.class) // Вказуємо контролер, який буде тестуватися
public class GameControllerRESTDaoTest extends AbstractControllerRESTDaoTest<GameDTO, IGameService>
{
    @MockitoBean
    private GameService_impl serviceImpl; // Мокаємо реалізацію сервісу

    @Override
    protected IGameService getService() {
        return serviceImpl;
    }

    @Override
    protected String getBaseUrl() {
        return "/api/games"; // Базовий URL для контролера Game, змініть, якщо інший
    }

    @Override
    protected GameDTO createDTO(Long id, String identifier) {
        // Створюємо GameDTO для тестів.
        // 'identifier' може бути використаний для унікальності, наприклад, у заголовку гри.

        // Можна додати тестові записи, якщо це потрібно для валідації або специфічних тестів
        List<RecordDTO> records = new ArrayList<>();
        if (id != null) {
            records.add(RecordDTO.builder()
                    .id(id * 100)
                    .gameId(id)
                    .userId(1L)
                    .score(100)
                    .datePlayed(LocalDateTime.now())
                    .build());
        }

        return GameDTO.builder()
                .id(id)
                .title("Test Game " + identifier)
                .description("Description for Test Game " + identifier)
                .typeGameName("Action") // Приклад типу гри
                .iconData(("icon_data_for_game_" + identifier).getBytes()) // Приклад байтового масиву
                .records(records)
                .build();
    }
}
