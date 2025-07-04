package com.example.packminigames.Controller.MVC_Controllers.GameIconController;

import com.example.packminigames.Controller.MVC_Controllers.AbstractBasicMVCController;
import com.example.packminigames.Models.DTO.GameIconDTO;
import com.example.packminigames.Service.DAO.GameIconService.GameIconService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game-icons")
public class GameIconMVC_Controller_impl extends AbstractBasicMVCController<GameIconService_impl, GameIconDTO> implements IGameIconMVC_Controller
{
    public GameIconMVC_Controller_impl(GameIconService_impl gameIconServiceImpl, String basePath, String dtoAttributeName, Class<GameIconDTO> gameIconDTOClass) {
        super(gameIconServiceImpl, basePath, dtoAttributeName, gameIconDTOClass);
    }
}
