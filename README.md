# Ejercicio 4 - Tamagotchis

## Descripción

Programa desarrollado en Java para simular el cuidado de varios Tamagotchis utilizando hilos (`Thread`).

Cada Tamagotchi funciona de manera independiente al resto y al programa principal. El cuidador puede interactuar con ellos para darles de comer, jugar, limpiarlos, consultar su estado o intentar matarlos.

El objetivo del ejercicio es trabajar con hilos y procesos concurrentes en Java.

## Funcionamiento

Al iniciar el programa se crean aleatoriamente entre 1 y 3 Tamagotchis.

Cada uno tiene:
- Un nombre para identificarlo.
- Un tiempo de alimentación diferente.
- Un tiempo de vida de 5 minutos.
- Un nivel de suciedad entre 0 y 10.
- Un estado que indica qué está haciendo.

Cada Tamagotchi se ejecuta en su propio hilo.

## Menú principal

El programa dispone de las siguientes opciones:

| Opción | Descripción |
|---|---|
| 1 | Alimentar a los Tamagotchis |
| 2 | Jugar con ellos |
| 3 | Limpiarlos |
| 4 | Consultar sus estados |
| 5 | Intentar matarlos |
| 6 | Salir del programa |

## Acciones de los Tamagotchis

### Alimentación

Cada Tamagotchi tiene asignado un tiempo de alimentación aleatorio.

Cuando recibe comida, comienza a comer y tarda el tiempo que tiene establecido. Al terminar, vuelve a estar disponible para realizar otra acción.

### Juego

Los Tamagotchis generan sumas aleatorias de dos números de una cifra cuyo resultado es inferior a 10.

El cuidador tiene que introducir la respuesta. Si es incorrecta, el Tamagotchi continúa jugando hasta recibir una respuesta correcta.

### Limpieza

Los Tamagotchis acumulan suciedad con el paso del tiempo.

- Cada 20 segundos aumenta un punto de suciedad.
- Al llegar a 5 puntos avisan de que empiezan a estar muy sucios.
- Al alcanzar 10 puntos mueren.
- Para limpiarse necesitan 5 segundos.
- Cuando terminan de limpiarse, su suciedad vuelve a cero.

### Tiempo de vida

Cada Tamagotchi tiene un tiempo de vida de 5 minutos.

Cuando se cumple ese tiempo, avisa de que ha llegado al final de su vida y muere.

### Eliminación

El cuidador puede solicitar la eliminación de los Tamagotchis.

Estos solamente atenderán la petición cuando estén ociosos, es decir, cuando no estén realizando ninguna acción.

## Clases del proyecto

El proyecto está dividido en tres clases:

### App.java

Es la clase principal del programa.

Se encarga de crear el cuidador, iniciar los Tamagotchis, mostrar el menú y recoger las opciones introducidas por el usuario.

### Cuidador.java

Es la clase encargada de administrar los Tamagotchis.

Se encarga de crearlos, enviarles órdenes, consultar sus estados y solicitar su eliminación.

Para almacenar los Tamagotchis utiliza un `ArrayList`.

### Tamagotchi.java

Representa a cada Tamagotchi y hereda de la clase `Thread`.

Cada objeto tiene su propio hilo y controla sus acciones, su suciedad, su tiempo de vida y su estado.

## Ejecución del programa

Para ejecutar el programa:

1. Abrir el proyecto en un entorno de desarrollo compatible con Java.
2. Buscar la clase `App.java`.
3. Ejecutar el método `main()`.
4. Utilizar el menú para interactuar con los Tamagotchis.

## Objetivo

Practicar la creación y utilización de hilos en Java, comprender cómo funcionan los procesos concurrentes y aprender a controlar el acceso a los recursos compartidos mediante la sincronización.
