package com.Esfe.Biblioteca.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Esfe.Biblioteca.Servicios.Interfaces.ILectorLibroServices;
import com.Esfe.Biblioteca.Servicios.Interfaces.ILectorServices;
import com.Esfe.Biblioteca.Servicios.Interfaces.ILibroServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class HomeController {

    private final ILibroServices libroServices;
    private final ILectorServices lectorServices;
    private final ILectorLibroServices lectorLibroServices;

    @Autowired
    public HomeController(ILibroServices libroServices, ILectorServices lectorServices, ILectorLibroServices lectorLibroServices) {
        this.libroServices = libroServices;
        this.lectorServices = lectorServices;
        this.lectorLibroServices = lectorLibroServices;
    }

    @GetMapping
    public String index(Model model) {
        int totalLibros = libroServices.obtenerTotalLibros();
        int totalLectores = lectorServices.obtenerTotalLectores();
        int totalPrestamos = lectorLibroServices.obtenerTotalPrestamos();

        model.addAttribute("totalLibros", totalLibros);
        model.addAttribute("totalLectores", totalLectores);
        model.addAttribute("totalPrestamos", totalPrestamos);

        return "home/index"; // Asegúrate de que esta sea la ruta correcta de tu plantilla
    }
}
