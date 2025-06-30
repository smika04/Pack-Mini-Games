package com.example.packminigames.Controller.MVC_Controllers.GameController;

import com.example.packminigames.Models.DTO.GameDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/game")
public class GameMVC_Controller_impl implements IGameMVC_Controller{

    @Override
    public String View(String name, Model model) {
        return "";
    }

    @Override
    public String Details(Long id, Model model) {
        return "";
    }

    @Override
    public String ShowForm(Model model) {
        return "";
    }

    @Override
    public String Create(GameDTO dto, RedirectAttributes redirectAttributes) {
        return "";
    }

    @Override
    public String ShowEdit(Long id, Model model) {
        return "";
    }

    @Override
    public String Update(Long id, GameDTO dto, RedirectAttributes redirectAttributes) {
        return "";
    }

    @Override
    public String Delete(Long id, RedirectAttributes redirectAttributes) {
        return "";
    }

    @Override
    public String DeletePost(Long id, RedirectAttributes redirectAttributes) {
        return "";
    }
}
