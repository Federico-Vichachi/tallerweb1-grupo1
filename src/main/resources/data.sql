-- Insertar domicilio primero
INSERT INTO Domicilio(id, calle, numero, ciudad, provincia, codigoPostal, departamento, piso)
VALUES(1, 'Av. Siempre Viva', '1234', 'Buenos Aires', 0, '1043', null, null);

-- Insertar usuario con referencia al domicilio
INSERT INTO Usuario(id, nombre, apellido, email, telefono, contrasenia, nombreDeUsuario, urlFotoDePerfil, rol, domicilio_id)
VALUES(1, 'Juan', 'Perez', 'test@gmail.com', '123456789', 'Contrasenia@2025', 'juanperez', null, 0, 1);

