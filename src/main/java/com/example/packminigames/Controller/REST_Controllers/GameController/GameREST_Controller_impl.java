package com.example.packminigames.Controller.REST_Controllers.GameController;

import com.example.packminigames.Controller.REST_Controllers.AbstractBasicRESTController;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Service.DAO.GameService.GameService_impl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameREST_Controller_impl extends AbstractBasicRESTController<GameService_impl,GameDTO> implements IGameREST_Controller
{
    public GameREST_Controller_impl(GameService_impl gameServiceImpl) {
        super(gameServiceImpl);
    }
}
