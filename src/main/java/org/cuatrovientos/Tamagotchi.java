package org.cuatrovientos;

public class Tamagotchi extends Thread {

    private String nombre;
    private long tiempoComer;
    private boolean vivo = true;
    private boolean ocupado, quiereComer;

    public Tamagotchi(String nombre, long tiempoComer) {

        this.nombre = nombre;
        this.tiempoComer = tiempoComer;
    }

    public String getNombre() {
        return nombre;
    }

    public long getTiempoComer() {
        return tiempoComer;
    }

    public boolean isVivo() {
        return vivo;
    }

    public synchronized boolean isOcupado() {
        return ocupado;
    }

    @Override
    public void run() {

        long inicioVida = System.currentTimeMillis();

        while (vivo) {
            long tiempoAhora = System.currentTimeMillis();

            long tiempoPasado = tiempoAhora - inicioVida;

            if (tiempoPasado >= 1000 * 60 * 5) {
                morir();
                break;
            }

            boolean empezarComer = false;

            synchronized (this) {
                if (quiereComer) {
                    quiereComer = false;
                    empezarComer = true;
                }
            }

            if (empezarComer) {

                System.out.println(nombre + " ha empezado a comer!");

                try {
                    Thread.sleep(tiempoComer);
                } catch (InterruptedException e) {
                    if (!vivo) {
                        break;
                    }
                    e.printStackTrace();
                }

                System.out.println(nombre + " ha terminado de comer!");

                synchronized (this) {
                    ocupado = false;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                if (!vivo) {
                    break;
                }
                e.printStackTrace();
            }

        }
    }

    public void morir() {
        vivo = false;
        interrupt();
        System.out.println(nombre + " ha muerto!");
    }

    public synchronized void comer() {

        if (!vivo) {
            System.out.println(nombre + " no puede comer, está muerto");
            return;
        }

        if (ocupado) {
            System.out.println(nombre + " no puede comer, está ocupado");
            return;
        }
        ocupado = true;
        quiereComer = true;
    }

}
