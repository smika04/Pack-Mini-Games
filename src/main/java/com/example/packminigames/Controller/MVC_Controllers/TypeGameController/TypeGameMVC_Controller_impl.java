package com.example.packminigames.Controller.MVC_Controllers.TypeGameController;

import com.example.packminigames.Controller.MVC_Controllers.AbstractBasicMVCController;
import com.example.packminigames.Models.DTO.TypeGameDTO;
import com.example.packminigames.Service.DAO.TypeGameService.TypeGameService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/typeGame")
public class TypeGameMVC_Controller_impl extends AbstractBasicMVCController<TypeGameService_impl, TypeGameDTO> implements ITypeGameMVC_Controller
{
    public TypeGameMVC_Controller_impl(TypeGameService_impl typeGameServiceImpl, String basePath, String dtoAttributeName, Class<TypeGameDTO> typeGameDTOClass) {
        super(typeGameServiceImpl, basePath, dtoAttributeName, typeGameDTOClass);
    }
}
