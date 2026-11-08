package org.cuatrovientos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tamagotchi extends Thread {

    private String nombre;
    private long tiempoComer, ultimaSubidaSuciedad, inicioVida, finVida;
    private boolean vivo = true;
    private boolean ocupado, quiereComer, quiereJugar, esperandoRespuesta, respuestaRecibida, quiereLimpiarse;
    private int num1, num2, respuesta;
    private int suciedad = 0;
    private String estado = "Ocioso";
    private List<String> mensajes = new ArrayList<>();

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

    public synchronized String getEstado() {
        return estado;
    }

    public synchronized int getSuciedad() {
        return suciedad;
    }

    public synchronized long getTiempoVida() {
        if (vivo) {
            return (System.currentTimeMillis() - inicioVida) / 1000;
        } else {
            return (finVida - inicioVida) / 1000;
        }
    }

    public synchronized List<String> getMensajes() {
        return new ArrayList<>(mensajes);
    }

    @Override
    public void run() {

        inicioVida = System.currentTimeMillis();
        ultimaSubidaSuciedad = inicioVida;

        while (vivo) {

            long tiempoAhora = System.currentTimeMillis();

            if ((tiempoAhora - inicioVida) >= 1000 * 60 * 5) {
                morir();
                break;
            }

            long tiempoSucio = System.currentTimeMillis() - ultimaSubidaSuciedad;

            if (tiempoSucio >= 20000) {
                synchronized (this) {
                    suciedad++;
                    ultimaSubidaSuciedad = System.currentTimeMillis();

                    if (suciedad == 5) {
                        mensajes.add("Está empezando a estar muy sucio!");
                    } else if (suciedad < 5) {
                        mensajes.remove("Está empezando a estar muy sucio!");
                    }

                    if (suciedad == 10) {
                        mensajes.add("Ha muerto por suciedad!");
                        vivo = false;
                        break;
                    }
                }
            }

            boolean empezarComer = false;

            synchronized (this) {
                if (quiereComer) {
                    quiereComer = false;
                    empezarComer = true;
                }
            }

            if (empezarComer) {

                synchronized (this) {
                    estado = "Comiendo";
                }

                try {
                    Thread.sleep(tiempoComer);
                } catch (InterruptedException e) {
                    if (!vivo) {
                        break;
                    }
                    e.printStackTrace();
                }

                synchronized (this) {
                    ocupado = false;
                    estado = "Ocioso";
                }
            }

            boolean empezarLimpiarse = false;

            synchronized (this) {
                if (quiereLimpiarse) {
                    quiereLimpiarse = false;
                    empezarLimpiarse = true;
                }
            }

            if (empezarLimpiarse) {
                synchronized (this) {
                    estado = "Bañándose";
                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    if (!vivo) {
                        break;
                    }
                    e.printStackTrace();
                }

                if (vivo) {

                    synchronized (this) {
                        if (suciedad >= 5) {
                            suciedad = suciedad - 5;
                        } else {
                            suciedad = 0;
                        }
                        ultimaSubidaSuciedad = System.currentTimeMillis();
                        ocupado = false;
                        estado = "Ocioso";
                    }
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
                        estado = "Ocupado";
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

    public synchronized void comer() {
        if (comprobar()) {
            ocupado = true;
            quiereComer = true;
        }
    }

    public synchronized void limpiar() {
        if (comprobar()) {
            ocupado = true;
            quiereLimpiarse = true;
        }
    }

    public synchronized void jugar() {
        if (comprobar()) {
            ocupado = true;
            quiereJugar = true;
        }
    }

    public synchronized void morir() {
        if (comprobar()) {
            vivo = false;
            estado = "Muerto";
            finVida = System.currentTimeMillis();
        }
    }

    public boolean comprobar() {
        if (!vivo) {
            return false;
        }
        if (ocupado) {
            return false;
        }
        return true;
    }

    public void forzarSalida() {
        interrupt();
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
