CREATE TABLE Libro (
    idLibro INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    isbn VARCHAR(13) NOT NULL,
    cantidadDisponible INT NOT NULL
);

CREATE TABLE Prestamo (
    idPrestamo INT PRIMARY KEY AUTO_INCREMENT,
    idEstudiante INT NOT NULL,
    idLibro INT NOT NULL,
    fechaPrestamo DATE NOT NULL,
    fechaDevolucion DATE,
    estado VARCHAR(100) NOT NULL,
    FOREIGN KEY (idEstudiante) REFERENCES Estudiante(idEstudiante),
    FOREIGN KEY (idLibro) REFERENCES Libro(idLibro)
);

CREATE TABLE Estudiante (
    idEstudiante INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    carnet VARCHAR(10) NOT NULL UNIQUE
);
