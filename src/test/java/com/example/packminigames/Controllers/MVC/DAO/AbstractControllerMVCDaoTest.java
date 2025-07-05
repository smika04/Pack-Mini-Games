package com.example.packminigames.Controllers.MVC.DAO;

import com.example.packminigames.Models.DTO.IIdentifiableDTO;
import com.example.packminigames.Service.DAO.IBasicServiceDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@DisplayName("Abstract Base Test for MVC Controller Web Operations")
public abstract class AbstractControllerMVCDaoTest<SERVICE extends IBasicServiceDAO<DTO>, DTO extends IIdentifiableDTO> {

    // Абстрактні методи, які мають бути реалізовані конкретними тестовими класами
    protected abstract MockMvc getMockMvc();
    protected abstract SERVICE getServiceMock();
    protected abstract String getBaseUrl();
    protected abstract String getDtoAttributeName();
    protected abstract DTO createNewDto();
    protected abstract DTO createExistingDto(Long id);
    protected abstract Class<DTO> getDtoClass();

    @Nested
    @DisplayName("General CRUD Operations Tests")
    class CrudOperationsTests
    {
        @Test
        @DisplayName("GET /{baseUrl} або /{baseUrl}/list: Повинен повернути список та view {baseUrl}/list")
        void testViewEntities() throws Exception
        {
            List<DTO> dtoList = Arrays.asList(createExistingDto(1L), createExistingDto(2L));
            when(getServiceMock().GetAll()).thenReturn(dtoList);


            getMockMvc().perform(get("/" + getBaseUrl() + "/list"))
                    .andExpect(status().isOk())
                    .andExpect(view().name(getBaseUrl() + "/list")) // Очікуємо view name, наприклад, "game/list"
                    .andExpect(model().attributeExists("list")) // Очікуємо атрибут "list" у моделі
                    .andExpect(model().attribute("list", hasSize(2)))
                    .andExpect(model().attribute("list", contains(dtoList.toArray())));

            verify(getServiceMock(), times(1)).GetAll();
        }

        @Test
        @DisplayName("GET /{baseUrl}/{id}: Повинен повернути деталі та view {baseUrl}/details при знаходженні")
        void testDetailsFound() throws Exception
        {
            Long existingId = 1L;
            DTO existingDto = createExistingDto(existingId);
            when(getServiceMock().GetById(existingId)).thenReturn(Optional.of(existingDto));

            getMockMvc().perform(get("/" + getBaseUrl() + "/" + existingId))
                    .andExpect(status().isOk())
                    .andExpect(view().name(getBaseUrl() + "/details"))
                    .andExpect(model().attributeExists(getDtoAttributeName()))
                    .andExpect(model().attribute(getDtoAttributeName(), existingDto));

            verify(getServiceMock(), times(1)).GetById(existingId);
        }

        @Test
        @DisplayName("GET /{baseUrl}/{id}: Повинен повернути view 'error' або перенаправити при відсутності об'єкта")
        void testDetailsNotFound() throws Exception
        {
            Long nonExistentId = 99L;
            when(getServiceMock().GetById(nonExistentId)).thenReturn(Optional.empty());

            getMockMvc().perform(get("/" + getBaseUrl() + "/" + nonExistentId))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/" + getBaseUrl() + "/list"))
                    .andExpect(flash().attributeExists("errorMessage"))
                    .andExpect(flash().attribute("errorMessage", containsString("Не знайдено"))); // Або інше повідомлення

            verify(getServiceMock(), times(1)).GetById(nonExistentId);
        }

        @Test
        @DisplayName("GET /{baseUrl}/new: Повинен повернути форму створення та view {baseUrl}/form")
        void testShowNewForm() throws Exception
        {
            DTO newDto = createNewDto();

            getMockMvc().perform(get("/" + getBaseUrl() + "/new"))
                    .andExpect(status().isOk())
                    .andExpect(view().name(getBaseUrl() + "/form"))
                    .andExpect(model().attributeExists(getDtoAttributeName()))
                    .andExpect(model().attribute(getDtoAttributeName(), hasProperty("id", nullValue())))
                    .andExpect(model().attribute(getDtoAttributeName(), instanceOf(getDtoClass())));
        }

        @Test
        @DisplayName("POST /{baseUrl}/create: Повинен створити об'єкт та перенаправити на /{baseUrl}/list при успіху")
        void testCreateSuccess() throws Exception
        {
            DTO newDto = createNewDto();
            when(getServiceMock().Add(any(getDtoClass()))).thenReturn(newDto);

            getMockMvc().perform(post("/" + getBaseUrl() + "/create")
                            .flashAttr("dto", newDto))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/" + getBaseUrl() + "/list"))
                    .andExpect(flash().attributeExists("successMessage"))
                    .andExpect(flash().attribute("successMessage", containsString("успішно створено")));

            verify(getServiceMock(), times(1)).Add(any(getDtoClass()));
        }

        @Test
        @DisplayName("POST /{baseUrl}/create: Повинен обробити помилку під час створення об'єкта (наприклад, валідація)")
        void testCreateFailure() throws Exception
        {
            DTO invalidDto = createNewDto();

            doThrow(new RuntimeException("Помилка валідації")).when(getServiceMock()).Add(any(getDtoClass()));

            getMockMvc().perform(post("/" + getBaseUrl() + "/create")
                            .flashAttr("dto", invalidDto))
                    .andExpect(status().isOk())
                    .andExpect(view().name(getBaseUrl() + "/form"))
                    .andExpect(model().attributeExists("errorMessage"))
                    .andExpect(model().attribute("errorMessage", containsString("Помилка")));

            verify(getServiceMock(), times(1)).Add(any(getDtoClass()));
        }

        @Test
        @DisplayName("GET /{baseUrl}/edit/{id}: Повинен повернути форму редагування та view {baseUrl}/form при знаходженні")
        void testShowEditFormFound() throws Exception
        {
            Long existingId = 1L;
            DTO existingDto = createExistingDto(existingId);
            when(getServiceMock().GetById(existingId)).thenReturn(Optional.of(existingDto));

            getMockMvc().perform(get("/" + getBaseUrl() + "/edit/" + existingId))
                    .andExpect(status().isOk())
                    .andExpect(view().name(getBaseUrl() + "/form"))
                    .andExpect(model().attributeExists(getDtoAttributeName()))
                    .andExpect(model().attribute(getDtoAttributeName(), existingDto));

            verify(getServiceMock(), times(1)).GetById(existingId);
        }

        @Test
        @DisplayName("GET /{baseUrl}/edit/{id}: Повинен перенаправити при відсутності об'єкта для редагування")
        void testShowEditFormNotFound() throws Exception
        {
            Long nonExistentId = 99L;
            when(getServiceMock().GetById(nonExistentId)).thenReturn(Optional.empty());

            getMockMvc().perform(get("/" + getBaseUrl() + "/edit/" + nonExistentId))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/" + getBaseUrl() + "/list"))
                    .andExpect(flash().attributeExists("errorMessage"))
                    .andExpect(flash().attribute("errorMessage", containsString("Не знайдено")));

            verify(getServiceMock(), times(1)).GetById(nonExistentId);
        }

        @Test
        @DisplayName("POST /{baseUrl}/update/{id}: Повинен оновити об'єкт та перенаправити на /{baseUrl}/list при успіху")
        void testUpdateSuccess() throws Exception
        {
            Long existingId = 1L;
            DTO updatedDto = createExistingDto(existingId);

            when(getServiceMock().Update(updatedDto)).thenReturn(updatedDto);

            getMockMvc().perform(post("/" + getBaseUrl() + "/update/" + existingId)
                            .flashAttr("dto", updatedDto))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/" + getBaseUrl() + "/list"))
                    .andExpect(flash().attributeExists("successMessage"))
                    .andExpect(flash().attribute("successMessage", containsString("успішно оновлено")));

            verify(getServiceMock(), times(1)).Update(updatedDto);
        }

        @Test
        @DisplayName("POST /{baseUrl}/update/{id}: Повинен обробити EntityNotFoundException під час оновлення")
        void testUpdateNotFound() throws Exception
        {
            Long nonExistentId = 99L;
            DTO someDto = createNewDto();

            doThrow(new RuntimeException("Об'єкт не знайдено для оновлення")).when(getServiceMock()).Update(any(getDtoClass()));

            getMockMvc().perform(post("/" + getBaseUrl() + "/update/" + nonExistentId)
                            .flashAttr("dto", someDto))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/" + getBaseUrl() + "/list"))
                    .andExpect(flash().attributeExists("errorMessage"))
                    .andExpect(flash().attribute("errorMessage", containsString("Не знайдено")));

            verify(getServiceMock(), times(1)).Update(any(getDtoClass())); // Перевіряємо, що Update було викликано з будь-яким DTO
        }

        @Test
        @DisplayName("POST /{baseUrl}/delete/{id}: Повинен видалити об'єкт та перенаправити на /{baseUrl}/list при успіху")
        void testDeleteSuccess() throws Exception
        {
            Long existingId = 1L;
            doNothing().when(getServiceMock()).Delete(existingId);

            getMockMvc().perform(post("/" + getBaseUrl() + "/delete/" + existingId))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/" + getBaseUrl() + "/list"))
                    .andExpect(flash().attributeExists("successMessage"))
                    .andExpect(flash().attribute("successMessage", containsString("успішно видалено")));

            verify(getServiceMock(), times(1)).Delete(existingId);
        }

        @Test
        @DisplayName("POST /{baseUrl}/delete/{id}: Повинен обробити помилку під час видалення об'єкта (наприклад, не знайдено)")
        void testDeleteFailure() throws Exception
        {
            Long nonExistentId = 99L;
            doThrow(new RuntimeException("Помилка видалення: об'єкт не знайдено")).when(getServiceMock()).Delete(nonExistentId);

            getMockMvc().perform(post("/" + getBaseUrl() + "/delete/" + nonExistentId))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/" + getBaseUrl() + "/list"))
                    .andExpect(flash().attributeExists("errorMessage"))
                    .andExpect(flash().attribute("errorMessage", containsString("Помилка видалення")));

            verify(getServiceMock(), times(1)).Delete(nonExistentId);
        }
    }
}