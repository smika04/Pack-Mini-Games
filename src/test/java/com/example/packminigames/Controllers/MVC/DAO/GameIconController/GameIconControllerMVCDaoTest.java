package com.example.packminigames.Controllers.MVC.DAO.GameIconController;

import com.example.packminigames.Controllers.MVC.DAO.AbstractControllerMVCDaoTest;
import com.example.packminigames.Controller.MVC_Controllers.GameIconController.GameIconMVC_Controller_impl; // Припустімо, що це шлях до вашого контролера
import com.example.packminigames.Models.DTO.GameIconDTO;
import com.example.packminigames.Service.DAO.GameIconService.GameIconService_impl; // Припустімо, що це шлях до вашого сервісу
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameIconMVC_Controller_impl.class)
@DisplayName("GameIcon MVC Controller DAO Tests")
public class GameIconControllerMVCDaoTest extends AbstractControllerMVCDaoTest<GameIconService_impl, GameIconDTO> {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GameIconService_impl gameIconService;

    // --- Реалізація абстрактних методів з AbstractControllerMVCDaoTest ---

    @Override
    protected MockMvc getMockMvc() {return mockMvc;}

    @Override
    protected GameIconService_impl getServiceMock() {return gameIconService;}

    @Override
    protected String getBaseUrl() {return "gameicon";}

    @Override
    protected String getDtoAttributeName() {return "gameIcon";}

    @Override
    protected GameIconDTO createNewDto()
    {
        return GameIconDTO.builder()
                .name("New Game Icon")
                .iconData("dummy_icon_data".getBytes())
                .contentType("image/png")
                .gameId(1L).build();
    }

    @Override
    protected GameIconDTO createExistingDto(Long id)
    {
        return GameIconDTO.builder()
                .id(id)
                .name("Existing Game Icon " + id)
                .iconData(("existing_icon_data_" + id).getBytes())
                .contentType("image/jpeg")
                .gameId(id % 5 + 1L).build();
    }

    @Override
    protected Class<GameIconDTO> getDtoClass() {return GameIconDTO.class;}

    // --- Специфічні тести для GameIconMVC_Controller_impl ---

    @Nested
    @DisplayName("Specific GameIcon Controller Tests")
    class SpecificGameIconTests
    {
        @Test
        @DisplayName("POST /gameicon/create: Повинен повернути форму з помилками для невалідного GameIconDTO (порожнє ім'я)")
        void testCreateGameIconWithInvalidName() throws Exception
        {
            GameIconDTO invalidGameIcon = GameIconDTO.builder()
                    .name("")
                    .iconData("some_data".getBytes())
                    .contentType("image/png")
                    .gameId(1L).build();

            getMockMvc().perform(post("/" + getBaseUrl() + "/create")
                            .flashAttr("dtoEntity", invalidGameIcon))
                    .andExpect(status().isOk())
                    .andExpect(view().name(getBaseUrl() + "/form"))
                    .andExpect(model().attributeHasFieldErrors("dtoEntity", "name"))
                    .andExpect(model().attribute("org.springframework.validation.BindingResult.dtoEntity", isA(BindingResult.class)));

            verify(getServiceMock(), never()).Add(any(GameIconDTO.class));
        }

        @Test
        @DisplayName("POST /gameicon/create: Повинен повернути форму з помилками для GameIconDTO без iconData (якщо @NotNull)")
        void testCreateGameIconWithMissingIconData() throws Exception
        {
            GameIconDTO missingIconData = GameIconDTO.builder()
                    .name("Icon Missing Data")
                    .iconData(null)
                    .contentType("image/png")
                    .gameId(2L).build();

            getMockMvc().perform(post("/" + getBaseUrl() + "/create")
                            .flashAttr("dtoEntity", missingIconData))
                    .andExpect(status().isOk())
                    .andExpect(view().name(getBaseUrl() + "/form"))
                    .andExpect(model().attributeHasFieldErrors("dtoEntity", "iconData"))
                    .andExpect(model().attribute("org.springframework.validation.BindingResult.dtoEntity", isA(BindingResult.class)));

            verify(getServiceMock(), never()).Add(any(GameIconDTO.class));
        }

        @Test
        @DisplayName("POST /gameicon/create: Повинен повернути форму з помилками для GameIconDTO без gameId (якщо @NotNull)")
        void testCreateGameIconWithMissingGameId() throws Exception
        {
            GameIconDTO missingGameId = GameIconDTO.builder()
                    .name("Icon Missing Game ID")
                    .iconData("some_data".getBytes())
                    .contentType("image/png")
                    .gameId(null).build();

            getMockMvc().perform(post("/" + getBaseUrl() + "/create")
                            .flashAttr("dtoEntity", missingGameId))
                    .andExpect(status().isOk())
                    .andExpect(view().name(getBaseUrl() + "/form"))
                    .andExpect(model().attributeHasFieldErrors("dtoEntity", "gameId"))
                    .andExpect(model().attribute("org.springframework.validation.BindingResult.dtoEntity", isA(BindingResult.class)));

            verify(getServiceMock(), never()).Add(any(GameIconDTO.class));
        }
    }
}