document.addEventListener("DOMContentLoaded", function() {

    const radiosCategoria = document.querySelectorAll('input[name="categoria"]');
    const contenedorTagsDinamicos = document.getElementById('contenedor-tags-dinamicos');
    const contenedorTagsActivos = document.getElementById('contenedor-tags-activos');

    const tagsPorCategoria = {
        'adopcion': ['Vacunado', 'Castrado', 'Sociable', 'Activo', 'Tranquilo'],
        'perdido': ['Collar', 'Recompensa', 'Visto por ultima vez', 'Contacto Urgente'],
        'encontrado': ['Hogar Temporal', 'Domestico', 'Necesita revision'],
        'salud': ['Urgente', 'Tratamiento', 'Cirugia', 'Medicacion'],
        'recaudacion': ['Donaciones', 'Alimento', 'Medicamentos', 'Voluntarios']
    }; // Mi ensalada de tags


    function actualizarFiltrosActivos() {
        contenedorTagsActivos.innerHTML = '';

        const tagsMarcados = document.querySelectorAll('#contenedor-tags-dinamicos input[type="checkbox"]:checked');


        tagsMarcados.forEach(checkbox => {
            const tagValue = checkbox.value;

            const tagSpan = document.createElement('span');
            tagSpan.className = 'tags-activos';
            tagSpan.textContent = tagValue;


            const removeButton = document.createElement('button');
            removeButton.className = 'eliminar-tags';
            removeButton.innerHTML = 'x'; // Le agrego la X
            removeButton.dataset.tagValue = tagValue;

            tagSpan.appendChild(removeButton);
            contenedorTagsActivos.appendChild(tagSpan);
        });
    }


    function actualizarTags(categoria) {
        contenedorTagsDinamicos.innerHTML = '';
        const tagsParaMostrar = tagsPorCategoria[categoria];
        if (!tagsParaMostrar) {
            actualizarFiltrosActivos();
            return;
        }

        tagsParaMostrar.forEach(tag => {
            const label = document.createElement('label');
            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.name = 'tags';
            checkbox.value = tag;
            label.appendChild(checkbox);
            label.appendChild(document.createTextNode(' ' + tag));
            contenedorTagsDinamicos.appendChild(label);
        });

        actualizarFiltrosActivos();
    }

    radiosCategoria.forEach(radio => {
        radio.addEventListener('change', function() {
            if (this.checked) {
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