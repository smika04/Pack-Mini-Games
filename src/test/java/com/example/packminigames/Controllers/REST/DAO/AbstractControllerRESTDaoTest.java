package com.example.packminigames.Controllers.REST.DAO;

import com.example.packminigames.Controllers.AbstractDAOServiceMockTest;
import com.example.packminigames.Service.DAO.IBasicServiceDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

// Явні статичні імпорти для MockMvcRequestBuilders
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

// Явні статичні імпорти для MockMvcResultMatchers
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DisplayName("Abstract Base Test for REST API Controller Operations")
public abstract class AbstractControllerRESTDaoTest<DTO, SERVICE extends IBasicServiceDAO<DTO>> extends AbstractDAOServiceMockTest<DTO, SERVICE>
{
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected abstract String getBaseUrl();

    // --- Загальні тести для REST контролерів ---

    @Test
    @DisplayName("GET /base_url - Should return all DTOs")
    void testGetAllReturnsAll() throws Exception {
        // Given
        DTO dto1 = createDTO(1L, "Test1");
        DTO dto2 = createDTO(2L, "Test2");
        setupGetAll(List.of(dto1, dto2));

        // When & Then
        mockMvc.perform(get(getBaseUrl()) // Використовуємо явний get()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Використовуємо явний status()
                .andExpect(jsonPath("$[0].id").value(1L)) // Використовуємо явний jsonPath()
                .andExpect(jsonPath("$[1].id").value(2L));

        verifyGetAllCalled();
    }

    @Test
    @DisplayName("GET /base_url/{id} - Should return DTO by ID if found")
    void testGetByIdFound() throws Exception {
        // Given
        DTO dto = createDTO(1L, "TestUser");
        setupGetById(1L, Optional.of(dto));

        // When & Then
        mockMvc.perform(get(getBaseUrl() + "/1") // Використовуємо явний get()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Використовуємо явний status()
                .andExpect(jsonPath("$.id").value(1L)); // Використовуємо явний jsonPath()

        verifyGetByIdCalled(1L);
    }

    @Test
    @DisplayName("GET /base_url/{id} - Should return 404 Not Found if DTO not found")
    void testGetByIdNotFound() throws Exception {
        // Given
        setupGetById(99L, Optional.empty());

        // When & Then
        mockMvc.perform(get(getBaseUrl() + "/99") // Використовуємо явний get()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); // Використовуємо явний status()

        verifyGetByIdCalled(99L);
    }

    @Test
    @DisplayName("POST /base_url - Should create a new DTO")
    void testCreateNew() throws Exception {
        // Given
        DTO newDto = createDTO(null, "NewItem");
        DTO createdDto = createDTO(3L, "NewItem");
        setupAdd(createdDto);

        // When & Then
        mockMvc.perform(post(getBaseUrl()) // Використовуємо явний post()
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newDto)))
                .andExpect(status().isCreated()) // Використовуємо явний status()
                .andExpect(jsonPath("$.id").value(3L)); // Використовуємо явний jsonPath()

        verifyAddCalled();
    }

    @Test
    @DisplayName("PUT /base_url/{id} - Should update an existing DTO")
    void testUpdateExisting() throws Exception {
        // Given
        DTO updatedDto = createDTO(1L, "UpdatedItem");
        setupUpdate(updatedDto);

        // When & Then
        mockMvc.perform(put(getBaseUrl() + "/1") // Використовуємо явний put()
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isOk()) // Використовуємо явний status()
                .andExpect(jsonPath("$.id").value(1L)); // Використовуємо явний jsonPath()

        verifyUpdateCalled();
    }

    @Test
    @DisplayName("PUT /base_url/{id} - Should return 404 Not Found if DTO to update not found")
    void testUpdateNotFound() throws Exception {
        // Given
        DTO nonExistentDto = createDTO(99L, "NonExistent");
        setupUpdateThrows(new jakarta.persistence.EntityNotFoundException("Item not found"));

        // When & Then
        mockMvc.perform(put(getBaseUrl() + "/99") // Використовуємо явний put()
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nonExistentDto)))
                .andExpect(status().isNotFound()); // Використовуємо явний status()

        verifyUpdateCalled();
    }

    @Test
    @DisplayName("DELETE /base_url/{id} - Should delete an existing DTO")
    void testDeleteExisting() throws Exception {
        // Given
        setupDelete(1L);

        // When & Then
        mockMvc.perform(delete(getBaseUrl() + "/1") // Використовуємо явний delete()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()); // Використовуємо явний status()

        verifyDeleteCalled(1L);
    }

    @Test
    @DisplayName("DELETE /base_url/{id} - Should return 404 Not Found if DTO to delete not found")
    void testDeleteNotFound() throws Exception {
        // Given
        setupDeleteThrows(99L, new RuntimeException("Сутність з ID 99 не знайдена."));

        // When & Then
        mockMvc.perform(delete(getBaseUrl() + "/99") // Використовуємо явний delete()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); // Використовуємо явний status()

        verifyDeleteCalled(99L);
    }
}
