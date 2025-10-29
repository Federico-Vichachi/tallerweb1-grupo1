document.addEventListener('DOMContentLoaded', () => {
    const btnFiltros = document.getElementById('btn-mostrar-filtros');
    const btnMapa = document.getElementById('btn-mostrar-mapa');
    const contenedorFiltros = document.getElementById('contenedor-filtros');
    const contenedorMapa = document.getElementById('contenedor-mapa');
    let mapaInicializado = false;

    btnFiltros.addEventListener('click', () => {
        contenedorFiltros.style.display = 'block';
        contenedorMapa.style.display = 'none';
        btnFiltros.classList.add('active');
        btnMapa.classList.remove('active');
    });

    btnMapa.addEventListener('click', () => {
        contenedorFiltros.style.display = 'none';
        contenedorMapa.style.display = 'block';
        btnMapa.classList.add('active');
        btnFiltros.classList.remove('active');

        if (!mapaInicializado) {
            iniciarMapaGlobal();
            mapaInicializado = true;
        }
    });
});