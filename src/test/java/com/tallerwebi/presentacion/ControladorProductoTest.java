package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.ServicioProducto;
import com.tallerwebi.dominio.ServicioPuntos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;

public class ControladorProductoTest {

    private ControladorProducto controladorProducto;
    private ServicioProducto servicioProducto;
    private ServicioPuntos servicioPuntos;
    private DatosProducto datosProducto;

    @BeforeEach
    public void setUp() {
        servicioProducto= mock(ServicioProducto.class);
        servicioPuntos = mock(ServicioPuntos.class);
        RepositorioUsuario repositorioUsuarioMock = mock(RepositorioUsuario.class);

        controladorProducto = new ControladorProducto(servicioProducto, servicioPuntos, repositorioUsuarioMock);
    }


    @Test
    public void dadoQueExistenDatosValidosDeProductoCuandoCreoElProductoEntoncesSeMuestraLaVistaDeTienda(){
        givenExistenDatosDeUnProducto();
        ModelAndView mav = whenCreoElProducto();
        thenSeMuestraLaVistaDeTienda(mav);
    }

    private void givenExistenDatosDeUnProducto() {
        datosProducto = new DatosProducto();
        datosProducto.setNombre("Voraz 10kg");
        datosProducto.setDescripcion("Bolsa de 10kg calidad economica, para perros adultos");
        datosProducto.setPrecioEnCentavos(12000);
        datosProducto.setPrecioEnPuntos(122);
        datosProducto.setStock(5);
        datosProducto.setImagen("Alimento.jpg");
    }


    private ModelAndView whenCreoElProducto() {
        return controladorProducto.crearProducto(datosProducto);
    }



    private void thenSeMuestraLaVistaDeTienda(ModelAndView mav) {
        assertThat(mav, is(notNullValue()));
        assertThat(mav.getViewName(), is("tienda"));
        assertThat(mav.getModel().get("datosProducto"), is(notNullValue()));
        assertThat(mav.getModel().get("datosProducto"), is(datosProducto));
    }

}
