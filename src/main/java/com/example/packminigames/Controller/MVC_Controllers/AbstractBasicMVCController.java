package com.example.packminigames.Controller.MVC_Controllers;

import com.example.packminigames.Models.DTO.IIdentifiableDTO;
import com.example.packminigames.Service.DAO.IBasicServiceDAO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractBasicMVCController<SERVICE extends IBasicServiceDAO<DTO>, DTO extends IIdentifiableDTO> implements IMVC_ControllerDAO<DTO>
{
    private final SERVICE service;
    protected final String basePath;
    protected final String dtoAttributeName;
    protected final Class<DTO> dtoClass;

    @GetMapping({"", "/list"})
    @Override
    public String View(@RequestParam(name = "name", required = false) String name, Model model) {
        List<DTO> dtoList = service.GetAll();
        model.addAttribute(dtoAttributeName + "s", dtoList);
        if (name != null && !name.isEmpty()) {
            model.addAttribute("message", "Фільтрація за іменем: " + name);
        }
        return basePath + "/list";
    }

    @GetMapping("/{id}")
    @Override
    public String Details(@PathVariable Long id, Model model) {
        Optional<DTO> optional = service.GetById(id);
        if (optional.isPresent()) {
            model.addAttribute(dtoAttributeName, optional.get());
            return basePath + "/details";
        } else {
            model.addAttribute("errorMessage", "Об'єкт з ID " + id + " не знайдено.");
            return "error";
        }
    }

    @GetMapping("/new")
    @Override
    public String ShowForm(Model model) {
        try {
            model.addAttribute(dtoAttributeName, dtoClass.getDeclaredConstructor().newInstance());
        } catch (Exception e) {

            System.err.println("Помилка при створенні нового DTO: " + e.getMessage());
            model.addAttribute("errorMessage", "Помилка при ініціалізації форми: " + e.getMessage());
            return "error";
        }
        return basePath + "/form";
    }

    @PostMapping("/create")
    @Override
    public String Create(@Valid @ModelAttribute(name = "dtoEntity") DTO dto, RedirectAttributes redirectAttributes) {
        try {
            service.Add(dto);
            redirectAttributes.addFlashAttribute("message", "Об'єкт успішно створено!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Помилка при створенні об'єкта: " + e.getMessage());
        }
        return "redirect:/" + basePath;
    }

    @GetMapping("/edit/{id}")
    @Override
    public String ShowEdit(@PathVariable Long id, Model model) {
        Optional<DTO> optional = service.GetById(id);
        if (optional.isPresent()) {
            model.addAttribute(dtoAttributeName, optional.get());
            return basePath + "/form";
        } else {
            model.addAttribute("errorMessage", "Об'єкт з ID " + id + " не знайдено для редагування.");
            return "redirect:/" + basePath;
        }
    }

    @PostMapping("/update/{id}")
    @Override
    public String Update(@PathVariable Long id, @ModelAttribute(name = "dtoEntity") DTO dto, RedirectAttributes redirectAttributes) {
        dto.setId(id);
        try {
            service.Update(dto);
            redirectAttributes.addFlashAttribute("message", "Об'єкт успішно оновлено!");
        } catch (jakarta.persistence.EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Об'єкт з ID " + id + " не знайдено для оновлення.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Помилка оновлення: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Помилка при оновленні об'єкта: " + e.getMessage());
        }
        return "redirect:/" + basePath;
    }

    @PostMapping("/delete/{id}")
    @Override
    public String DeletePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            service.Delete(id);
            redirectAttributes.addFlashAttribute("message", "Об'єкт успішно видалено!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Об'єкт з ID " + id + " не знайдено для видалення.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Помилка при видаленні об'єкта: " + e.getMessage());
        }
        return "redirect:/" + basePath;
    }

    @Override
    public String Delete(Long id, RedirectAttributes redirectAttributes) {return DeletePost(id, redirectAttributes);}
}
