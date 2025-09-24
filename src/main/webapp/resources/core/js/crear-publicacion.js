document.addEventListener("DOMContentLoaded", function() {
    const btnAdopcion   = document.getElementById("boton-adopcion");
    const btnRecaudacion = document.getElementById("boton-recaudacion");
    const btnConsulta    = document.getElementById("boton-consulta");
    const btnPerdido     = document.getElementById("boton-perdido");
    const btnEncontrado  = document.getElementById("boton-encontrado");

    function ocultarFormularios() {
      document.getElementById("form-adopcion").style.display = "none";
      document.getElementById("form-recaudacion").style.display = "none";
      document.getElementById("form-consulta").style.display = "none";
      document.getElementById("form-perdido").style.display = "none";
      document.getElementById("form-encontrado").style.display = "none";
    }

    btnAdopcion.onclick = function() { ocultarFormularios(); document.getElementById("form-adopcion").style.display = "block"; };
    btnRecaudacion.onclick = function() { ocultarFormularios(); document.getElementById("form-recaudacion").style.display = "block"; };
    btnConsulta.onclick = function() { ocultarFormularios(); document.getElementById("form-consulta").style.display = "block"; };
    btnPerdido.onclick = function() { ocultarFormularios(); document.getElementById("form-perdido").style.display = "block"; };
    btnEncontrado.onclick = function() { ocultarFormularios(); document.getElementById("form-encontrado").style.display = "block"; };
});