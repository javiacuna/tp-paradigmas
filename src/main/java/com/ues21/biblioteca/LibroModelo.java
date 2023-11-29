package com.ues21.biblioteca;

public class LibroModelo {
    private int idLibro;
    private String titulo;
    private String autor;
    private String isbn;
    private int cantidadDisponible;

    public LibroModelo() {
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    @Override
    public String toString() {
        return "{" +
                "idLibro= " + idLibro +
                ", titulo= '" + titulo + '\'' +
                ", autor= '" + autor + '\'' +
                ", isbn= '" + isbn + '\'' +
                ", cantidadDisponible= " + cantidadDisponible +
                '}';
    }
}
