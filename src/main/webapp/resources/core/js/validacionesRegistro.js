document.addEventListener('DOMContentLoaded', function () {
    const formularioDeRegistro = document.querySelector('form')
    const camposDeRegistro = formularioDeRegistro.querySelectorAll('input[pattern], input[required]') // Cambio: querySelectorAll

    camposDeRegistro.forEach(campo => {
        campo.addEventListener('invalid', function (evento) {
            evento.preventDefault()
            mostrarMensajeDeError(this)
        })

        campo.addEventListener('input', function () {
            if (this.validity.valid) {
                borrarError(this)
            }
        })
    })

    function mostrarMensajeDeError(campo) {
        borrarError(campo)

        let mensajeDeError = ''

        if (campo.validity.valueMissing) {
            mensajeDeError = `Este campo es obligatorio.`
        } else if (campo.validity.patternMismatch) {
            mensajeDeError = campo.getAttribute('title') || `${obtenerNombreDeCampo(campo)} no tiene el formato correcto.`
        } else if (campo.validity.typeMismatch) {
            mensajeDeError = `${obtenerNombreDeCampo(campo)} no tiene el formato correcto.`
        }

        const divError = document.createElement('div')
        divError.className = 'mensaje-error'
        divError.textContent = mensajeDeError
        campo.parentNode.insertBefore(divError, campo.nextSibling) // Removido: línea duplicada
        campo.classList.add('input-error')
        campo.classList.add('no-valido')
    }

    function borrarError(campo) {
        campo.classList.remove('no-valido')
        campo.classList.remove('input-error') // Agregado: remover clase de error
        const divError = campo.parentNode.querySelector('.mensaje-error')
        if (divError) {
            divError.remove()
        }
    }

    function obtenerNombreDeCampo(campo) {
        const nombreDeCampo = document.querySelector(`label[for="${campo.id}"]`)
        return nombreDeCampo ? nombreDeCampo.textContent : campo.name
    }

    const contrasenia = document.getElementById('contrasenia')
    const repeticionContrasenia = document.getElementById('repeticionDeContrasenia') // Cambio: ID correcto

    function validarConcordanciaEntreContraseniaYSuRepeticion() {
        if (repeticionContrasenia.value && contrasenia.value !== repeticionContrasenia.value) {
            repeticionContrasenia.setCustomValidity('Las contraseñas no coinciden.')
        } else {
            repeticionContrasenia.setCustomValidity('')
        }
    }

    contrasenia.addEventListener('input', validarConcordanciaEntreContraseniaYSuRepeticion)
    repeticionContrasenia.addEventListener('input', validarConcordanciaEntreContraseniaYSuRepeticion)
})
