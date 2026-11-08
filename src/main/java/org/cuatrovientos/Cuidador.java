package org.cuatrovientos;

import java.util.ArrayList;
import java.util.Random;

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

    public void esperarAcciones() {

        boolean hayOcupados;

        do {
            hayOcupados = false;

            for (Tamagotchi tamagotchi : tamagotchis) {
                if (tamagotchi.isOcupado()) {
                    hayOcupados = true;
                    break;
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (hayOcupados);
    }

    public void matarTamagotchis() {
        for (int i = 0; i < tamagotchis.size(); i++) {
            Tamagotchi tamagotchi = tamagotchis.get(i);

            tamagotchi.morir();

            if (!tamagotchi.isVivo()) {
                tamagotchis.remove(i);
                i--;
            }
        }
    }

    public void forzarMuerteTamagotchis() {
        for (Tamagotchi tamagotchi : tamagotchis) {
            tamagotchi.morirForzado();
        }

        tamagotchis.clear();
    }

    public int contarTamagotchis() {
        return tamagotchis.size();
    }
}
