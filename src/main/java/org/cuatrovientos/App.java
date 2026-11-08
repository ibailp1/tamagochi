package org.cuatrovientos;

import java.util.Random;

public class App {
    public static void main(String[] args) {

        Cuidador cuidador = new Cuidador();

        Random random = new Random();

        int cantidadTamagotchis = random.nextInt(1, 4);

        cuidador.crearTamagotchis(cantidadTamagotchis);
        System.out.println("Se han creado " + cantidadTamagotchis + " tamagotchis!");


    }
}
