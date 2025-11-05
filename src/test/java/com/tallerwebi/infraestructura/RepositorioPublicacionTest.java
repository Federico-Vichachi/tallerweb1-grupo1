package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioPublicacionTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RepositorioPublicacion repositorioPublicacion;

    @Test
    @Transactional
    @Rollback
    public void queAlBuscarPorFiltroDeProvinciaDevuelvaSoloLasPublicacionesDeEsaProvincia() {

        dadoQueExistenPublicacionesEnDiferentesProvincias();
        List<Publicacion> publicacionesEncontradas = cuandoBuscoPorFiltroDeProvincia(Provincias.CORDOBA);
        entoncesLaListaContieneSoloPublicacionesDeCordoba(publicacionesEncontradas);
    }

    private void dadoQueExistenPublicacionesEnDiferentesProvincias() {
        Usuario usuario = new Usuario();
        usuario.setEmail("dillesca@ulma.com.ar");
        sessionFactory.getCurrentSession().save(usuario);

        PublicacionAdopcion adopcionBsAs = new PublicacionAdopcion();
        adopcionBsAs.setTitulo("Perro en Adopcion BS AS");
        adopcionBsAs.setProvincia(Provincias.BUENOS_AIRES);
        adopcionBsAs.setLocalidad("Moron");
        adopcionBsAs.setUsuario(usuario);
        sessionFactory.getCurrentSession().save(adopcionBsAs);

        PublicacionPerdido perdidoCordoba = new PublicacionPerdido();
        perdidoCordoba.setTitulo("Gato Perdido CORDOBA");
        perdidoCordoba.setProvincia(Provincias.CORDOBA);
        perdidoCordoba.setLocalidad("Capital");
        perdidoCordoba.setUsuario(usuario);
        sessionFactory.getCurrentSession().save(perdidoCordoba);
    }

    private List<Publicacion> cuandoBuscoPorFiltroDeProvincia(Provincias provincia) {
        return repositorioPublicacion.buscarPublicacionesPorFiltros(null, null, provincia, null);
    }

    private void entoncesLaListaContieneSoloPublicacionesDeCordoba(List<Publicacion> resultado) {
        assertThat(resultado, hasSize(1));
        assertThat(resultado.get(0).getProvincia(), equalTo(Provincias.CORDOBA));
        assertThat(resultado.get(0).getTitulo(), equalTo("Gato Perdido CORDOBA"));
    }

    @Test
    @Transactional
    @Rollback
    public void queAlBuscarPorMultiplesFiltrosMeDevuelvaCorrectamenteLasPublicacionesFiltradas() {

        dadoQueExistenVariasPublicacionesParaFiltradoCombinado();
        List<Publicacion> publicacionesEncontradas = cuandoBuscoPorCategoriaYProvincia("ADOPCION", Provincias.CORDOBA);
        entoncesLaListaContieneUnaSolaPublicacionQueCumpleAmbosCriterios(publicacionesEncontradas);
    }

    private void dadoQueExistenVariasPublicacionesParaFiltradoCombinado() {
        Usuario usuario = new Usuario();
        usuario.setEmail("dillesca@ulma.com.ar");
        sessionFactory.getCurrentSession().save(usuario);

        PublicacionAdopcion adopcionBsAs = new PublicacionAdopcion();
        adopcionBsAs.setTitulo("Adopcion en BS AS");
        adopcionBsAs.setProvincia(Provincias.BUENOS_AIRES);
        adopcionBsAs.setLocalidad("La Matanza");
        adopcionBsAs.setUsuario(usuario);
        sessionFactory.getCurrentSession().save(adopcionBsAs);

        PublicacionPerdido perdidoCordoba = new PublicacionPerdido();
        perdidoCordoba.setTitulo("Perdido en CORDOBA");
        perdidoCordoba.setProvincia(Provincias.CORDOBA);
        perdidoCordoba.setLocalidad("Capital");
        perdidoCordoba.setUsuario(usuario);
        sessionFactory.getCurrentSession().save(perdidoCordoba);

        PublicacionAdopcion adopcionCordoba = new PublicacionAdopcion();
        adopcionCordoba.setTitulo("Adopcion en CORDOBA");
        adopcionCordoba.setProvincia(Provincias.CORDOBA);
        adopcionCordoba.setLocalidad("Capital");
        adopcionCordoba.setUsuario(usuario);
        sessionFactory.getCurrentSession().save(adopcionCordoba);
    }

    private List<Publicacion> cuandoBuscoPorCategoriaYProvincia(String categoria, Provincias provincia) {
        return repositorioPublicacion.buscarPublicacionesPorFiltros(categoria, null, provincia, null);
    }

    private void entoncesLaListaContieneUnaSolaPublicacionQueCumpleAmbosCriterios(List<Publicacion> resultado) {
        assertThat(resultado, hasSize(1));
        Publicacion encontrada = resultado.get(0);
        assertThat(encontrada, instanceOf(PublicacionAdopcion.class));
        assertThat(encontrada.getProvincia(), equalTo(Provincias.CORDOBA));
        assertThat(encontrada.getTitulo(), equalTo("Adopcion en CORDOBA"));
    }

    @Test
    @Transactional
    @Rollback
    public void queAlBuscarPorTituloParcialDevuelvaLasPublicacionesConCoincidencias() {

        dadoQueExistenPublicacionesConTitulosSimilares();
        List<Publicacion> publicacionesEncontradas = cuandoBuscoPorFiltroDeNombre("Gatito");
        entoncesEncuentroLasPublicacionesQueContienenEsaPalabraEnElTitulo(publicacionesEncontradas);
    }

    private void dadoQueExistenPublicacionesConTitulosSimilares() {
        Usuario usuario = new Usuario();
        usuario.setEmail("dillesca@ulma.com.ar");
        sessionFactory.getCurrentSession().save(usuario);

        PublicacionAdopcion adopcionGato = new PublicacionAdopcion();
        adopcionGato.setTitulo("Lindo Gatito en adopcion");
        adopcionGato.setLocalidad("Almagro");
        adopcionGato.setUsuario(usuario);
        sessionFactory.getCurrentSession().save(adopcionGato);

        PublicacionPerdido perdidoPerro = new PublicacionPerdido();
        perdidoPerro.setTitulo("Se perdio mi perro");
        perdidoPerro.setLocalidad("Caballito");
        perdidoPerro.setUsuario(usuario);
        sessionFactory.getCurrentSession().save(perdidoPerro);

        PublicacionEncontrado encontradoGato = new PublicacionEncontrado();
        encontradoGato.setTitulo("Encontre un Gatito negro");
        encontradoGato.setLocalidad("Flores");
        encontradoGato.setUsuario(usuario);
        sessionFactory.getCurrentSession().save(encontradoGato);
    }

    private List<Publicacion> cuandoBuscoPorFiltroDeNombre(String nombre) {
        return repositorioPublicacion.buscarPublicacionesPorFiltros(null, nombre, null, null);
    }

    private void entoncesEncuentroLasPublicacionesQueContienenEsaPalabraEnElTitulo(List<Publicacion> resultado) {
        assertThat(resultado, hasSize(2));
        assertThat(resultado.get(0).getTitulo(), containsStringIgnoringCase("Gatito"));
        assertThat(resultado.get(1).getTitulo(), containsStringIgnoringCase("Gatito"));
    }


    @Test
    @Transactional
    @Rollback
    public void queAlBuscarPorLocalidadParcialDevuelvaLasQueCoincidan() {

        dadoQueExistenPublicacionesEnDistintasLocalidades();
        List<Publicacion> publicacionesEncontradas = cuandoBuscoPorFiltroDeLocalidad("Moro");
        entoncesEncuentroLasPublicacionesDeEsaLocalidad(publicacionesEncontradas);
    }

    private void dadoQueExistenPublicacionesEnDistintasLocalidades() {
        Usuario usuario = new Usuario();
        usuario.setEmail("dillesca@ulma.com.ar");
        sessionFactory.getCurrentSession().save(usuario);

        PublicacionAdopcion adopcionMoron = new PublicacionAdopcion();
        adopcionMoron.setTitulo("En Moron");
        adopcionMoron.setLocalidad("Moron");
        adopcionMoron.setUsuario(usuario);
        sessionFactory.getCurrentSession().save(adopcionMoron);

        PublicacionPerdido perdidoItuzaingo = new PublicacionPerdido();
        perdidoItuzaingo.setTitulo("En Ituzaingo");
        perdidoItuzaingo.setLocalidad("Ituzaingo");
        perdidoItuzaingo.setUsuario(usuario);
        sessionFactory.getCurrentSession().save(perdidoItuzaingo);
    }

    private List<Publicacion> cuandoBuscoPorFiltroDeLocalidad(String localidad) {
        return repositorioPublicacion.buscarPublicacionesPorFiltros(null, null, null, localidad);
    }

    private void entoncesEncuentroLasPublicacionesDeEsaLocalidad(List<Publicacion> resultado) {
        assertThat(resultado, hasSize(1));
        assertThat(resultado.get(0).getLocalidad(), equalTo("Moron"));
    }

    @Test
    @Transactional
    @Rollback
    public void queAlBuscarConFiltrosSinCoincidenciasDevuelvaListaVacia() {

        dadoQueExistenVariasPublicacionesParaFiltradoCombinado();
        List<Publicacion> publicacionesEncontradas = cuandoBuscoConFiltrosSinResultados("PERDIDO", Provincias.BUENOS_AIRES);
        entoncesLaListaDeResultadosEstaVacia(publicacionesEncontradas);
    }

    private List<Publicacion> cuandoBuscoConFiltrosSinResultados(String categoria, Provincias provincia) {
        return repositorioPublicacion.buscarPublicacionesPorFiltros(categoria, null, provincia, null);
    }

    private void entoncesLaListaDeResultadosEstaVacia(List<Publicacion> resultado) {
        assertThat(resultado, is(notNullValue()));
        assertThat(resultado, is(empty()));
    }
}