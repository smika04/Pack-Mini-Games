package com.example.packminigames.Controllers.REST.DAO.RecordController;

import com.example.packminigames.Controller.REST_Controllers.RecordController.RecordREST_Controller_impl;
import com.example.packminigames.Controllers.REST.DAO.AbstractControllerRESTDaoTest;
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Service.DAO.RecordService.IRecordService;
import com.example.packminigames.Service.DAO.RecordService.RecordService_impl;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;

@DisplayName("Record REST Controller Integration Tests")
@WebMvcTest(RecordREST_Controller_impl.class) // Вказуємо контролер, який буде тестуватися
public class RecordControllerRESTDaoTest extends AbstractControllerRESTDaoTest<RecordDTO, IRecordService>
{
    @MockitoBean
    private RecordService_impl serviceImpl; // Мокаємо реалізацію сервісу

    @Override
    protected IRecordService getService() {
        return serviceImpl;
    }

    @Override
    protected String getBaseUrl() {
        return "/api/records"; // Базовий URL для контролера Record, змініть, якщо інший
    }

    @Override
    protected RecordDTO createDTO(Long id, String identifier) {
        // Створюємо RecordDTO для тестів.
        // 'identifier' може бути використаний для унікальності, наприклад, у рахунку (score)
        // або комбінації GameId/UserId, якщо це більш релевантно для унікальності записів.
        // Для простоти використаємо його як частину score, щоб він був унікальним.
        int score = 1000 + (identifier != null ? identifier.hashCode() % 1000 : 0);

        return RecordDTO.builder()
                .id(id)
                .gameId(1L) // Можна використовувати фіксовані ID для тестів
                .userId(1L)
                .score(score)
                .datePlayed(LocalDateTime.now()) // Використовуємо поточний час
                .build();
    }
}
