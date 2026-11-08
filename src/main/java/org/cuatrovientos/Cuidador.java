package org.cuatrovientos;

import java.util.ArrayList;
import java.util.Random;

public class Cuidador {

    private ArrayList<Tamagotchi> tamagotchis = new ArrayList<>();

    public Cuidador() {}

    public void crearTamagotchis(int cantidad) {

        Random random = new Random();

        for (int i = 0; i < cantidad; i++) {
            long tiempoComer = random.nextInt(5001);

            Tamagotchi tamagotchi = new Tamagotchi("Tamagotchi " + i, tiempoComer);

            tamagotchis.add(tamagotchi);

            tamagotchi.start();
        }

    }
}
