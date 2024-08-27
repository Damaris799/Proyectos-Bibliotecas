package com.Esfe.Biblioteca.Controladores;

import com.Esfe.Biblioteca.Entidades.Libro;
import com.Esfe.Biblioteca.Servicios.Interfaces.ILibroServices;
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
@RequestMapping("/Libros")
public class LibroController {

    @Autowired
    private ILibroServices libroServices;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Libro> libros = libroServices.buscarTodosPaginados(pageable);
        model.addAttribute("libros", libros);

        int totalPages = libros.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "libro/index";
    }

    @GetMapping("/create")
    public String create(Libro libro) {
        return "libro/create";
    }

    @PostMapping("/save")
    public String save(Libro libro, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("libro", libro);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "libro/create";
        }
        libroServices.crearOEditar(libro);
        attributes.addFlashAttribute("msg", "Libro creado correctamente");
        return "redirect:/Libros";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Libro libro = libroServices.buscarPorId(id).orElse(null);
        model.addAttribute("libro", libro);
        return "libro/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Libro libro = libroServices.buscarPorId(id).orElse(null);
        model.addAttribute("libro", libro);
        return "libro/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Libro libro = libroServices.buscarPorId(id).orElse(null);
        model.addAttribute("libro", libro);
        return "libro/delete";
    }

    @PostMapping("/delete")
    public String delete(Libro libro, RedirectAttributes attributes) {
        libroServices.eliminarPorId(libro.getId());
        attributes.addFlashAttribute("msg", "Libro eliminado correctamente.");
        return "redirect:/Libros";
    }
}

