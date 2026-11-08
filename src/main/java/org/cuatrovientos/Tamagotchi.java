package org.cuatrovientos;

public class Tamagotchi extends Thread {

    private String nombre;
    private long tiempoComer;
    private boolean vivo = true;

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

        long inicioVida = System.currentTimeMillis();

        System.out.println(getNombre() + " ha nacido!");

        while (vivo) {
            long tiempoAhora = System.currentTimeMillis();

            long tiempoPasado = tiempoAhora - inicioVida;

            if (tiempoPasado >= 1000 * 60 * 5) {
                morir();
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void morir() {
        vivo = false;
        System.out.println(getNombre() + " ha muerto!");
    }
}
