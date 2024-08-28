package com.Esfe.Biblioteca.Controladores;

import com.Esfe.Biblioteca.Entidades.Rol;
import com.Esfe.Biblioteca.Entidades.Usuario;
import com.Esfe.Biblioteca.Servicios.Interfaces.IRolService;
import com.Esfe.Biblioteca.Servicios.Interfaces.IUsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService; // Servicio para manejar roles

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 1
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Usuario> usuarios = usuarioService.buscarTodosPaginados(pageable);
        model.addAttribute("usuarios", usuarios);
        int totalPages = usuarios.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "usuario/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolService.obtenerTodos()); // Pasar roles a la vista
        return "usuario/create";
    }

    @PostMapping("/save")
    public String save(Usuario usuario,
                       @RequestParam List<Integer> selectedRoles,
                       BindingResult result,
                       Model model,
                       RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("roles", rolService.obtenerTodos());
            attributes.addFlashAttribute("error", "No se puede guardar debido a un error");
            return "usuario/create";
        }

        // Asignar los roles seleccionados al usuario
        List<Rol> roles = selectedRoles.stream()
                .map(rolService::buscarPorId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        usuario.setRoles(roles);
        usuario.setStatus(1);
        usuario.setRoles(roles);

        boolean isEdit = usuario.getId() != null && usuario.getId() > 0;
        usuarioService.crearOEditar(usuario);
        if (isEdit) {
            attributes.addFlashAttribute("msg", "Editado Correctamente");
        } else {
            attributes.addFlashAttribute("msg", "Creado Correctamente");
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id).orElse(null);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        } else {
            model.addAttribute("error", "Usuario no encontrado");
        }
        return "usuario/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id).orElse(null);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("roles", rolService.obtenerTodos()); // Pasar roles a la vista
        } else {
            model.addAttribute("error", "Usuario no encontrado");
            return "redirect:/usuarios";
        }
        return "usuario/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id).orElse(null);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        } else {
            model.addAttribute("error", "Usuario no encontrado");
            return "redirect:/usuarios";
        }
        return "usuario/delete";
    }

    @PostMapping("/delete")
    public String delete(Usuario usuario, RedirectAttributes attributes) {
        usuarioService.eliminarPorId(usuario.getId());
        attributes.addFlashAttribute("msg", "Usuario eliminado correctamente");
        return "redirect:/usuarios";
    }
}
