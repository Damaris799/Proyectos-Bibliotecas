package com.Esfe.Biblioteca.Controladores;

import com.Esfe.Biblioteca.Entidades.Lector;
import com.Esfe.Biblioteca.Entidades.LectorLibro;
import com.Esfe.Biblioteca.Entidades.Libro;
import com.Esfe.Biblioteca.Servicios.Interfaces.ILectorLibroServices;
import com.Esfe.Biblioteca.Servicios.Interfaces.ILectorServices;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/lectorLibros")
public class LectorLibroController {

    @Autowired
    private ILectorLibroServices lectorLibroServices;

    @Autowired
    private ILectorServices lectorServices;

    @Autowired
    private ILibroServices libroServices;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<LectorLibro> lectorLibros = lectorLibroServices.buscarTodosPaginados(pageable);
        model.addAttribute("lectorLibros", lectorLibros);

        int totalPage = lectorLibros.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "lectorLibro/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("lectorLibro", new LectorLibro());
        model.addAttribute("lectores", lectorServices.obtenerTodos());
        model.addAttribute("libros", libroServices.obtenerTodos());
        return "lectorLibro/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer lectorId,
                       @RequestParam Integer libroId,
                       @RequestParam String diaPrestamo,
                       @RequestParam String diaDevolucion,
                       RedirectAttributes attributes) {

        Lector lector = lectorServices.buscarPorId(lectorId).orElse(null);
        Libro libro = libroServices.buscarPorId(libroId).orElse(null);

        if (lector != null && libro != null) {
            LectorLibro lectorLibro = new LectorLibro();
            lectorLibro.setLector(lector);
            lectorLibro.setLibro(libro);
            lectorLibro.setDiaPrestamo(LocalDate.parse(diaPrestamo));
            lectorLibro.setDiaDevolucion(LocalDate.parse(diaDevolucion));
            lectorLibroServices.crearOeditar(lectorLibro);
            attributes.addFlashAttribute("msg", "Lector libro creado.");
        } else {
            attributes.addFlashAttribute("msg", "Lector o Libro no encontrado.");
        }

        return "redirect:/lectorLibros";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        LectorLibro lectorLibro = lectorLibroServices.buscarPorId(id).orElse(null);
        model.addAttribute("lectorLibro", lectorLibro);
        return "lectorLibro/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        LectorLibro lectorLibro = lectorLibroServices.buscarPorId(id).orElse(null);
        model.addAttribute("lectores", lectorServices.obtenerTodos());
        model.addAttribute("libros", libroServices.obtenerTodos());
        model.addAttribute("lectorLibro", lectorLibro);
        return "lectorLibro/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id,
                         @RequestParam Integer lectorId,
                         @RequestParam Integer libroId,
                         @RequestParam String diaPrestamo,
                         @RequestParam String diaDevolucion,
                         RedirectAttributes attributes) {

        Lector lector = lectorServices.buscarPorId(lectorId).orElse(null);
        Libro libro = libroServices.buscarPorId(libroId).orElse(null);

        if (lector != null && libro != null) {
            LectorLibro lectorLibro = lectorLibroServices.buscarPorId(id).orElse(null);

            if (lectorLibro != null) {
                lectorLibro.setLector(lector);
                lectorLibro.setLibro(libro);
                lectorLibro.setDiaPrestamo(LocalDate.parse(diaPrestamo));
                lectorLibro.setDiaDevolucion(LocalDate.parse(diaDevolucion));

                lectorLibroServices.crearOeditar(lectorLibro);
                attributes.addFlashAttribute("msg", "Asignación modificada correctamente.");
            } else {
                attributes.addFlashAttribute("msg", "Lector-Libro no encontrado.");
            }
        } else {
            attributes.addFlashAttribute("msg", "Lector o Libro no encontrado.");
        }

        return "redirect:/lectorLibros";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        LectorLibro lectorLibro = lectorLibroServices.buscarPorId(id).orElse(null);
        model.addAttribute("lectorLibro", lectorLibro);
        return "lectorLibro/delete";
    }

    @PostMapping("/delete")
    public String delete(LectorLibro lectorLibro, RedirectAttributes attributes) {
        lectorLibroServices.eliminarPorId(lectorLibro.getId());
        attributes.addFlashAttribute("msg", "Asignación eliminada correctamente.");
        return "redirect:/lectorLibros";
    }
}
