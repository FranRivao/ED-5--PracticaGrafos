package metro;

import java.util.Arrays;
import java.util.LinkedList;

public class Pruebas {
    public static void main(String[] args) {
        /*
        Descomentar el ejercicio que se está realizando.
        Al entregar, dejar los tres descomentados.
        * */
        ejercicio_1();
        ejercicio_2();
        ejercicio_3();
    }

    public static void ejercicio_1() {
        System.out.println("--------- Ejercicio 1 ---------\n" +
                "-- Crear Paradas --");
        Parada p1 = new Parada("Planetario", new int[]{6});
        Parada p2 = new Parada("Plaza eliptica", new int[]{6});
        System.out.println(p1);
        System.out.println(p2);
        System.out.println("-- Crear red --\n" +
                "-- Comunicar Paradas --\n" +
                "-- Información de la red de metro --");
        Metro metro = new Metro(new Parada[]{p1, p2});
        metro.comunicarParadas(p1, p2);
        metro.mostrarAmpliado();
    }

    public static void ejercicio_2() {
        System.out.println("--------- Ejercicio 2 ---------\n" +
                "-- Crear Paradas --\n" +
                "-- Listar Paradas --");
        // 0 "Planetario"
        // 1 "Plaza eliptica"
        // 2 "Moncloa"
        // 3 "Manuel Becerra"
        // 4 "Sol"
        // 5 "Puerta de alcala"
        // 6 "Ventas"
        // 7 "Cuatro Caminos"
        // 8 "Chamartin"
        String [] nombres = {"Planetario", "Plaza eliptica",  "Moncloa", "Manuel Becerra", "Sol",  "Puerta de alcala", "Ventas", "Cuatro Caminos", "Chamartin"};
        int [][] lineas = new int[][] {{6}, {6}, {6}, {2,6}, {1,2}, {2}, {2}, {1,2,6}, {1}};
        Parada [] paradas = new Parada[nombres.length];
        for (int i = 0; i < nombres.length; i++) {
            paradas[i] = new Parada(nombres[i], lineas[i]);
        }
        System.out.println(Arrays.toString(paradas));

        System.out.println("-- Crear redMetro --\n" +
                "-- Comunicar Paradas --");
        Metro metro = new Metro(paradas);
        metro.comunicarParadas(paradas[0], paradas[1]);
        metro.comunicarParadas(paradas[0], paradas[3]);
        metro.comunicarParadas(paradas[1], paradas[2]);
        metro.comunicarParadas(paradas[2], paradas[7]);
        metro.comunicarParadas(paradas[3], paradas[5]);
        metro.comunicarParadas(paradas[3], paradas[7]);
        metro.comunicarParadas(paradas[3], paradas[6]);
        metro.comunicarParadas(paradas[4], paradas[5]);
        metro.comunicarParadas(paradas[4], paradas[7]);
        metro.comunicarParadas(paradas[7], paradas[8]);
        metro.mostrarAmpliado();
        System.out.println();

        LinkedList<Parada> c = metro.conexiones(paradas[0]);
        System.out.println(Arrays.toString(c.toArray()));
        System.out.println();

        metro.mostrarConexiones();
        System.out.println();
        metro.mostrarConexionesLineas();
        System.out.println();
        int [] grados = metro.getGrados();
    }

    public static void ejercicio_3() {
        System.out.println("--------- Ejercicio 3 ---------\n" +
                "-- Crear Paradas --\n" +
                "-- Listar Paradas --");
        // 0 "Planetario"
        // 1 "Plaza eliptica"
        // 2 "Moncloa"
        // 3 "Manuel Becerra"
        // 4 "Sol"
        // 5 "Puerta de alcala"
        // 6 "Ventas"
        // 7 "Cuatro Caminos"
        // 8 "Chamartin"
        String [] nombres = {"Planetario", "Plaza eliptica",  "Moncloa", "Manuel Becerra", "Sol",  "Puerta de alcala", "Ventas", "Cuatro Caminos", "Chamartin"};
        int [][] lineas = new int[][] {{6}, {6}, {6}, {2,6}, {1,2}, {2}, {2}, {1,2,6}, {1}};
        Parada [] paradas = new Parada[nombres.length];
        for (int i = 0; i < nombres.length; i++) {
            paradas[i] = new Parada(nombres[i], lineas[i]);
        }
        System.out.println(Arrays.toString(paradas));

        System.out.println("-- Crear redMetro --\n" +
                "-- Comunicar Paradas --");
        Metro metro = new Metro(paradas);
        metro.comunicarParadas(paradas[0], paradas[1]);
        metro.comunicarParadas(paradas[0], paradas[3]);
        metro.comunicarParadas(paradas[1], paradas[2]);
        metro.comunicarParadas(paradas[2], paradas[7]);
        metro.comunicarParadas(paradas[3], paradas[5]);
        metro.comunicarParadas(paradas[3], paradas[7]);
        metro.comunicarParadas(paradas[3], paradas[6]);
        metro.comunicarParadas(paradas[4], paradas[5]);
        metro.comunicarParadas(paradas[4], paradas[7]);
        metro.comunicarParadas(paradas[7], paradas[8]);
        metro.mostrarAmpliado();
        System.out.println();

        System.out.println("--> Obras desde Manuel Becerra hacia Cuatro Caminos <--");
        metro.obrasEntreParadas(paradas[3], paradas[7]);
        metro.mostrarAmpliado();

        System.out.println("¿Se puede ir de Chamartin a Moncloa? " + metro.existeCamino(paradas[8], paradas[2]));
        System.out.println("¿Se puede ir de Plaza Eliptica a Moncloa? " + metro.existeCamino(paradas[6], paradas[2]));

        System.out.println("--> Nuevas obras");
        metro.obrasEntreParadas(paradas[1], paradas[2]);
        metro.obrasEntreParadas(paradas[3], paradas[5]);
        metro.mostrarAmpliado();

        boolean c1 =  metro.existeCamino(paradas[8], paradas[2]);
        System.out.println("¿Se puede ir de Chamartin a Moncloa? " + c1);
        boolean c2 = metro.existeCamino(paradas[1], paradas[2]);
        System.out.println("¿Se puede ir de Plaza Eliptica a Moncloa? " + c2);

        System.out.println("¿Se pueden ver en Moncloa? " + (c1 && c2));

    }
}
