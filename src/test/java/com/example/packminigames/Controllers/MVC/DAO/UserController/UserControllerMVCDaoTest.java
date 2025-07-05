package com.example.packminigames.Controllers.MVC.DAO.UserController;

import com.example.packminigames.Controllers.MVC.DAO.AbstractControllerMVCDaoTest;
import com.example.packminigames.Controller.MVC_Controllers.UserController.UserMVC_Controller_impl;
import com.example.packminigames.Models.DTO.UserDTO;
import com.example.packminigames.Service.DAO.UserService.UserService_impl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserMVC_Controller_impl.class)
@DisplayName("User MVC Controller DAO Tests")
public class UserControllerMVCDaoTest extends AbstractControllerMVCDaoTest<UserService_impl, UserDTO> {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService_impl userService;

    // --- Реалізація абстрактних методів з AbstractControllerMVCDaoTest ---

    @Override
    protected MockMvc getMockMvc() {return mockMvc;}

    @Override
    protected UserService_impl getServiceMock() {return userService;}

    @Override
    protected String getBaseUrl() {return "user";}

    @Override
    protected String getDtoAttributeName() {return "user";}

    @Override
    protected UserDTO createNewDto() {return UserDTO.builder().username("NewUser").birthday(LocalDate.of(2000, 1, 1)).build();}

    @Override
    protected UserDTO createExistingDto(Long id) {return UserDTO.builder().id(id).username("User" + id).birthday(LocalDate.of(1990, 5, 10).plusDays(id)).build();}

    @Override
    protected Class<UserDTO> getDtoClass() {return UserDTO.class;}

    // --- Спеціальні тести для UserMVC_Controller_impl ---

    @Nested
    @DisplayName("Specific User Controller Tests")
    class SpecificUserTests
    {
        @Test
        @DisplayName("POST /user/create: Повинен повернути форму з помилками для невалідного UserDTO (наприклад, пусте ім'я користувача)")
        void testCreateUserWithInvalidData() throws Exception
        {
            UserDTO invalidUser = UserDTO.builder().username("").birthday(LocalDate.of(2000, 1, 1)).build();

            getMockMvc().perform(post("/" + getBaseUrl() + "/create")
                            .flashAttr("dtoEntity", invalidUser))
                    .andExpect(status().isOk())
                    .andExpect(view().name(getBaseUrl() + "/form"))
                    .andExpect(model().attributeHasFieldErrors("dtoEntity", "username"))
                    .andExpect(model().attribute("org.springframework.validation.BindingResult.dtoEntity", isA(BindingResult.class)));

            verify(getServiceMock(), never()).Add(any(UserDTO.class));
        }
    }
}