package com.example.packminigames.Controllers.MVC.DAO.GameController;

import com.example.packminigames.Controllers.MVC.DAO.AbstractControllerMVCDaoTest;
import com.example.packminigames.Controller.MVC_Controllers.GameController.GameMVC_Controller_impl; // Припустімо, що це шлях до вашого контролера
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Service.DAO.GameService.GameService_impl; // Припустімо, що це шлях до вашого сервісу
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameMVC_Controller_impl.class)
@DisplayName("Game MVC Controller DAO Tests")
public class GameControllerMVCDaoTest extends AbstractControllerMVCDaoTest<GameService_impl, GameDTO>
{
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GameService_impl gameService;

    // --- Реалізація абстрактних методів з AbstractControllerMVCDaoTest ---

    @Override
    protected MockMvc getMockMvc() {return mockMvc;}

    @Override
    protected GameService_impl getServiceMock() {return gameService;}

    @Override
    protected String getBaseUrl() {return "game";}

    @Override
    protected String getDtoAttributeName() {return "game";}

    @Override
    protected GameDTO createNewDto()
    {
        return GameDTO.builder()
                .title("New Game Title")
                .description("A fantastic new game.")
                .typeGameName("Arcade")
                .iconData("dummy_icon_data_new".getBytes())
                .records(Collections.emptyList()).build();
    }

    @Override
    protected GameDTO createExistingDto(Long id)
    {
        RecordDTO record1 = RecordDTO.builder()
                .id(100L + id)
                .gameId(id)
                .userId(1L)
                .score(1000)
                .datePlayed(LocalDateTime.now()).build();
        RecordDTO record2 = RecordDTO.builder()
                .id(200L + id)
                .gameId(id)
                .userId(2L)
                .score(800)
                .datePlayed(LocalDateTime.now().minusDays(1)).build();
        List<RecordDTO> records = List.of(record1, record2);

        return GameDTO.builder()
                .id(id)
                .title("Existing Game " + id)
                .description("Description for existing game " + id + ".")
                .typeGameName("Puzzle")
                .iconData(("existing_icon_data_" + id).getBytes())
                .records(records).build();
    }

    @Override
    protected Class<GameDTO> getDtoClass() {return GameDTO.class;}

    // --- Специфічні тести для GameMVC_Controller_impl ---

    @Nested
    @DisplayName("Specific Game Controller Tests")
    class SpecificGameTests
    {
        @Test
        @DisplayName("POST /game/create: Повинен повернути форму з помилками для невалідного GameDTO (порожній заголовок)")
        void testCreateGameWithInvalidTitle() throws Exception
        {
            GameDTO invalidGame = GameDTO.builder()
                    .title("")
                    .description("Some description.")
                    .typeGameName("Adventure")
                    .iconData("icon".getBytes()).build();

            getMockMvc().perform(post("/" + getBaseUrl() + "/create")
                            .flashAttr("dtoEntity", invalidGame))
                    .andExpect(status().isOk())
                    .andExpect(view().name(getBaseUrl() + "/form"))
                    .andExpect(model().attributeHasFieldErrors("dtoEntity", "title"))
                    .andExpect(model().attribute("org.springframework.validation.BindingResult.dtoEntity", isA(BindingResult.class)));

            verify(getServiceMock(), never()).Add(any(GameDTO.class));
        }

        @Test
        @DisplayName("POST /game/create: Повинен повернути форму з помилками для GameDTO без typeGameName (якщо @NotNull або @NotBlank)")
        void testCreateGameWithMissingTypeGameName() throws Exception
        {
            GameDTO missingTypeNameGame = GameDTO.builder()
                    .title("Game Without Type")
                    .description("Description.")
                    .typeGameName(null)
                    .iconData("icon".getBytes()).build();

            getMockMvc().perform(post("/" + getBaseUrl() + "/create")
                            .flashAttr("dtoEntity", missingTypeNameGame))
                    .andExpect(status().isOk())
                    .andExpect(view().name(getBaseUrl() + "/form"))
                    .andExpect(model().attributeHasFieldErrors("dtoEntity", "typeGameName"))
                    .andExpect(model().attribute("org.springframework.validation.BindingResult.dtoEntity", isA(BindingResult.class)));

            verify(getServiceMock(), never()).Add(any(GameDTO.class));
        }
    }
}