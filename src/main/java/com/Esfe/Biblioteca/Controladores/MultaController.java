package com.Esfe.Biblioteca.Controladores;

import com.Esfe.Biblioteca.Entidades.Lector;
import com.Esfe.Biblioteca.Entidades.Libro;
import com.Esfe.Biblioteca.Entidades.Multa;
import com.Esfe.Biblioteca.Servicios.Interfaces.ILectorServices;
import com.Esfe.Biblioteca.Servicios.Interfaces.ILibroServices;
import com.Esfe.Biblioteca.Servicios.Interfaces.IMultaServices;
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
@RequestMapping("/multas")
public class MultaController {

    @Autowired
    private IMultaServices multaServices;

    @Autowired
    private ILectorServices lectorServices;

    @Autowired
    private ILibroServices libroServices;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Multa> multas = multaServices.buscarTodosPaginados(pageable);
        model.addAttribute("multas", multas);

        int totalPage = multas.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "multa/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("multa", new Multa());
        model.addAttribute("lectores", lectorServices.obtenerTodos());
        model.addAttribute("libros", libroServices.obtenerTodos());
        return "multa/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer lectorId,
                       @RequestParam Integer libroId,
                       @RequestParam String fechaMulta,
                       @RequestParam Double monto,
                       @RequestParam String estado,
                       RedirectAttributes attributes) {

        Lector lector = lectorServices.buscarPorId(lectorId).orElse(null);
        Libro libro = libroServices.buscarPorId(libroId).orElse(null);

        if (lector != null && libro != null) {
            Multa multa = new Multa();
            multa.setLector(lector);
            multa.setLibro(libro);
            multa.setFechaMulta(LocalDate.parse(fechaMulta));
            multa.setMonto(monto);
            multa.setEstado(estado);
            multaServices.crearOeditar(multa);
            attributes.addFlashAttribute("msg", "Multa creada.");
        } else {
            attributes.addFlashAttribute("msg", "Lector o Libro no encontrado.");
        }

        return "redirect:/multas";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Multa multa = multaServices.buscarPorId(id).orElse(null);
        model.addAttribute("multa", multa);
        return "multa/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Multa multa = multaServices.buscarPorId(id).orElse(null);
        model.addAttribute("lectores", lectorServices.obtenerTodos());
        model.addAttribute("libros", libroServices.obtenerTodos());
        model.addAttribute("multa", multa);
        return "multa/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id,
                         @RequestParam Integer lectorId,
                         @RequestParam Integer libroId,
                         @RequestParam String fechaMulta,
                         @RequestParam Double monto,
                         @RequestParam String estado,
                         RedirectAttributes attributes) {

        Lector lector = lectorServices.buscarPorId(lectorId).orElse(null);
        Libro libro = libroServices.buscarPorId(libroId).orElse(null);

        if (lector != null && libro != null) {
            Multa multa = multaServices.buscarPorId(id).orElse(null);

            if (multa != null) {
                multa.setLector(lector);
                multa.setLibro(libro);
                multa.setFechaMulta(LocalDate.parse(fechaMulta));
                multa.setMonto(monto);
                multa.setEstado(estado);

                multaServices.crearOeditar(multa);
                attributes.addFlashAttribute("msg", "Multa modificada correctamente.");
            } else {
                attributes.addFlashAttribute("msg", "Multa no encontrada.");
            }
        } else {
            attributes.addFlashAttribute("msg", "Lector o Libro no encontrado.");
        }

        return "redirect:/multas";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Multa multa = multaServices.buscarPorId(id).orElse(null);
        model.addAttribute("multa", multa);
        return "multa/delete";
    }

    @PostMapping("/delete")
    public String delete(Multa multa, RedirectAttributes attributes) {
        multaServices.eliminarPorId(multa.getId());
        attributes.addFlashAttribute("msg", "Multa eliminada correctamente.");
        return "redirect:/multas";
    }
}
