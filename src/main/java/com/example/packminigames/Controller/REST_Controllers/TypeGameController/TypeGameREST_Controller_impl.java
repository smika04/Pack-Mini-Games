package com.example.packminigames.Controller.REST_Controllers.TypeGameController;

import com.example.packminigames.Controller.REST_Controllers.AbstractBasicRESTController;
import com.example.packminigames.Models.DTO.TypeGameDTO;
import com.example.packminigames.Service.DAO.TypeGameService.TypeGameService_impl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/typeGame")
public class TypeGameREST_Controller_impl extends AbstractBasicRESTController<TypeGameService_impl, TypeGameDTO> implements ITypeGameREST_Controller
{
    public TypeGameREST_Controller_impl(TypeGameService_impl typeGameServiceImpl) {
        super(typeGameServiceImpl);
    }
}
