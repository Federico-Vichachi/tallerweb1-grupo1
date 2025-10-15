-- Insertar domicilio primero
INSERT INTO Domicilio(id, calle, numero, ciudad, provincia, codigoPostal, departamento, piso)
VALUES(1, 'Av. Siempre Viva', '1234', 'Buenos Aires', 'BUENOS_AIRES', '1043', null, null);

-- Insertar usuario con referencia al domicilio
INSERT INTO Usuario(id, nombre, apellido, email, telefono, contrasenia, nombreDeUsuario, urlFotoDePerfil, rol, domicilio_id)
VALUES(1, 'Juan', 'Perez', 'test@gmail.com', '123456789', 'Contrasenia@2025', 'juanperez', null, 'USUARIO_COMUN', 1);



-- Publicacion 1: Adopcion
INSERT INTO Publicacion(id, usuario_id, fechaPublicacion, tipo, urlImagen, nombreMascota, edad, ubicacion, raza, descripcion, tags)
VALUES(1, 1, '2023-10-27 10:30:00', 'ADOPCION', '/images/perro.webp', 'Rocky', '2 anios', 'Ramos Mejia, Buenos Aires', 'Mestizo', 'Rocky es un perrito muy jugueton y carinioso. Busca una familia que le de mucho amor y tenga espacio para correr.', 'Jugueton,Carinioso,Mediano');

-- Publicacion 2: Perdido
INSERT INTO Publicacion(id, usuario_id, fechaPublicacion, tipo, urlImagen, nombreMascota, edad, ubicacion, raza, descripcion, tags)
VALUES(2, 1, '2023-10-26 18:00:00', 'PERDIDO', '/images/perro.webp', 'Luna', '5 anios', 'Caballito, CABA', 'Siames', 'Luna se perdio el 25 de Octubre por la zona del Parque Rivadavia. Es asustadiza pero no agresiva. Tiene un collar rojo con un cascabel.', 'Urgente,Recompensa');

-- Publicacion 3: Recaudacion
INSERT INTO Publicacion(id, usuario_id, fechaPublicacion, tipo, urlImagen, nombreMascota, edad, ubicacion, raza, descripcion, tags)
VALUES(3, 1, '2023-10-25 12:00:00', 'RECAUDACION', '/images/perro.webp', 'Gatitos de la calle', '1 mes', 'Moron, Buenos Aires', 'Comon Europeo', 'Necesitamos ayuda para cubrir los costos veterinarios de una camada de gatitos que rescatamos. Cualquier contribucion nos ayuda a darles un futuro.', 'Ayuda,Veterinario');

-- Publicacion 4: Adopcion
INSERT INTO Publicacion(id, usuario_id, fechaPublicacion, tipo, urlImagen, nombreMascota, edad, ubicacion, raza, descripcion, tags)
VALUES(4, 1, '2023-10-28 09:00:00', 'ADOPCION', '/images/perro.webp', 'Toto', '3 meses', 'Cordoba Capital', 'Mestizo (tamanio mediano)', 'Toto fue rescatado junto a sus hermanitos. Es muy activo y necesita una familia con paciencia y amor para enseniarle.', 'Cachorro,Activo,Desparasitado');

-- Publicacion 5: Encontrado
INSERT INTO Publicacion(id, usuario_id, fechaPublicacion, tipo, urlImagen, nombreMascota, edad, ubicacion, raza, descripcion, tags)
VALUES(5, 1, '2023-10-27 20:15:00', 'ENCONTRADO', '/images/perro.webp', 'Desconocido (Gato gris)', 'Adulto', 'Palermo, CABA', 'Sin raza definida', 'Encontre este gato macho, muy docil, en la zona de Plaza Serrano. Parece perdido, esta bien cuidado y es muy carinioso.', 'BuscaDueño,Mimoso');

-- Publicacion 6: Salud
--INSERT INTO Publicacion(id, usuario_id, fechaPublicacion, tipo, urlImagen, nombreMascota, edad, ubicacion, raza, descripcion, tags)
--VALUES(6, 1, '2023-10-27 15:45:00', 'SALUD', '/images/perro.webp', 'Brisa', 'aprox. 6 anios', 'La Plata, Buenos Aires', 'Galgo', 'Brisa necesita una consulta urgente con un traumatologo. Tiene dificultades para caminar con su pata trasera. Buscamos recomendacion.', 'Consulta,Traumatologia,Urgente');

-- Publicacion 7: Adopcion
INSERT INTO Publicacion(id, usuario_id, fechaPublicacion, tipo, urlImagen, nombreMascota, edad, ubicacion, raza, descripcion, tags)
VALUES(7, 1, '2023-10-26 11:00:00', 'ADOPCION', '/images/perro.webp', 'Negra', '4 anios', 'Rosario, Santa Fe', 'Mestiza (Labrador)', 'Negra es una perra adulta muy tranquila y obediente. Es ideal para una familia con niños o personas mayores. Se lleva bien con otros perros.', 'Adulto,Tranquila,Obediente');

-- Publicacion 8: Perdido
INSERT INTO Publicacion(id, usuario_id, fechaPublicacion, tipo, urlImagen, nombreMascota, edad, ubicacion, raza, descripcion, tags)
VALUES(8, 1, '2023-10-25 22:30:00', 'PERDIDO', '/images/perro.webp', 'Simon', '2 anios', 'Mar del Plata', 'Naranja atigrado', 'Simon se escapo de casa en la zona del puerto. Es un poco miedoso con extraños pero no es agresivo. Tiene una pequeña mancha blanca en el pecho.', 'Miedoso,Casero');

-- Publicacion 9: Recaudacion
INSERT INTO Publicacion(id, usuario_id, fechaPublicacion, tipo, urlImagen, nombreMascota, edad, ubicacion, raza, descripcion, tags)
VALUES(9, 1, '2023-10-24 14:00:00', 'RECAUDACION', '/images/perro.webp', 'Refugio "Patitas Felices"', 'Varios', 'Mendoza', 'Varios', '¡Necesitamos ayuda para comprar alimento balanceado para los más de 50 perritos que tenemos en el refugio! Cualquier donacion suma.', 'Ayuda,Refugio,Donacion');