package com.example.packminigames.Controllers.REST.DAO.UserController;

import com.example.packminigames.Controller.REST_Controllers.UserController.UserREST_Controller_impl;
import com.example.packminigames.Controllers.REST.DAO.AbstractControllerRESTDaoTest;
import com.example.packminigames.Models.DTO.UserDTO;
import com.example.packminigames.Service.DAO.UserService.UserService_impl;
import com.example.packminigames.Service.DAO.UserService.IUserService;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;

@DisplayName("User REST Controller Integration Tests")
@WebMvcTest(UserREST_Controller_impl.class)
public class UserControllerRESTDaoTest extends AbstractControllerRESTDaoTest<UserDTO, IUserService>
{
    @MockitoBean
    private UserService_impl serviceImpl;

    @Override
    protected IUserService getService() {
        return serviceImpl;
    }

    @Override
    protected String getBaseUrl() {
        return "/api/users";
    }

    @Override
    protected UserDTO createDTO(Long id, String identifier) {

        return UserDTO.builder()
                .id(id)
                .username(identifier)
                .birthday(LocalDate.of(1990, 1, 1))
                .build();
    }
}

