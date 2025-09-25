document.addEventListener("DOMContentLoaded", function() {

    const radiosCategoria = document.querySelectorAll('input[name="categoria"]'); // Selecciono todos los inputs que tengan name categoria
    const contenedorTagsDinamicos = document.getElementById('contenedor-tags-dinamicos'); // Selecciono el contenedor de mis tags
    const contenedorTagsActivos = document.getElementById('contenedor-tags-activos'); // Selecciono mi contenedor de tags activos

    const tagsPorCategoria = {
        'adopcion': ['Vacunado', 'Castrado', 'Sociable', 'Activo', 'Tranquilo'],
        'perdido': ['Collar', 'Recompensa', 'Visto por ultima vez', 'Contacto Urgente'],
        'encontrado': ['Hogar Temporal', 'Domestico', 'Necesita revision'],
        'salud': ['Urgente', 'Tratamiento', 'Cirugia', 'Medicacion'],
        'recaudacion': ['Donaciones', 'Alimento', 'Medicamentos', 'Voluntarios']
    }; // Mi ensalada de tags


    function actualizarFiltrosActivos() {
        contenedorTagsActivos.innerHTML = ''; // Vacio por las dudas mi contenedor

        const tagsMarcados = document.querySelectorAll('#contenedor-tags-dinamicos input[type="checkbox"]:checked');
        //Agarro todos mis inputs checkbox marcados

        tagsMarcados.forEach(checkbox => {     // Recorro el array
            const tagValue = checkbox.value;   // Agarro el valor del checkbox

            const tagSpan = document.createElement('span'); // Creo el span
            tagSpan.className = 'tags-activos';
            tagSpan.textContent = tagValue;

            // Crea el botón 'x' para eliminar
            const removeButton = document.createElement('button');  // Creo un elemento button
            removeButton.className = 'eliminar-tags';
            removeButton.innerHTML = 'x'; // Le agrego la X
            removeButton.dataset.tagValue = tagValue;

            tagSpan.appendChild(removeButton); // Agrego el boton al span
            contenedorTagsActivos.appendChild(tagSpan); // Agrego el span al contenedor
        });
    }

// Funcino par actualizar los tags por categoria
    function actualizarTags(categoria) {
        contenedorTagsDinamicos.innerHTML = '';  // Limpio el contenedor de tags
        const tagsParaMostrar = tagsPorCategoria[categoria]; // Agarro mi array y busco la lista de tags para la categoria actual
        if (!tagsParaMostrar) {
            actualizarFiltrosActivos(); // Limpia los filtros activos si no hay tags
            return;
        }

        tagsParaMostrar.forEach(tag => {
            const label = document.createElement('label'); // Creo el label
            const checkbox = document.createElement('input'); // Creo el input
            checkbox.type = 'checkbox'; // Lo hago checkbox al input
            checkbox.name = 'tags';
            checkbox.value = tag;
            label.appendChild(checkbox);
            label.appendChild(document.createTextNode(' ' + tag));
            contenedorTagsDinamicos.appendChild(label); // Lo agrego al contenedor
        });

        actualizarFiltrosActivos();  // Creo todos los checkbox y esta funcion los marca
    }

    radiosCategoria.forEach(radio => {
        radio.addEventListener('change', function() {    // Cuando se cambia la categoria
            if (this.checked) {                          // Si cambia los radios seleccionados, los actualizamos
                actualizarTags(this.value);
            }
        });
    });

    contenedorTagsDinamicos.addEventListener('click', function(event) {
        if (event.target.type === 'checkbox') {
            actualizarFiltrosActivos();
        }
    });

    contenedorTagsActivos.addEventListener('click', function(event) {
        if (event.target.classList.contains('eliminar-tags')) {
            const tagValueToRemove = event.target.dataset.tagValue;
            const checkboxToUncheck = document.querySelector(`#contenedor-tags-dinamicos input[value="${tagValueToRemove}"]`);

            if (checkboxToUncheck) {
                checkboxToUncheck.checked = false;
                actualizarFiltrosActivos();
            }
        }
    });
});