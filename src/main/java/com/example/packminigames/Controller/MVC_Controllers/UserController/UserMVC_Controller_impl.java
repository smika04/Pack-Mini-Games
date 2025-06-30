package com.example.packminigames.Controller.MVC_Controllers.UserController;

import com.example.packminigames.Models.DTO.UserDTO;
import com.example.packminigames.Service.DAO.UserService.UserService_impl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserMVC_Controller_impl implements IUserMVC_Controller
{
    private final UserService_impl userService;


    @GetMapping({"", "/list"})
    @Override
    public String View(@RequestParam(name = "name", required = false) String name, Model model) {
        List<UserDTO> users = userService.GetAll();
        model.addAttribute("users", users);
        if (name != null && !name.isEmpty()) {
            model.addAttribute("message", "Фільтрація за іменем: " + name);
        }
        return "user/list";
    }

    @GetMapping("/{id}")
    @Override
    public String Details(@PathVariable Long id, Model model) {
        Optional<UserDTO> userOptional = userService.GetById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "user/details";
        } else {
            model.addAttribute("errorMessage", "Користувача з ID " + id + " не знайдено.");
            return "error";
        }
    }

    @GetMapping("/new")
    @Override
    public String ShowForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user/form";
    }

    @PostMapping("/create")
    @Override
    public String Create(@Valid @ModelAttribute("user") UserDTO dto, RedirectAttributes redirectAttributes) {
        try {
            userService.Add(dto);
            redirectAttributes.addFlashAttribute("message", "Користувача успішно створено!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Помилка при створенні користувача: " + e.getMessage());
        }
        return "redirect:/user";
    }


    @GetMapping("/edit/{id}")
    @Override
    public String ShowEdit(@PathVariable Long id, Model model) {
        Optional<UserDTO> userOptional = userService.GetById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "user/form";
        } else {
            model.addAttribute("errorMessage", "Користувача з ID " + id + " не знайдено для редагування.");
            return "redirect:/user";
        }
    }

    @PostMapping("/update/{id}")
    @Override
    public String Update(@PathVariable Long id, @ModelAttribute("user") UserDTO dto, RedirectAttributes redirectAttributes) {
        dto.setId(id);
        try {
            userService.Update(dto);
            redirectAttributes.addFlashAttribute("message", "Користувача успішно оновлено!");
        } catch (jakarta.persistence.EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Користувача з ID " + id + " не знайдено для оновлення.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Помилка оновлення: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Помилка при оновленні користувача: " + e.getMessage());
        }
        return "redirect:/user";
    }

    @PostMapping("/delete/{id}")
    @Override
    public String DeletePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.Delete(id);
            redirectAttributes.addFlashAttribute("message", "Користувача успішно видалено!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Користувача з ID " + id + " не знайдено для видалення.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Помилка при видаленні користувача: " + e.getMessage());
        }
        return "redirect:/user";
    }

    @Override
    public String Delete(Long id, RedirectAttributes redirectAttributes) {
        return DeletePost(id, redirectAttributes);
    }

}
