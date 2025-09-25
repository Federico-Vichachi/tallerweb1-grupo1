document.addEventListener('DOMContentLoaded', function (){
    const tagsPorCategoria = {
        adopcion:['Vacunado','Castrado','Sociable']
        perdido:['Collar','Recompensa','Lugar Desaparicion']
        encontrado:['Hogar Temporal','Domestico','Enfermo']
        salud:['Urgente','Tratamiento','Cirugia']
        recaudacion:['Donaciones','Alimento','Medicamentos']
    };

    const radiosCategoria = document.querySelectorAll("input[name="categoria"]");
    const contenedorTags = document.queriSelector(".contenedor-tags");
    const contenedorFiltrosActivos = document.querySelector(".contenedor-filtros-activos");
    const btnLimpiar = document.querySelector(".limpiar-filtros-btn")

    let filtrosActivos = new Set(); // Aca guardo los tags que va seleccionando

    function actualizarTagsDisponibles(categoria){
        contenedorTags.innerHTML = '';
        const todosTags = tagsPorCategoria[categoria];

        tags.forEach(tag =>){
            const button = document.createElement("button");
            button.className = "";

        }
    }
    }

 }

