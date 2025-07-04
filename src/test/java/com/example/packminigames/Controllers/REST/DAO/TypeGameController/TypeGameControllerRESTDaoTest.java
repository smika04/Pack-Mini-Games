package com.example.packminigames.Controllers.REST.DAO.TypeGameController;

import com.example.packminigames.Controller.REST_Controllers.TypeGameController.TypeGameREST_Controller_impl;
import com.example.packminigames.Controllers.REST.DAO.AbstractControllerRESTDaoTest;
import com.example.packminigames.Models.DTO.TypeGameDTO;
import com.example.packminigames.Service.DAO.TypeGameService.ITypeGameService;
import com.example.packminigames.Service.DAO.TypeGameService.TypeGameService_impl;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@DisplayName("TypeGame REST Controller Integration Tests")
@WebMvcTest(TypeGameREST_Controller_impl.class) // Вказуємо контролер, який буде тестуватися
public class TypeGameControllerRESTDaoTest extends AbstractControllerRESTDaoTest<TypeGameDTO, ITypeGameService>
{
    @MockitoBean
    private TypeGameService_impl serviceImpl; // Мокаємо реалізацію сервісу

    @Override
    protected ITypeGameService getService() {
        return serviceImpl;
    }

    @Override
    protected String getBaseUrl() {
        return "/api/typegames"; // Базовий URL для контролера TypeGame, змініть, якщо інший
    }

    @Override
    protected TypeGameDTO createDTO(Long id, String identifier) {
        // Створюємо TypeGameDTO для тестів
        return TypeGameDTO.builder()
                .id(id)
                .name(identifier) // 'identifier' тут буде використовуватися як назва типу гри
                .build();
    }
}
