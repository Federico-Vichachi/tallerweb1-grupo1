function copiarCantidad(id, tipo) {
    const cantidad = document.getElementById("cant-" + id).value;
    document.getElementById("cant-" + tipo + "-" + id).value = cantidad;
}