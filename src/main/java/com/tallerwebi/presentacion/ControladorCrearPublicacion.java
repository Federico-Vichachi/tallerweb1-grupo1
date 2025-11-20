package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.ValidacionPublicacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
public class ControladorCrearPublicacion {

    private final ServicioPublicacion servicioPublicacion;
    private static final Path UPLOADS_IMAGES_DIR = Paths.get(System.getProperty("user.dir"), "uploads", "images");

    @Autowired
    public ControladorCrearPublicacion(ServicioPublicacion servicioPublicacion) {
        this.servicioPublicacion = servicioPublicacion;
    }

    @RequestMapping(value = "/crear-publicacion", method =  RequestMethod.GET)
    public ModelAndView irCrearPublicacion() {
        ModelMap model = new ModelMap();
        model.put("datosAdopcion", new DatosAdopcion());
        model.put("datosRecaudacion", new DatosRecaudacion());
        model.put("datosSalud", new DatosSalud());
        model.put("datosPerdido", new DatosPerdido());
        model.put("datosEncontrado", new DatosEncontrado());
        model.put("provincias", Provincias.values());
        return new ModelAndView("crear-publicacion", model);
    }


    @RequestMapping(value = "/publicacion-adopcion", method = RequestMethod.POST)
    public ModelAndView crearPublicacionAdopcion(@ModelAttribute DatosAdopcion datosAdopcion,
                                                   @RequestParam(value = "imagenPublicacion", required = false) MultipartFile archivo,
                                                   HttpServletRequest request) {
        ModelMap model = new ModelMap();

        try {
            if (archivo != null && !archivo.isEmpty()) {
                validarImagen(archivo);
                String nombreArchivo = guardarImagenEnCarpeta(archivo);
                datosAdopcion.setImagen("/images/" + nombreArchivo);
            }

            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
            PublicacionAdopcion publicacionDeAdopcion = new PublicacionAdopcion(datosAdopcion);
            publicacionDeAdopcion.setUsuario(usuario);
            servicioPublicacion.guardar(publicacionDeAdopcion, request);
            model.put("mensaje", "Publicacion adopcion guardada correctamente");
            model.put("datosAdopcion", datosAdopcion);
            return new ModelAndView("redirect:/feed");
        }catch(ValidacionPublicacionException e){
            model.put("error", e.getMessage());
            model.put("datosAdopcion", datosAdopcion);
            model.put("datosRecaudacion", new DatosRecaudacion());
            model.put("datosSalud", new DatosSalud());
            model.put("datosPerdido", new DatosPerdido());
            model.put("datosEncontrado", new DatosEncontrado());
            model.put("provincias", Provincias.values());
            return new ModelAndView("crear-publicacion", model);
        } catch (IOException e) {
            model.put("error", "Error al subir la imagen: " + e.getMessage());
            model.put("datosAdopcion", datosAdopcion);
            model.put("datosRecaudacion", new DatosRecaudacion());
            model.put("datosSalud", new DatosSalud());
            model.put("datosPerdido", new DatosPerdido());
            model.put("datosEncontrado", new DatosEncontrado());
            model.put("provincias", Provincias.values());
            return new ModelAndView("crear-publicacion", model);
        }
    }


    @RequestMapping(value = "/publicacion-recaudacion", method = RequestMethod.POST)
    public ModelAndView crearPublicacionRecaudacion(@ModelAttribute DatosRecaudacion datosRecaudacion,
                                                      @RequestParam(value = "imagenPublicacion", required = false) MultipartFile archivo,
                                                      HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            if (archivo != null && !archivo.isEmpty()) {
                validarImagen(archivo);
                String nombreArchivo = guardarImagenEnCarpeta(archivo);
                datosRecaudacion.setImagen("/images/" + nombreArchivo);
            }

            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
            PublicacionRecaudacion publicacionDeRecaudacion = new PublicacionRecaudacion(datosRecaudacion);
            publicacionDeRecaudacion.setUsuario(usuario);
            servicioPublicacion.guardar(publicacionDeRecaudacion, request);

            model.put("mensaje", "Publicacion recaudacion creada correctamente");
            model.put("datosRecaudacion", datosRecaudacion);
            return new ModelAndView("redirect:/feed");
        }catch(ValidacionPublicacionException e){
            model.put("error", e.getMessage());
            model.put("datosAdopcion", new DatosAdopcion());
            model.put("datosRecaudacion", datosRecaudacion);
            model.put("datosSalud", new DatosSalud());
            model.put("datosPerdido", new DatosPerdido());
            model.put("datosEncontrado", new DatosEncontrado());
            model.put("provincias", Provincias.values());
            return new ModelAndView("crear-publicacion", model);
        } catch (IOException e) {
            model.put("error", "Error al subir la imagen: " + e.getMessage());
            model.put("datosAdopcion", new DatosAdopcion());
            model.put("datosRecaudacion", datosRecaudacion);
            model.put("datosSalud", new DatosSalud());
            model.put("datosPerdido", new DatosPerdido());
            model.put("datosEncontrado", new DatosEncontrado());
            model.put("provincias", Provincias.values());
            return new ModelAndView("crear-publicacion", model);
        }
    }

    @RequestMapping(value = "/publicacion-salud", method = RequestMethod.POST)
    public ModelAndView crearPublicacionSalud(@ModelAttribute DatosSalud datosSalud,
                                               @RequestParam(value = "imagenPublicacion", required = false) MultipartFile archivo,
                                               HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            if (archivo != null && !archivo.isEmpty()) {
                validarImagen(archivo);
                String nombreArchivo = guardarImagenEnCarpeta(archivo);
                datosSalud.setImagen("/images/" + nombreArchivo);
            }

            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
            PublicacionSalud publicacionDeSalud = new PublicacionSalud(datosSalud);
            publicacionDeSalud.setUsuario(usuario);
            servicioPublicacion.guardar(publicacionDeSalud, request);
            model.put("mensaje", "Publicacion salud creada correctamente");
            model.put("datosSalud", datosSalud);
            return new ModelAndView("redirect:/feed");
        }catch (ValidacionPublicacionException e){
            model.put("error", e.getMessage());
            model.put("datosAdopcion", new DatosAdopcion());
            model.put("datosRecaudacion", new DatosRecaudacion());
            model.put("datosSalud", datosSalud);
            model.put("datosPerdido", new DatosPerdido());
            model.put("datosEncontrado", new DatosEncontrado());
            model.put("provincias", Provincias.values());
            return new ModelAndView("crear-publicacion", model);
        } catch (IOException e) {
            model.put("error", "Error al subir la imagen: " + e.getMessage());
            model.put("datosAdopcion", new DatosAdopcion());
            model.put("datosRecaudacion", new DatosRecaudacion());
            model.put("datosSalud", datosSalud);
            model.put("datosPerdido", new DatosPerdido());
            model.put("datosEncontrado", new DatosEncontrado());
            model.put("provincias", Provincias.values());
            return new ModelAndView("crear-publicacion", model);
        }
    }

    @RequestMapping(value = "/publicacion-perdido", method = RequestMethod.POST)
    public ModelAndView crearPublicacionPerdido(@ModelAttribute DatosPerdido datosPerdido,
                                                 @RequestParam(value = "imagenPublicacion", required = false) MultipartFile archivo,
                                                 HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            if (archivo != null && !archivo.isEmpty()) {
                validarImagen(archivo);
                String nombreArchivo = guardarImagenEnCarpeta(archivo);
                datosPerdido.setImagen("/images/" + nombreArchivo);
            }

            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
            PublicacionPerdido publicacionDePerdido = new PublicacionPerdido(datosPerdido);
            publicacionDePerdido.setUsuario(usuario);
            servicioPublicacion.guardar(publicacionDePerdido, request);

            model.put("mensaje", "Publicacion perdido creada correctamente");
            model.put("datosPerdido", datosPerdido);
            return new ModelAndView("redirect:/feed");

        }catch (ValidacionPublicacionException e){
            model.put("error", e.getMessage());
            model.put("datosAdopcion", new DatosAdopcion());
            model.put("datosRecaudacion", new DatosRecaudacion());
            model.put("datosSalud", new DatosSalud());
            model.put("datosPerdido", datosPerdido);
            model.put("datosEncontrado", new DatosEncontrado());
            model.put("provincias", Provincias.values());
            return new ModelAndView("crear-publicacion", model);
        } catch (IOException e) {
            model.put("error", "Error al subir la imagen: " + e.getMessage());
            model.put("datosAdopcion", new DatosAdopcion());
            model.put("datosRecaudacion", new DatosRecaudacion());
            model.put("datosSalud", new DatosSalud());
            model.put("datosPerdido", datosPerdido);
            model.put("datosEncontrado", new DatosEncontrado());
            model.put("provincias", Provincias.values());
            return new ModelAndView("crear-publicacion", model);
        }
    }

    @RequestMapping(value = "/publicacion-encontrado", method = RequestMethod.POST)
    public ModelAndView crearPublicacionEncontrado(@ModelAttribute DatosEncontrado datosEncontrado,
                                                     @RequestParam(value = "imagenPublicacion", required = false) MultipartFile archivo,
                                                     HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            if (archivo != null && !archivo.isEmpty()) {
                validarImagen(archivo);
                String nombreArchivo = guardarImagenEnCarpeta(archivo);
                datosEncontrado.setImagen("/images/" + nombreArchivo);
            }

            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
            PublicacionEncontrado publicacionDeEncontrado = new PublicacionEncontrado(datosEncontrado);
            publicacionDeEncontrado.setUsuario(usuario);
            servicioPublicacion.guardar(publicacionDeEncontrado, request);
            model.put("mensaje", "Publicacion encontrado creada correctamente");
            model.put("datosEncontrado", datosEncontrado);
            return new ModelAndView("redirect:/feed");
        }catch (ValidacionPublicacionException e){
            model.put("error", e.getMessage());
            model.put("datosAdopcion", new DatosAdopcion());
            model.put("datosRecaudacion", new DatosRecaudacion());
            model.put("datosSalud", new DatosSalud());
            model.put("datosPerdido", new DatosPerdido());
            model.put("datosEncontrado", datosEncontrado);
            model.put("provincias", Provincias.values());
            return new ModelAndView("crear-publicacion", model);
        } catch (IOException e) {
            model.put("error", "Error al subir la imagen: " + e.getMessage());
            model.put("datosAdopcion", new DatosAdopcion());
            model.put("datosRecaudacion", new DatosRecaudacion());
            model.put("datosSalud", new DatosSalud());
            model.put("datosPerdido", new DatosPerdido());
            model.put("datosEncontrado", datosEncontrado);
            model.put("provincias", Provincias.values());
            return new ModelAndView("crear-publicacion", model);
        }
    }

    private void validarImagen(MultipartFile archivo) throws IOException {
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

        if (!Files.exists(UPLOADS_IMAGES_DIR)) {
            Files.createDirectories(UPLOADS_IMAGES_DIR);
        }

        Path rutaDestino = UPLOADS_IMAGES_DIR.resolve(nombreUnico);
        Files.copy(archivo.getInputStream(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);

        return nombreUnico;
    }
}
