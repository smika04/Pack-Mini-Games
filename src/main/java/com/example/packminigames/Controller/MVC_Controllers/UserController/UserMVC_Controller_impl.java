package com.example.packminigames.Controller.MVC_Controllers.UserController;

import com.example.packminigames.Controller.MVC_Controllers.AbstractBasicMVCController;
import com.example.packminigames.Models.DTO.UserDTO;
import com.example.packminigames.Service.DAO.UserService.UserService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserMVC_Controller_impl extends AbstractBasicMVCController<UserService_impl, UserDTO> implements IUserMVC_Controller
{
    public UserMVC_Controller_impl(UserService_impl userServiceImpl, String basePath, String dtoAttributeName, Class<UserDTO> userDTOClass) {
        super(userServiceImpl, basePath, dtoAttributeName, userDTOClass);
    }
}
