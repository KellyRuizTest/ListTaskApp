package com.example.todolistappmine;

public class Tasking {

    private String nombre;
    private int prioridad;


    public Tasking(){

        nombre = "empty";
        prioridad = -1;
    }

    public Tasking(String name) {
        this.nombre = name;
    }

    public Tasking(int prioridad) {
        this.prioridad = prioridad;
    }

    public Tasking(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}
