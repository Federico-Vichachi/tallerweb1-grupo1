document.addEventListener('DOMContentLoaded', () => {
    const mapElement = document.getElementById('map-container');
    if (!mapElement) {
        console.error("Error: Contenedor del mapa no encontrado.");
        return;
    }

    let map;
    let markersLayer = L.layerGroup();

    let miUbicacionOriginal = null;

    const filtroNombre = document.getElementById('filtro-nombre');
    const radiosCategoria = document.querySelectorAll('input[name="categoria"]');
    const filtroRango = document.getElementById('filtro-rango');
    const rangoValorDisplay = document.getElementById('rango-valor');


    function inicializarMapa() {
        navigator.geolocation.getCurrentPosition(
            (position) => crearMapa([position.coords.latitude, position.coords.longitude]),
            () => crearMapa([-34.6037, -58.3816])
        );
    }


    function crearMapa(coordenadas) {
        miUbicacionOriginal = coordenadas;

        const userLocationIcon = L.icon({
            iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-green.png',
            shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
            iconSize: [25, 41],
            iconAnchor: [12, 41],
            popupAnchor: [1, -34],
            shadowSize: [41, 41]
        });

        map = L.map(mapElement).setView(coordenadas, 9);
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png').addTo(map);
        markersLayer.addTo(map);

        L.marker(coordenadas, { icon: userLocationIcon })
            .addTo(map)
            .bindPopup('<b>Estas aca</b>')
            .openPopup();

        filtroNombre.addEventListener('input', debounce(actualizarMascotasEnMapa, 500));
        radiosCategoria.forEach(radio => radio.addEventListener('change', actualizarMascotasEnMapa));

        filtroRango.addEventListener('input', () => {
            rangoValorDisplay.textContent = filtroRango.value;
        });
        filtroRango.addEventListener('change', actualizarMascotasEnMapa);

        actualizarMascotasEnMapa();
    }


    function actualizarMascotasEnMapa() {
        if (!miUbicacionOriginal) {
            console.log("Esperando ubicación original para buscar...");
            return;
        }

        const lat = miUbicacionOriginal[0];
        const lon = miUbicacionOriginal[1];

        const radioKm = filtroRango.value;
        const nombre = filtroNombre.value;
        const categoriaSeleccionada = document.querySelector('input[name="categoria"]:checked');
        const categoria = categoriaSeleccionada ? categoriaSeleccionada.value : "";

        const params = new URLSearchParams({ lat, lon, radioKm });
        if (categoria) params.append('categoria', categoria);
        if (nombre) params.append('nombre', nombre);
        const apiUrl = `${contextPath}api/publicaciones-filtradas?${params.toString()}`;

        console.log("Buscando desde ubicación fija. Llamando a:", apiUrl);

        fetch(apiUrl)
            .then(response => response.json())
            .then(publicaciones => {
                markersLayer.clearLayers();
                publicaciones.forEach(pub => {
                    const popupContenido = `
                        <div style="text-align: center; font-family: sans-serif; max-width: 150px;">
                            <img src="${contextPath.slice(0, -1)}${pub.imagen}" alt="${pub.titulo}" width="120" style="border-radius: 5px; margin-bottom: 8px;"/>
                            <h6 style="margin: 0; font-size: 14px; white-space: normal;">${pub.titulo}</h6>
                            <span class="badge bg-${pub.tipo === 'Perdido' ? 'danger' : 'success'}" style="margin: 4px 0;">${pub.tipo}</span><br>
                            <a href="${contextPath}publicacion/${pub.id}" style="font-size: 12px;">Ver mas detalles</a>
                        </div>
                    `;
                    L.marker([pub.latitud, pub.longitud]).addTo(markersLayer).bindPopup(popupContenido);
                });
            })
            .catch(error => console.error('Error al actualizar marcadores:', error));
    }

    function debounce(func, delay) {
        let timeout;
        return function(...args) {
            clearTimeout(timeout);
            timeout = setTimeout(() => func.apply(this, args), delay);
        };
    }

    inicializarMapa();
});