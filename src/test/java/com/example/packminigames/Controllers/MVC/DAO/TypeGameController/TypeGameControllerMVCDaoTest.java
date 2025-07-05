package com.example.packminigames.Controllers.MVC.DAO.TypeGameController;

import com.example.packminigames.Controllers.MVC.DAO.AbstractControllerMVCDaoTest;
import com.example.packminigames.Controller.MVC_Controllers.TypeGameController.TypeGameMVC_Controller_impl; // Припустімо, що це шлях до вашого контролера
import com.example.packminigames.Models.DTO.TypeGameDTO;
import com.example.packminigames.Service.DAO.TypeGameService.TypeGameService_impl; // Припустімо, що це шлях до вашого сервісу
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

@WebMvcTest(TypeGameMVC_Controller_impl.class)
@DisplayName("TypeGame MVC Controller DAO Tests")
public class TypeGameControllerMVCDaoTest extends AbstractControllerMVCDaoTest<TypeGameService_impl, TypeGameDTO> {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TypeGameService_impl typeGameService;

    @Override
    protected MockMvc getMockMvc() {return mockMvc;}

    @Override
    protected TypeGameService_impl getServiceMock() {return typeGameService;}

    @Override
    protected String getBaseUrl() {return "typeGame";}

    @Override
    protected String getDtoAttributeName() {return "typeGame";}

    @Override
    protected TypeGameDTO createNewDto() {return TypeGameDTO.builder().name("New Type Game").build();}

    @Override
    protected TypeGameDTO createExistingDto(Long id) {return TypeGameDTO.builder().id(id).name("Existing Type Game " + id).build();}

    @Override
    protected Class<TypeGameDTO> getDtoClass() {return TypeGameDTO.class;}

    // --- Специфічні тести для TypeGameMVC_Controller_impl ---

    @Nested
    @DisplayName("Specific TypeGame Controller Tests")
    class SpecificTypeGameTests
    {
        @Test
        @DisplayName("POST /typeGame/create: Повинен повернути форму з помилками для невалідного TypeGameDTO (порожнє ім'я)")
        void testCreateTypeGameWithInvalidData() throws Exception
        {
            TypeGameDTO invalidTypeGame = TypeGameDTO.builder().name("").build();

            getMockMvc().perform(post("/" + getBaseUrl() + "/create")
                            .flashAttr("dtoEntity", invalidTypeGame))
                    .andExpect(status().isOk())
                    .andExpect(view().name(getBaseUrl() + "/form"))
                    .andExpect(model().attributeHasFieldErrors("dtoEntity", "name"))
                    .andExpect(model().attribute("org.springframework.validation.BindingResult.dtoEntity", isA(BindingResult.class)));

            verify(getServiceMock(), never()).Add(any(TypeGameDTO.class));
        }
    }
}
