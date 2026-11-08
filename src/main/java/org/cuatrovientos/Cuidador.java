package org.cuatrovientos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Cuidador {

    private ArrayList<Tamagotchi> tamagotchis = new ArrayList<>();

    public Cuidador() {
    }

    public void crearTamagotchis(int cantidad) {

        Random random = new Random();

        for (int i = 0; i < cantidad; i++) {
            long tiempoComer = random.nextInt(5001);

            Tamagotchi tamagotchi = new Tamagotchi("Tamagotchi " + i, tiempoComer);

            tamagotchis.add(tamagotchi);

            System.out.println(tamagotchi.getNombre() + " ha nacido!");

            tamagotchi.start();
        }

    }

    public void darDeComerTamagatochis() {
        
        for (Tamagotchi tamagotchi : tamagotchis) {
            tamagotchi.comer();
        }
    }

    public void limpiarTamagotchis() {
        for (Tamagotchi tamagotchi : tamagotchis) {
            tamagotchi.limpiar();
        }

    }

    public void jugarConTamagotchis(Scanner scanner) {

        for (Tamagotchi tamagotchi : tamagotchis) {

            tamagotchi.jugar();

            while (tamagotchi.isOcupado() && tamagotchi.isVivo()) {

                if (tamagotchi.isEsperandoRespuesta()) {

                    String txt;
                    int respuesta = 0;
                    boolean esInt;
                    do {
                        System.out.print("Respuesta: ");
                        txt = scanner.nextLine();

                        try {
                            respuesta = Integer.parseInt(txt);
                            esInt = true;
                        } catch (NumberFormatException e) {
                            System.out.println("¡Error, debes introducir un número válido!");
                            esInt = false;
                        }

                    } while (!esInt);

                    tamagotchi.responder(respuesta);

                    while (tamagotchi.isRespuestaRecibida()) {
                    }
                }

            }
        }
    }

    

    public void mostrarEstados() {

        System.out.println("\n### ESTADO DE LOS TAMAGOTCHIS ###");
        for (Tamagotchi tamagotchi : tamagotchis) {

            System.out.println("\n" + tamagotchi.getNombre());
            System.out.println();
            System.out.println("Estado: " + tamagotchi.getEstado());
            System.out.println("Vivo: " + tamagotchi.isVivo());
            System.out.println("Suciedad: " + tamagotchi.getSuciedad() + "/10");
            System.out.println("Tiempo de vida: " + tamagotchi.getTiempoVida() + " segundos");
            System.out.println("TIempo que tarda en comer: " + tamagotchi.getTiempoComer() + " ms");
            if (tamagotchi.getMensajes().size() != 0) {
                for (String mensaje : tamagotchi.getMensajes()) {
                    System.out.println(mensaje);
                }
            }
        }
    }

    
    public void matarTamagotchis() {
        for (Tamagotchi tamagotchi : tamagotchis) {
            tamagotchi.morir();
        }
    }

    public void eliminarTodos() {
        for (Tamagotchi tamagotchi : tamagotchis) {
            tamagotchi.forzarSalida();
        }
        tamagotchis.clear();
    }
}
