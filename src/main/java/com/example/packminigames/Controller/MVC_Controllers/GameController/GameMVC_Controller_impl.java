package com.example.packminigames.Controller.MVC_Controllers.GameController;

import com.example.packminigames.Controller.MVC_Controllers.AbstractBasicMVCController;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Service.DAO.GameService.GameService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class GameMVC_Controller_impl extends AbstractBasicMVCController<GameService_impl, GameDTO> implements IGameMVC_Controller
{
    public GameMVC_Controller_impl(GameService_impl gameServiceImpl, String basePath, String dtoAttributeName, Class<GameDTO> gameDTOClass) {
        super(gameServiceImpl, basePath, dtoAttributeName, gameDTOClass);
    }
}
