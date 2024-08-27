package com.Esfe.Biblioteca.Controladores;

import com.Esfe.Biblioteca.Entidades.Lector;
import com.Esfe.Biblioteca.Servicios.Interfaces.ILectorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/Lectores")
public class LectorController {

    @Autowired
    private ILectorServices lectorServices;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Lector> lectores = lectorServices.buscarTodosPaginados(pageable);
        model.addAttribute("lectores", lectores);

        int totalPages = lectores.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "lector/index";
    }

    @GetMapping("/create")
    public String create(Lector lector) {
        return "lector/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Lector lector, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("lector", lector);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "lector/create";
        }
        lectorServices.crearOEditar(lector);
        attributes.addFlashAttribute("msg", "Lector creado correctamente");
        return "redirect:/Lectores";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Lector lector = lectorServices.buscarPorId(id).orElse(null);
        model.addAttribute("lector", lector);
        return "lector/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Lector lector = lectorServices.buscarPorId(id).orElse(null);
        model.addAttribute("lector", lector);
        return "lector/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Lector lector = lectorServices.buscarPorId(id).orElse(null);
        model.addAttribute("lector", lector);
        return "lector/delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Lector lector, RedirectAttributes attributes) {
        lectorServices.eliminarPorId(lector.getId());
        attributes.addFlashAttribute("msg", "Lector eliminado correctamente.");
        return "redirect:/Lectores";
    }
}
