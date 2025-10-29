-- Domicilios
INSERT INTO Domicilio(id, calle, numero, ciudad, provincia, codigoPostal, departamento, piso)
VALUES(1, 'Av. de Mayo', '1234', 'Ramos Mejia', 'BUENOS_AIRES', '1704', null, null);
INSERT INTO Domicilio(id, calle, numero, ciudad, provincia, codigoPostal, departamento, piso)
VALUES(2, 'Belgrano', '567', 'Cordoba', 'CORDOBA', '5000', null, null);
INSERT INTO Domicilio(id, calle, numero, ciudad, provincia, codigoPostal, departamento, piso)
VALUES(3, 'San Martin', '890', 'Rosario', 'SANTA_FE', '2000', null, null);

-- Usuarios
INSERT INTO Usuario(id, nombre, apellido, email, telefono, contrasenia, nombreDeUsuario, rol, domicilio_id, urlFotoDePerfil, puntos)
VALUES(1, 'Juan', 'Perez', 'juan.perez@test.com', '1122334455', 'Contrasenia@2025', 'juanperez', 'USUARIO_COMUN', 1, '/images/user.jpg', 0);
INSERT INTO Usuario(id, nombre, apellido, email, telefono, contrasenia, nombreDeUsuario, rol, domicilio_id, urlFotoDePerfil, puntos)
VALUES(2, 'Maria', 'Garcia', 'maria.garcia@test.com', '3511223344', 'Contrasenia@2025', 'mariagarcia', 'ORGANIZACION', 2, '/images/user.jpg', 0);
INSERT INTO Usuario(id, nombre, apellido, email, telefono, contrasenia, nombreDeUsuario, rol, domicilio_id, urlFotoDePerfil, puntos)
VALUES(3, 'Carlos', 'Lopez', 'carlos.lopez@test.com', '3415556677', 'Contrasenia@2025', 'carloslopez', 'VETERINARIO', 3, '/images/user.jpg', 0);


-- Publicaciones
-- Publicacion 1: Adopcion
INSERT INTO Publicacion(id, titulo, descripcionCorta, descripcionDetallada, imagen, raza, tamanio, provincia, localidad, telefono, email, usuario_id, fechaPublicacion)
VALUES(1, 'Rocky busca un hogar amoroso', 'Perrito mestizo de 2 anios, muy jugueton y carinioso.', 'Fue rescatado de la calle y ya esta desparasitado y vacunado. Se lleva bien con ninios y otros perros. Busca una familia con espacio.', '/images/perro.webp', 'Mestizo', 25, 'BUENOS_AIRES', 'Ramos Mejia', 1122334455, 'juan.perez@test.com', 1, '2023-10-27 10:30:00');
INSERT INTO PublicacionAdopcion(id, edad)
VALUES(1, 2);

-- Publicacion 2: Perdido
INSERT INTO Publicacion(id, titulo, descripcionCorta, descripcionDetallada, imagen, raza, tamanio, provincia, localidad, telefono, email, usuario_id, fechaPublicacion, latitud, longitud)
VALUES(2, 'Se perdio Simon, gato atigrado', 'Gato macho de 3 anios, se perdio en Merlo.', 'Es un poco asustadizo pero muy docil. Tiene una mancha blanca en el pecho. Por favor, si lo ven, contactense.', '/images/perro.webp', 'Comun Europeo Atigrado', 5, 'BUENOS_AIRES','Merlo', 3511223344, 'maria.garcia@test.com', 2, '2023-10-26 18:00:00', -34.6625, -58.6980);
INSERT INTO PublicacionPerdido(id, fechaDesaparicion, horaDesaparicion, llevaCollar, recompensa)
VALUES(2, '2023-10-25', '20:00', false, 'No');

-- Publicacion 3: Recaudacion
INSERT INTO Publicacion(id, titulo, descripcionCorta, descripcionDetallada, imagen, raza, tamanio, provincia, localidad, telefono, email, usuario_id, fechaPublicacion)
VALUES(3, 'Ayuda para la cirugia de Luna', 'Necesitamos recaudar fondos para operar a Luna.', 'Luna fue atropellada y necesita una cirugia compleja en su cadera. Cualquier ayuda, por pequenia que sea, nos acerca a la meta. ¡Gracias!', '/images/perro.webp', 'Galgo', 20, 'CORDOBA','Capital', 3511223344, 'maria.garcia@test.com', 2, '2023-10-25 12:00:00');
INSERT INTO PublicacionRecaudacion(id, edad, meta, cbu, metodoPreferido)
VALUES(3, 4, 50000.00, 'AYUDA.LUNA.MP', 'Mercado Pago');

-- Publicacion 4: Salud
INSERT INTO Publicacion(id, titulo, descripcionCorta, descripcionDetallada, imagen, raza, tamanio, provincia, localidad, telefono, email, usuario_id, fechaPublicacion)
VALUES(4, 'Consulta sobre comportamiento canino', 'Mi perro ladra sin parar cuando me voy.', 'Es un caniche de 5 anios y sufre de ansiedad por separacion. ¿Algun consejo o recomendacion de un especialista en comportamiento en Rosario?', '/images/perro.webp', 'Caniche', 8, 'SANTA_FE','Santa Fe', 3415556677, 'carlos.lopez@test.com', 3, '2023-10-28 09:00:00');
INSERT INTO PublicacionSalud(id, edad, sintomasPrincipales, diagnostico, nivelUrgencia)
VALUES(4, 5, 'Ladridos excesivos, destructividad', 'Ansiedad por separacion', 'No urgente');

-- Publicacion 5: Encontrado
INSERT INTO Publicacion(id, titulo, descripcionCorta, descripcionDetallada, imagen, raza, tamanio, provincia, localidad, telefono, email, usuario_id, fechaPublicacion, latitud, longitud)
VALUES(5, 'Encontre perrita en San Telmo', 'Perrita hembra encontrada cerca de Plaza Dorrego.', 'Parece joven, es muy buena y se nota que es de una casa. Tiene un collar rosa pero sin chapita. Buscamos a sus duenios.', '/images/perro.webp', 'Mestiza', 15, 'CAPITAL_FEDERAL', 'San Telmo', 1122334455, 'juan.perez@test.com', 1, '2023-10-27 20:15:00', -34.617, -58.373);
INSERT INTO PublicacionEncontrado(id)
VALUES(5);

-- Publicacion 6: Adopcion
INSERT INTO Publicacion(id, titulo, descripcionCorta, descripcionDetallada, imagen, raza, tamanio, provincia, localidad, telefono, email, usuario_id, fechaPublicacion)
VALUES(6, 'Gatitos buscan familia responsable', 'Camada de 2 meses listos para ser adoptados.', 'Son 4 hermanitos (2 machos y 2 hembras) muy juguetones. Se entregan con compromiso de castracion a los 6 meses.', '/images/perro.webp', 'Comun Europeo', 1, 'CORDOBA', 'Cordoba', 3511223344, 'maria.garcia@test.com', 2, '2023-10-24 11:00:00');
INSERT INTO PublicacionAdopcion(id, edad)
VALUES(6, 0);