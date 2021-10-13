package com.example.model;

import java.io.Serializable;

public class Message implements Serializable {
    String nombre;
    int intentos;

    public Message(){}

    public Message(String nombre, int intentos) {
        this.nombre = nombre;
        this.intentos = intentos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    @Override
    public String toString() {
        return "Message{" +
                "nombre='" + nombre + '\'' +
                ", intentos=" + intentos +
                '}';
    }
}
