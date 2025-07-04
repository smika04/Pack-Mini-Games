package com.example.packminigames.Controllers.REST.DAO.GameIconController;

import com.example.packminigames.Controller.REST_Controllers.GameIconController.GameIconREST_Controller_impl;
import com.example.packminigames.Controllers.REST.DAO.AbstractControllerRESTDaoTest;
import com.example.packminigames.Models.DTO.GameIconDTO;
import com.example.packminigames.Service.DAO.GameIconService.GameIconService_impl;
import com.example.packminigames.Service.DAO.GameIconService.IGameIconService;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@DisplayName("GameIcon REST Controller Integration Tests")
@WebMvcTest(GameIconREST_Controller_impl.class) // Вказуємо контролер, який буде тестуватися
public class GameIconControllerRESTDaoTest extends AbstractControllerRESTDaoTest<GameIconDTO, IGameIconService>
{
    @MockitoBean
    private GameIconService_impl serviceImpl; // Мокаємо реалізацію сервісу

    @Override
    protected IGameIconService getService() {
        return serviceImpl;
    }

    @Override
    protected String getBaseUrl() {
        return "/api/gameicons"; // Базовий URL для контролера GameIcon, змініть, якщо інший
    }

    @Override
    protected GameIconDTO createDTO(Long id, String identifier) {
        // Створюємо GameIconDTO для тестів.
        // 'identifier' може бути використаний для унікальності, наприклад, у назві іконки.
        return GameIconDTO.builder()
                .id(id)
                .name("test_icon_" + identifier)
                .iconData(("icon_data_for_" + identifier).getBytes()) // Приклад байтового масиву
                .contentType("image/png")
                .gameId(id != null ? id + 100L : 100L) // Приклад gameId, пов'язаного з id
                .build();
    }
}
