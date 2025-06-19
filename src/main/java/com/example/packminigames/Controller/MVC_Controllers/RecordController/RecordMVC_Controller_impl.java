package com.example.packminigames.Controller.MVC_Controllers.RecordController;

import com.example.packminigames.Models.DTO.RecordDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/record")
public class RecordMVC_Controller_impl implements IRecordMVC_Controller{
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
    public String Create(RecordDTO dto, RedirectAttributes redirectAttributes) {
        return "";
    }

    @Override
    public String ShowEdit(Long id, Model model) {
        return "";
    }

    @Override
    public String Update(Long id, RecordDTO dto, RedirectAttributes redirectAttributes) {
        return "";
    }

    @Override
    public String Delete(Long id, RedirectAttributes redirectAttributes) {
        return "";
    }
}
