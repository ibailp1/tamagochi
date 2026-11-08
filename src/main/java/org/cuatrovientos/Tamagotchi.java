package org.cuatrovientos;

import java.util.Random;

public class Tamagotchi extends Thread {

    private String nombre;
    private long tiempoComer;
    private boolean vivo = true;
    private boolean ocupado, quiereComer, quiereJugar, esperandoRespuesta, respuestaRecibida;
    private int num1, num2, respuesta;

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

    public synchronized int getNum1() {
        return num1;
    }

    public synchronized int getNum2() {
        return num2;
    }

    public synchronized boolean isEsperandoRespuesta() {
        return esperandoRespuesta;
    }

    public synchronized boolean isRespuestaRecibida() {
        return respuestaRecibida;
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

            boolean empezarJugar = false;

            synchronized (this) {
                if (quiereJugar) {
                    quiereJugar = false;

                    Random random = new Random();

                    num1 = random.nextInt(10);
                    num2 = random.nextInt(10 - num1);

                    esperandoRespuesta = true;
                    empezarJugar = true;
                }
            }

            if (empezarJugar) {
                System.out.println(nombre + ": Cuanto es " + num1 + " + " + num2 + "?");
            }

            if (esperandoRespuesta && respuestaRecibida) {

                synchronized (this) {
                    respuestaRecibida = false;

                    if (num1 + num2 == respuesta) {
                        System.out.println(nombre + ": Correcto!");
                        esperandoRespuesta = false;
                        ocupado = false;
                    } else {
                        System.out.println(nombre + ": Incorrecto,seguimos jugando!");
                    }
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

    public synchronized void morir() {
        if (ocupado) {
            System.out.println(nombre + " no puede morir, está ocupado");
            return;
        }

        vivo = false;
        interrupt();
        System.out.println(nombre + " ha muerto!");
    }

    public void morirForzado() {
        vivo = false;
        interrupt();
    }

    public synchronized void comer() {
        if (ocupado) {
            System.out.println(nombre + " no puede comer, está ocupado");
            return;
        }
        ocupado = true;
        quiereComer = true;
    }

    public synchronized void jugar() {
        if (ocupado) {
            System.out.println(nombre + " no puede jugar, está ocupado");
            return;
        }

        ocupado = true;
        quiereJugar = true;
    }

    public synchronized void responder(int respuesta) {
        if (!esperandoRespuesta) {
            System.out.println(nombre + " todavía no ha preguntado nada");
            return;
        }

        this.respuesta = respuesta;
        respuestaRecibida = true;
    }
}
