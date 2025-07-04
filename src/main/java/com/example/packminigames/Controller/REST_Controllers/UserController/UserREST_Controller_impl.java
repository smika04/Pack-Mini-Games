package com.example.packminigames.Controller.REST_Controllers.UserController;

import com.example.packminigames.Controller.REST_Controllers.AbstractBasicRESTController;
import com.example.packminigames.Models.DTO.UserDTO;
import com.example.packminigames.Service.DAO.UserService.UserService_impl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserREST_Controller_impl extends AbstractBasicRESTController<UserService_impl, UserDTO> implements IUserREST_Controller
{
    public UserREST_Controller_impl(UserService_impl userService) {
        super(userService);
    }
}
