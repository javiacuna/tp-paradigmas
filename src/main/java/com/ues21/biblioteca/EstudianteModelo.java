package com.ues21.biblioteca;

public class EstudianteModelo {
    private int idEstudiante;
    private String nombre;
    private String apellido;
    private String carnet;

    public EstudianteModelo() {
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    @Override
    public String toString() {
        return "{" +
                "ID= " + idEstudiante +
                ", nombre= '" + nombre + '\'' +
                ", apellido= '" + apellido + '\'' +
                ", carnet= '" + carnet + '\'' +
                '}';
    }

}
