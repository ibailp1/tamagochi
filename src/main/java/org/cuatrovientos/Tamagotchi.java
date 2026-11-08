package org.cuatrovientos;

public class Tamagotchi extends Thread {

    private String nombre;
    private long tiempoComer;

    public Tamagotchi(String nombre, long tiempoComer) {

        this.nombre = nombre;
        this.tiempoComer = tiempoComer;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTiempoComer() {
        return tiempoComer;
    }

    public void setTiempoComer(long tiempoComer) {
        this.tiempoComer = tiempoComer;
    }

    @Override
    public void run() {
        System.out.println(getNombre() + " ha nacido!");
    }
}
