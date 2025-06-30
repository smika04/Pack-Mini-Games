package com.example.packminigames.Controller.MVC_Controllers;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IMVC_ControllerDAO<D>
{
    String View(@RequestParam(name = "name", required = false) String name, Model model);
    String Details(@PathVariable Long id, Model model);
    String ShowForm(Model model);
    String Create(@Valid @ModelAttribute D dto, RedirectAttributes redirectAttributes);
    String ShowEdit(@PathVariable Long id, Model model);
    String Update(@PathVariable Long id, @ModelAttribute D dto, RedirectAttributes redirectAttributes);
    String Delete(@PathVariable Long id, RedirectAttributes redirectAttributes);
    String DeletePost(@PathVariable Long id, RedirectAttributes redirectAttributes);
}