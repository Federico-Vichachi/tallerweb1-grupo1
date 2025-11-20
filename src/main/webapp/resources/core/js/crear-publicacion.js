document.addEventListener("DOMContentLoaded", function() {
    const btnAdopcion   = document.getElementById("boton-adopcion");
    const btnRecaudacion = document.getElementById("boton-recaudacion");
    const btnConsulta    = document.getElementById("boton-consulta");
    const btnPerdido     = document.getElementById("boton-perdido");
    const btnEncontrado  = document.getElementById("boton-encontrado");

    const formAdopcion   = document.getElementById("form-adopcion");
    const formRecaudacion = document.getElementById("form-recaudacion");
    const formConsulta    = document.getElementById("form-consulta");
    const formPerdido     = document.getElementById("form-perdido");
    const formEncontrado  = document.getElementById("form-encontrado");

    let mapaPerdido = null;
    let mapaEncontrado = null;

    function ocultarFormularios() {
        formAdopcion.style.display = "none";
        formRecaudacion.style.display = "none";
        formConsulta.style.display = "none";
        formPerdido.style.display = "none";
        formEncontrado.style.display = "none";
    }

    function inicializarMapa(mapaId, latitudId, longitudId) {
        const latInicial = -34.6037;
        const lonInicial = -58.3816;

        const mapa = L.map(mapaId).setView([latInicial, lonInicial], 11);
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '© OpenStreetMap contributors'
        }).addTo(mapa);

        let marcador = null;

        mapa.on('click', function(e) {
            const lat = e.latlng.lat;
            const lon = e.latlng.lng;

            document.getElementById(latitudId).value = lat;
            document.getElementById(longitudId).value = lon;

            if (marcador) {
                marcador.setLatLng(e.latlng);
            } else {
                marcador = L.marker(e.latlng).addTo(mapa);
            }
        });

        setTimeout(() => mapa.invalidateSize(), 10);

        return mapa;
    }


    btnAdopcion.onclick = function() { ocultarFormularios(); formAdopcion.style.display = "block"; };
    btnRecaudacion.onclick = function() { ocultarFormularios(); formRecaudacion.style.display = "block"; };
    btnConsulta.onclick = function() { ocultarFormularios(); formConsulta.style.display = "block"; };

    btnPerdido.onclick = function() {
        ocultarFormularios();
        formPerdido.style.display = "block";
        if (!mapaPerdido) {
            mapaPerdido = inicializarMapa('mapa-perdido', 'latitud-perdido', 'longitud-perdido');
        } else {
            setTimeout(() => mapaPerdido.invalidateSize(), 10);
        }
    };

    btnEncontrado.onclick = function() {
        ocultarFormularios();
        formEncontrado.style.display = "block";
        if (!mapaEncontrado) {
            mapaEncontrado = inicializarMapa('mapa-encontrado', 'latitud-encontrado', 'longitud-encontrado');
        } else {
            setTimeout(() => mapaEncontrado.invalidateSize(), 10);
        }
    };
});