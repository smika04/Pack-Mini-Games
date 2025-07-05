package com.example.packminigames.Controllers.MVC.DAO.RecordController;

import com.example.packminigames.Controllers.MVC.DAO.AbstractControllerMVCDaoTest;
import com.example.packminigames.Controller.MVC_Controllers.RecordController.RecordMVC_Controller_impl; // Припустімо, що це шлях до вашого контролера
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Service.DAO.RecordService.RecordService_impl; // Припустімо, що це шлях до вашого сервісу
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecordMVC_Controller_impl.class)
@DisplayName("Record MVC Controller DAO Tests")
public class RecordControllerMVCDaoTest extends AbstractControllerMVCDaoTest<RecordService_impl, RecordDTO> {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RecordService_impl recordService;

    // --- Реалізація абстрактних методів з AbstractControllerMVCDaoTest ---

    @Override
    protected MockMvc getMockMvc() {return mockMvc;}

    @Override
    protected RecordService_impl getServiceMock() {return recordService;}

    @Override
    protected String getBaseUrl() {return "record";}

    @Override
    protected String getDtoAttributeName() {return "record";}

    @Override
    protected RecordDTO createNewDto()
    {
        return RecordDTO.builder()
                .gameId(1L)
                .userId(1L)
                .score(100)
                .datePlayed(LocalDateTime.now()).build();
    }

    @Override
    protected RecordDTO createExistingDto(Long id)
    {
        return RecordDTO.builder()
                .id(id)
                .gameId(id % 2 == 0 ? 2L : 1L)
                .userId(id % 3 == 0 ? 3L : 1L)

                .score(100 + id.intValue() * 10)
                .datePlayed(LocalDateTime.now().minusDays(id)).build();
    }

    @Override
    protected Class<RecordDTO> getDtoClass() {return RecordDTO.class;}

    @Nested
    @DisplayName("Specific Record Controller Tests")
    class SpecificRecordTests
    {
        @Test
        @DisplayName("POST /record/create: Повинен повернути форму з помилками для невалідного RecordDTO (наприклад, від'ємний рахунок)")
        void testCreateRecordWithInvalidScore() throws Exception
        {
            RecordDTO invalidRecord = RecordDTO.builder()
                    .gameId(1L)
                    .userId(1L)
                    .score(-50)
                    .datePlayed(LocalDateTime.now()).build();

            getMockMvc().perform(post("/" + getBaseUrl() + "/create")
                            .flashAttr("dtoEntity", invalidRecord))
                    .andExpect(status().isOk())
                    .andExpect(view().name(getBaseUrl() + "/form"))
                    .andExpect(model().attributeHasFieldErrors("dtoEntity", "score"))
                    .andExpect(model().attribute("org.springframework.validation.BindingResult.dtoEntity", isA(BindingResult.class)));

            verify(getServiceMock(), never()).Add(any(RecordDTO.class));
        }

        @Test
        @DisplayName("POST /record/create: Повинен повернути форму з помилками, якщо gameId або userId відсутні (якщо вони @NotNull)")
        void testCreateRecordWithMissingIds() throws Exception
        {
            RecordDTO missingIdsRecord = RecordDTO.builder()
                    .score(100)
                    .datePlayed(LocalDateTime.now()).build();

            getMockMvc().perform(post("/" + getBaseUrl() + "/create")
                            .flashAttr("dtoEntity", missingIdsRecord))
                    .andExpect(status().isOk())
                    .andExpect(view().name(getBaseUrl() + "/form"))
                    .andExpect(model().attributeHasFieldErrors("dtoEntity", "gameId")) // Перевіряємо помилку для gameId
                    .andExpect(model().attributeHasFieldErrors("dtoEntity", "userId")) // Перевіряємо помилку для userId
                    .andExpect(model().attribute("org.springframework.validation.BindingResult.dtoEntity", isA(BindingResult.class)));

            verify(getServiceMock(), never()).Add(any(RecordDTO.class));
        }
    }
}