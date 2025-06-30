package com.example.packminigames.Controller.MVC_Controllers.GameIconController;

import com.example.packminigames.Models.DTO.GameIconDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/game-icons")
public class GameIconMVC_Controller_impl implements IGameIconMVC_Controller{

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
    public String Create(GameIconDTO dto, RedirectAttributes redirectAttributes) {
        return "";
    }

    @Override
    public String ShowEdit(Long id, Model model) {
        return "";
    }

    @Override
    public String Update(Long id, GameIconDTO dto, RedirectAttributes redirectAttributes) {
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
