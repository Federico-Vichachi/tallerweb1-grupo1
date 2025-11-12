package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioPerfil;
import com.tallerwebi.dominio.ServicioPublicacion;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.Provincias;
import com.tallerwebi.dominio.Publicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ControladorPerfil {

    ServicioPerfil servicioPerfil;
    ServicioPublicacion servicioPublicacion;

    @Autowired
    private ServletContext servletContext;

    // Directorio externo de uploads definido también en SpringWebConfig
    private static final Path UPLOADS_IMAGES_DIR = Paths.get(System.getProperty("user.dir"), "uploads", "images");

    @Autowired
    public ControladorPerfil(ServicioPerfil servicioPerfil, ServicioPublicacion servicioPublicacion) {
        this.servicioPerfil = servicioPerfil;
        this.servicioPublicacion = servicioPublicacion;
    }

    @RequestMapping(path = "/perfil", method = RequestMethod.GET)
    public ModelAndView irAlPerfil(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return new ModelAndView("redirect:/inicio-de-sesion");
        }

        List<Publicacion> publicaciones = servicioPublicacion.obtenerPublicacionesDelUsuario(usuario);

        // Asegurar que nunca sea null
        if (publicaciones == null) {
            publicaciones = new ArrayList<>();
        }

        model.put("usuario", usuario);
        model.put("publicaciones", publicaciones);
        return new ModelAndView("perfil", model);
    }

    @RequestMapping(path = "/perfil/editar", method = RequestMethod.GET)
    public ModelAndView irAEditarPerfil(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return new ModelAndView("redirect:/inicio-de-sesion");
        }

        DatosEdicionPerfil datosEdicionPerfil = new DatosEdicionPerfil(
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getNombreDeUsuario(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getDomicilio().getCalle(),
                usuario.getDomicilio().getNumero(),
                usuario.getDomicilio().getPiso(),
                usuario.getDomicilio().getDepartamento(),
                usuario.getDomicilio().getCiudad(),
                usuario.getDomicilio().getProvincia(),
                usuario.getDomicilio().getCodigoPostal()
        );

        // Mantener la ruta actual de la foto en el DTO para poder mostrarla en el formulario
        datosEdicionPerfil.setUrlFotoDePerfil(usuario.getUrlFotoDePerfil());

        model.put("usuario", usuario);
        model.put("datosEdicionPerfil", datosEdicionPerfil);
        model.put("provincias", Provincias.values());

        return new ModelAndView("editar-perfil", model);
    }


    @RequestMapping(path = "/perfil/editar/guardar", method = RequestMethod.POST)
    public ModelAndView guardarPerfil(@ModelAttribute("datosEdicionPerfil") DatosEdicionPerfil datosEdicionPerfil,
                                      @RequestParam(value = "fotoPerfil", required = false) MultipartFile archivo,
                                      HttpServletRequest request) {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return new ModelAndView("redirect:/inicio-de-sesion");
        }

        // Si el formulario no envió urlFotoDePerfil (no hay input para ese campo), preservar la actual
        if (datosEdicionPerfil.getUrlFotoDePerfil() == null || datosEdicionPerfil.getUrlFotoDePerfil().trim().isEmpty()) {
            datosEdicionPerfil.setUrlFotoDePerfil(usuario.getUrlFotoDePerfil());
        }

        ModelMap model = new ModelMap();
        ModelAndView camposObligatoriosVacios = verficarCamposObligatoriosVacios(datosEdicionPerfil, model);
        if (camposObligatoriosVacios.getModelMap().containsKey("error")) {
            String mensaje = (String) camposObligatoriosVacios.getModelMap().get("error");
            return devolverEdicionFallidaConDTO(new ModelMap(), datosEdicionPerfil, usuario, mensaje);
        }

        if (archivo != null && !archivo.isEmpty()) {
            try {
                validarImagen(archivo);
                String nombreArchivo = guardarImagenEnCarpeta(archivo);
                datosEdicionPerfil.setUrlFotoDePerfil("/images/" + nombreArchivo);
            } catch (Exception e) {
                return devolverEdicionFallidaConDTO(model, datosEdicionPerfil, usuario, "Error al subir la imagen: " + e.getMessage());
            }
        }

        datosEdicionPerfil.setId(usuario.getId());

        try {
            servicioPerfil.guardarCambiosPerfil(datosEdicionPerfil);
        } catch (Exception e) {
            return devolverEdicionFallidaConDTO(model, datosEdicionPerfil, usuario, e.getMessage());
        }

        Usuario usuarioActualizado = servicioPerfil.buscarPorId(usuario.getId());
        session.setAttribute("usuarioLogueado", usuarioActualizado);

        return new ModelAndView("redirect:/perfil");
    }

    @RequestMapping(path = "perfil/cerrar-sesion", method = RequestMethod.GET)
    public ModelAndView cerrarSesion(HttpServletRequest requestMock) {
        HttpSession session = requestMock.getSession();
        session.invalidate();
        return new ModelAndView("redirect:/inicio-de-sesion");
    }

    private ModelAndView verficarCamposObligatoriosVacios(DatosEdicionPerfil datosEdicionPerfil, ModelMap model) {
        if (datosEdicionPerfil.getNombre() == null || datosEdicionPerfil.getNombre().trim().isEmpty()) {
            return devolverEdicionFallida(model, "El nombre es obligatorio.");
        }
        if (datosEdicionPerfil.getApellido() == null || datosEdicionPerfil.getApellido().trim().isEmpty()) {
            return devolverEdicionFallida(model, "El apellido es obligatorio.");
        }
        if (datosEdicionPerfil.getNombreDeUsuario() == null || datosEdicionPerfil.getNombreDeUsuario().trim().isEmpty()) {
            return devolverEdicionFallida(model, "El nombre de usuario es obligatorio.");
        }
        if (datosEdicionPerfil.getEmail() == null || datosEdicionPerfil.getEmail().trim().isEmpty()) {
            return devolverEdicionFallida(model, "El email es obligatorio.");
        }
        if (datosEdicionPerfil.getTelefono() == null || datosEdicionPerfil.getTelefono().trim().isEmpty()) {
            return devolverEdicionFallida(model, "El teléfono es obligatorio.");
        }
        if (datosEdicionPerfil.getCalle() == null || datosEdicionPerfil.getCalle().trim().isEmpty()) {
            return devolverEdicionFallida(model, "La calle es obligatoria.");
        }
        if (datosEdicionPerfil.getNumero() == null || datosEdicionPerfil.getNumero().trim().isEmpty()) {
            return devolverEdicionFallida(model, "El número de domicilio es obligatorio.");
        }
        if (datosEdicionPerfil.getCiudad() == null || datosEdicionPerfil.getCiudad().trim().isEmpty()) {
            return devolverEdicionFallida(model, "La ciudad es obligatoria.");
        }
        if (datosEdicionPerfil.getProvincia() == null) {
            return devolverEdicionFallida(model, "La provincia es obligatoria.");
        }
        if (datosEdicionPerfil.getCodigoPostal() == null || datosEdicionPerfil.getCodigoPostal().trim().isEmpty()) {
            return devolverEdicionFallida(model, "El código postal es obligatorio.");
        }
        return new ModelAndView("editar-perfil", model);
    }

    private ModelAndView devolverEdicionFallida(ModelMap model, String mensaje) {
        model.put("error", mensaje);
        model.put("provincias", Provincias.values());
        return new ModelAndView("editar-perfil", model);
    }

    // Nuevo helper para errores preservando el DTO y usuario
    private ModelAndView devolverEdicionFallidaConDTO(ModelMap model, DatosEdicionPerfil datos, Usuario usuario, String mensaje) {
        model.put("error", mensaje);
        model.put("provincias", Provincias.values());
        model.put("datosEdicionPerfil", datos); // Mantener datos ingresados y urlFoto
        model.put("usuario", usuario);
        return new ModelAndView("editar-perfil", model);
    }

    // --- Helpers para manejo de imágenes ---
    private void validarImagen(MultipartFile archivo) throws IOException {
        // Tamaño máximo 5MB
        if (archivo.getSize() > 5 * 1024 * 1024) {
            throw new IOException("El archivo no debe superar los 5MB");
        }
        String contentType = archivo.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IOException("Solo se permiten archivos de imagen");
        }
    }

    private String guardarImagenEnCarpeta(MultipartFile archivo) throws IOException {
        String nombreOriginal = archivo.getOriginalFilename();
        String extension = "";
        if (nombreOriginal != null && nombreOriginal.contains(".")) {
            extension = nombreOriginal.substring(nombreOriginal.lastIndexOf('.'));
        }
        String nombreUnico = UUID.randomUUID().toString() + extension;

        // Usar siempre el directorio externo de uploads
        if (!Files.exists(UPLOADS_IMAGES_DIR)) {
            Files.createDirectories(UPLOADS_IMAGES_DIR);
        }
        Path rutaArchivo = UPLOADS_IMAGES_DIR.resolve(nombreUnico);
        try (InputStream is = archivo.getInputStream()) {
            Files.copy(is, rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
        }

        return nombreUnico;
    }
}
