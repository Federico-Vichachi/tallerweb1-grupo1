window.iniciarMapaGlobal = function() {
    console.log("Iniciando mapa...");
    const mapElement = document.getElementById('map');

    if (navigator.geolocation) {
        mapElement.innerHTML = '<p class="text-center p-3">Obteniendo tu ubicación...</p>';
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const lat = position.coords.latitude;
                const lon = position.coords.longitude;
                inicializarMapaConUbicacion(lat, lon);
            },
            () => {
                mapElement.innerHTML = '<p class="text-center text-danger p-3">No se pudo obtener tu ubicación. Mostrando mapa por defecto.</p>';
                inicializarMapaConUbicacion(-34.6037, -58.3816);
            }
        );
    } else {
        mapElement.innerHTML = '<p class="text-center text-danger p-3">Geolocalización no soportada por este navegador.</p>';
        inicializarMapaConUbicacion(-34.6037, -58.3816);
    }
};


function inicializarMapaConUbicacion(lat, lon) {
    console.log(`Inicializando mapa en lat: ${lat}, lon: ${lon}`);
    const mapElement = document.getElementById('map');
    try {
        mapElement.innerHTML = '';
        const map = L.map('map').setView([lat, lon], 14);

        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);

        L.marker([lat, lon]).addTo(map)
            .bindPopup('<b>¡Estás aquí!</b>')
            .openPopup();

        cargarPublicacionesCercanas(map, lat, lon);

        setTimeout(() => map.invalidateSize(), 10);

    } catch (error) {
        console.error("Error al inicializar el mapa de Leaflet:", error);
        mapElement.innerHTML = "<p class='text-danger'>Ocurrió un error al intentar mostrar el mapa.</p>";
    }
}


function cargarPublicacionesCercanas(map, lat, lon) {
    console.log("Buscando publicaciones cercanas...");

    const apiUrl = `${contextPath}api/publicaciones-cercanas?lat=${lat}&lon=${lon}`;

    console.log("Llamando a la API en:", apiUrl);

    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error en la red: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(publicaciones => {
            console.log("Publicaciones recibidas:", publicaciones);

            if (publicaciones.length === 0) {
                console.log("No se encontraron mascotas perdidas o encontradas en un radio de 2km.");
                return;
            }

            publicaciones.forEach(pub => {
                const popupContenido = `
                    <div style="text-align: center; font-family: sans-serif;">
                        <img src="${contextPath.slice(0, -1)}${pub.imagen}" alt="${pub.titulo}" width="120" style="border-radius: 5px; margin-bottom: 8px;"/>
                        <h6 style="margin: 0; font-size: 14px;">${pub.titulo}</h6>
                        <span class="badge bg-${pub.tipo === 'Perdido' ? 'danger' : 'primary'}" style="margin: 4px 0;">${pub.tipo}</span><br>
                         <a href="${contextPath}publicacion/${pub.id}">Ver más detalles</a>
                    </div>
                `;

                L.marker([pub.latitud, pub.longitud])
                    .addTo(map)
                    .bindPopup(popupContenido);
            });
        })
        .catch(error => {
            console.error('Error crítico durante el fetch de publicaciones:', error);
        });
}