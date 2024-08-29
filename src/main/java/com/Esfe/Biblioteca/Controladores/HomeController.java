package com.Esfe.Biblioteca.Controladores;

import com.Esfe.Biblioteca.Servicios.Interfaces.ILectorLibroServices;
import com.Esfe.Biblioteca.Servicios.Interfaces.ILectorServices;
import com.Esfe.Biblioteca.Servicios.Interfaces.ILibroServices;
import com.Esfe.Biblioteca.Servicios.Interfaces.IMultaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final ILibroServices libroServices;
    private final ILectorServices lectorServices;
    private final ILectorLibroServices lectorLibroServices;
    private final IMultaServices multaServices;

    @Autowired
    public HomeController(ILibroServices libroServices, ILectorServices lectorServices, ILectorLibroServices lectorLibroServices, IMultaServices multaServices) {
        this.libroServices = libroServices;
        this.lectorServices = lectorServices;
        this.lectorLibroServices = lectorLibroServices;
        this.multaServices = multaServices;
    }

    @GetMapping
    public String index(Model model) {
        int totalLibros = libroServices.obtenerTotalLibros();
        int totalLectores = lectorServices.obtenerTotalLectores();
        int totalPrestamos = lectorLibroServices.obtenerTotalPrestamos();
        int totalMultas = multaServices.obtenerTotalMultas();

        model.addAttribute("totalLibros", totalLibros);
        model.addAttribute("totalLectores", totalLectores);
        model.addAttribute("totalPrestamos", totalPrestamos);
        model.addAttribute("totalMultas", totalMultas);

        return "home/index";
    }
}