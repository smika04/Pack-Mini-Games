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
    void testGetAllReturnsAll() throws Exception
    {
        DTO dto1 = createDTO(1L, "Test1");
        DTO dto2 = createDTO(2L, "Test2");
        setupGetAll(List.of(dto1, dto2));

        mockMvc.perform(get(getBaseUrl())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L)) // Використовуємо явний jsonPath()
                .andExpect(jsonPath("$[1].id").value(2L));

        verifyGetAllCalled();
    }

    @Test
    @DisplayName("GET /base_url/{id} - Should return DTO by ID if found")
    void testGetByIdFound() throws Exception
    {
        DTO dto = createDTO(1L, "TestUser");
        setupGetById(1L, Optional.of(dto));

        mockMvc.perform(get(getBaseUrl() + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verifyGetByIdCalled(1L);
    }

    @Test
    @DisplayName("GET /base_url/{id} - Should return 404 Not Found if DTO not found")
    void testGetByIdNotFound() throws Exception
    {
        setupGetById(99L, Optional.empty());

        mockMvc.perform(get(getBaseUrl() + "/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verifyGetByIdCalled(99L);
    }

    @Test
    @DisplayName("POST /base_url - Should create a new DTO")
    void testCreateNew() throws Exception
    {
        DTO newDto = createDTO(null, "NewItem");
        DTO createdDto = createDTO(3L, "NewItem");
        setupAdd(createdDto);

        mockMvc.perform(post(getBaseUrl())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3L));

        verifyAddCalled();
    }

    @Test
    @DisplayName("PUT /base_url/{id} - Should update an existing DTO")
    void testUpdateExisting() throws Exception
    {
        DTO updatedDto = createDTO(1L, "UpdatedItem");
        setupUpdate(updatedDto);

        mockMvc.perform(put(getBaseUrl() + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verifyUpdateCalled();
    }

    @Test
    @DisplayName("PUT /base_url/{id} - Should return 404 Not Found if DTO to update not found")
    void testUpdateNotFound() throws Exception
    {
        DTO nonExistentDto = createDTO(99L, "NonExistent");
        setupUpdateThrows(new jakarta.persistence.EntityNotFoundException("Item not found"));

        mockMvc.perform(put(getBaseUrl() + "/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nonExistentDto)))
                .andExpect(status().isNotFound());

        verifyUpdateCalled();
    }

    @Test
    @DisplayName("DELETE /base_url/{id} - Should delete an existing DTO")
    void testDeleteExisting() throws Exception
    {
        setupDelete(1L);


        mockMvc.perform(delete(getBaseUrl() + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verifyDeleteCalled(1L);
    }

    @Test
    @DisplayName("DELETE /base_url/{id} - Should return 404 Not Found if DTO to delete not found")
    void testDeleteNotFound() throws Exception
    {
        setupDeleteThrows(99L, new RuntimeException("Сутність з ID 99 не знайдена."));

        mockMvc.perform(delete(getBaseUrl() + "/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verifyDeleteCalled(99L);
    }
}
