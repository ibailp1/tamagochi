package org.cuatrovientos;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cuidador cuidador = new Cuidador();

        Random random = new Random();

        int cantidadCreacionTamagotchis = random.nextInt(1, 4);

        cuidador.crearTamagotchis(cantidadCreacionTamagotchis);
        System.out.println("Se han creado " + cantidadCreacionTamagotchis + " tamagotchis!");

        boolean salir = false;
        while (!salir) {
            int cantidadTamagotchis = cuidador.contarTamagotchis();
            if (cantidadTamagotchis == 0) {
                cantidadCreacionTamagotchis = random.nextInt(1, 4);

                cuidador.crearTamagotchis(cantidadCreacionTamagotchis);
                System.out.println("Se han creado " + cantidadCreacionTamagotchis + " tamagotchis!");
            }
            System.out.println("\nMENÚ TAMAGOTCHIS");
            System.out.println("1. Alimentarlos");
            System.out.println("2. Jugar con ellos");
            System.out.println("3. Limpiarlos");
            System.out.println("4. Ver sus estados");
            System.out.println("5. Matarlos");
            System.out.println("6. Salir");

            String txt;
            int opcion = 0;
            boolean esInt;
            do {
                System.out.print("\nElige una opción: ");
                txt = scanner.nextLine();
                try {
                    opcion = Integer.parseInt(txt);
                    esInt = true;
                } catch (NumberFormatException e) {
                    System.out.println("¡Error, debes de introducir un opción válida!");
                    esInt = false;
                }
            } while (!esInt || opcion > 6 || opcion < 1);

            switch (opcion) {
                case 1:
                    cuidador.darDeComerTamagatochis();
                    cuidador.esperarAcciones();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    cuidador.matarTamagotchis();
                    break;
                case 6:
                    cuidador.forzarMuerteTamagotchis();
                    salir = true;
                    break;
            }
        }
        System.out.println("Has finalizado el programa...");
        if (scanner != null) {
            scanner.close();
        }
    }
}
