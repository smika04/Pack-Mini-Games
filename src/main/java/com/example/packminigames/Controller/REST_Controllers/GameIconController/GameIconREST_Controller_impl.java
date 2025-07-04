package com.example.packminigames.Controller.REST_Controllers.GameIconController;

import com.example.packminigames.Controller.REST_Controllers.AbstractBasicRESTController;
import com.example.packminigames.Models.DTO.GameIconDTO;
import com.example.packminigames.Service.DAO.GameIconService.GameIconService_impl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gameicons")
public class GameIconREST_Controller_impl extends AbstractBasicRESTController<GameIconService_impl,GameIconDTO> implements IGameIconREST_Controller
{
    public GameIconREST_Controller_impl(GameIconService_impl gameIconServiceImpl) {
        super(gameIconServiceImpl);
    }
}
