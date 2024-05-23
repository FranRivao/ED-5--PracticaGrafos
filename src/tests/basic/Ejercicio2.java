/*
>> No modificar <<
Estos tests comprueban la resolución correcta de los ejercicios propuestos para los ejemplos del enunciado.
 */

package tests.basic;

import metro.Metro;
import metro.Parada;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

public class Ejercicio2 {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    @Before
    public void setUpStreams(){
        System.setOut(new PrintStream(output));
    }
    private final PrintStream originalOut = System.out;
    @After
    public void restoreStreams(){
        System.setOut(originalOut);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setIn(null);
    }


    @org.junit.Test
    public void ejercicio2Conexiones1(){
        restoreStreams();
        int[] lineas = new int[] {6};
        Parada planetario = new Parada("Planetario", lineas);
        Parada eliptica = new Parada("Plaza Elíptica", new int[] {6});
        Parada[] paradas = new Parada[] {planetario, eliptica};
        Metro miRed = new Metro(paradas);
        miRed.comunicarParadas(planetario,eliptica);
        LinkedList<Parada> alumno = miRed.conexiones(planetario);
        LinkedList<Parada> solucion = new LinkedList();
        solucion.add(eliptica);

        System.out.println("-- Iniciando Assert True --");

        org.junit.Assert.assertEquals(
                "Falla: Ejercicio 2 | Conexiones1",
                solucion,
                alumno
        );
        System.out.println("-- Test Ejercicio 2 OK --");
        System.out.println("------ Test Ejercicio 2 ------->");

    }

    @org.junit.Test
    public void ejercicio2getConexiones1() {
        restoreStreams();
        int[] lineas = new int[]{6};
        Parada planetario = new Parada("Planetario", lineas);
        Parada eliptica = new Parada("Plaza Elíptica", new int[]{6});
        Parada[] paradas = new Parada[]{planetario, eliptica};
        Metro miRed = new Metro(paradas);
        miRed.comunicarParadas(planetario, eliptica);
        LinkedList<LinkedList<Parada>> alumno = miRed.getConexiones();
        LinkedList<Parada> solucionDentro1 = new LinkedList<>();
        LinkedList<Parada> solucionDentro2 = new LinkedList<>();
        LinkedList<LinkedList<Parada>> solucion = new LinkedList<LinkedList<Parada>>();
        solucionDentro1.add(eliptica);
        solucionDentro2.add(planetario);
        solucion.add(solucionDentro1);
        solucion.add(solucionDentro2);

        System.out.println("-- Iniciando Assert True --");

        org.junit.Assert.assertEquals(
                "Falla: Ejercicio 2 | Conexiones4",
                solucion,
                alumno
        );
        System.out.println("-- Test Ejercicio 2 | Get Conexiones OK --");
        System.out.println("------ Test Ejercicio 2 | Get Conexiones ------->");
    }

    @org.junit.Test
    public void mostrarConexiones1(){
        setUpStreams();
        int[] lineas = new int[]{6};
        Parada planetario = new Parada("Planetario", lineas);
        Parada eliptica = new Parada("Plaza Elíptica", new int[]{6});
        Parada[] paradas = new Parada[]{planetario, eliptica};
        Metro miRed = new Metro(paradas);
        miRed.comunicarParadas(planetario, eliptica);
        miRed.mostrarConexiones();
        restoreStreams();

        String outputSolucion = "0: Planetario conecta con: Plaza Elíptica \n" +
                "1: Plaza Elíptica conecta con: Planetario \n";

        System.out.println("---------------- OBTAINED --------------------");
        System.out.println(output.toString());
        System.out.println("---------------- EXPECTED --------------------");
        System.out.println(outputSolucion);
        System.out.println("------------------ CHECK ----------------------");
        System.out.println("-- Iniciando Assert True --");

        org.junit.Assert.assertEquals(
                "Falla: Ejercicio 2 | Mostrar Conexiones",
                outputSolucion.replace("\r\n", "\n").replace("\r", "\n"),
                output.toString().replace("\r\n", "\n").replace("\r", "\n")
        );
        System.out.println("-- Test Ejercicio 2 OK --");
        System.out.println("------ Test Ejercicio 2 | Mostrar Conexiones ------->");

    }

    @org.junit.Test
    public void mostrarConexionesLineas1(){
        setUpStreams();
        int[] lineas = new int[]{6};
        Parada planetario = new Parada("Planetario", lineas);
        Parada eliptica = new Parada("Plaza Elíptica", new int[]{6});
        Parada[] paradas = new Parada[]{planetario, eliptica};
        Metro miRed = new Metro(paradas);
        miRed.comunicarParadas(planetario, eliptica);
        miRed.mostrarConexionesLineas();
        restoreStreams();

        String outputSolucion = "0: Planetario conecta con: Plaza Elíptica ([6]) \n" +
                "1: Plaza Elíptica conecta con: Planetario ([6]) \n";

        System.out.println("---------------- OBTAINED --------------------");
        System.out.println(output.toString());
        System.out.println("---------------- EXPECTED --------------------");
        System.out.println(outputSolucion);
        System.out.println("------------------ CHECK ----------------------");
        System.out.println("-- Iniciando Assert True --");

        org.junit.Assert.assertEquals(
                "Falla: Ejercicio 2 | Mostrar Conexiones Lineas",
                outputSolucion.replace("\r\n", "\n").replace("\r", "\n"),
                output.toString().replace("\r\n", "\n").replace("\r", "\n")
        );
        System.out.println("-- Test Ejercicio 2 | Mostrar Conexiones Lineas OK --");
        System.out.println("------ Test Ejercicio 2 | Mostrar Conexiones Lineas ------->");

    }

    @org.junit.Test
    public void getGrados(){
        restoreStreams();
        int[] lineas = new int[]{6};
        Parada planetario = new Parada("Planetario", lineas);
        Parada eliptica = new Parada("Plaza Elíptica", new int[]{6});
        Parada chamartin = new Parada("Plaza Elíptica", new int[]{6,10});
        Parada[] paradas = new Parada[]{planetario, eliptica, chamartin};
        Metro miRed = new Metro(paradas);
        miRed.comunicarParadas(planetario, eliptica);
        miRed.comunicarParadas(chamartin, eliptica);
        int[] gradosSolucion =  miRed.getGrados();
        int[] gradosEsperados = new int[]{1,2,1};


        org.junit.Assert.assertArrayEquals(
                "Falla: Ejercicio 2 | Get Grados",
                gradosSolucion,
                gradosEsperados
        );
        System.out.println("-- Test Ejercicio 2 | Get grados OK --");
        System.out.println("------ Test Ejercicio 2 | Get grados ------->");

    }

    @org.junit.Test
    public void ejercicio2_salidaCompleta(){
        setUpStreams();
        output.reset();
        metro.Pruebas.ejercicio_2();
        restoreStreams();

        String outputSolucion = "--------- Ejercicio 2 ---------\n" +
                "-- Crear Paradas -- \n" +
                "-- Listar Paradas -- \n" +
                " Paradas (Usando Arrays.toString): \n" +
                "[Parada{Planetario Líneas: [6], 1}, Parada{Plaza Elíptica Líneas: [6], 1}, Parada{Moncloa Líneas: [6], 1}, Parada{Manuel Becerra Líneas: [6, 2], 2}, Parada{Sol Líneas: [1, 2], 2}, Parada{Alcala Líneas: [2], 1}, Parada{Ventas Líneas: [2], 1}, Parada{Cuatro Caminos Líneas: [1, 2, 6], 3}, Parada{Chamartin Líneas: [1], 1}]\n" +
                "-- Crear redMetro -- \n" +
                "-- Comunicar Paradas -- \n" +
                "Numero de paradas: 9\n" +
                "El grafo tiene una Matriz de 9 x 9\n" +
                "De un grafo No dirigido\n" +
                "    0  1  2  3  4  5  6  7  8 \n" +
                " 0  F  T  F  T  F  F  F  F  F \n" +
                " 1  T  F  T  F  F  F  F  F  F \n" +
                " 2  F  T  F  F  F  F  F  T  F \n" +
                " 3  T  F  F  F  F  T  T  T  F \n" +
                " 4  F  F  F  F  F  T  F  T  F \n" +
                " 5  F  F  F  T  T  F  F  F  F \n" +
                " 6  F  F  F  T  F  F  F  F  F \n" +
                " 7  F  F  T  T  T  F  F  F  T \n" +
                " 8  F  F  F  F  F  F  F  T  F \n" +
                "Conexiones con Plaza Elíptica\n" +
                "[Parada{Plaza Elíptica Líneas: [6], 1}, Parada{Manuel Becerra Líneas: [6, 2], 2}]\n" +
                "--> Todas las conexiones de mi red de metro <--\n" +
                "0: Planetario conecta con: Plaza Elíptica Manuel Becerra \n" +
                "1: Plaza Elíptica conecta con: Planetario Moncloa \n" +
                "2: Moncloa conecta con: Plaza Elíptica Cuatro Caminos \n" +
                "3: Manuel Becerra conecta con: Planetario Alcala Ventas Cuatro Caminos \n" +
                "4: Sol conecta con: Alcala Cuatro Caminos \n" +
                "5: Alcala conecta con: Manuel Becerra Sol \n" +
                "6: Ventas conecta con: Manuel Becerra \n" +
                "7: Cuatro Caminos conecta con: Moncloa Manuel Becerra Sol Chamartin \n" +
                "8: Chamartin conecta con: Cuatro Caminos \n" +
                "--> Todas las conexiones de mi red de metro (incluye líneas) <--\n" +
                "0: Planetario conecta con: Plaza Elíptica ([6]) Manuel Becerra ([6]) \n" +
                "1: Plaza Elíptica conecta con: Planetario ([6]) Moncloa ([6]) \n" +
                "2: Moncloa conecta con: Plaza Elíptica ([6]) Cuatro Caminos ([6]) \n" +
                "3: Manuel Becerra conecta con: Planetario ([6]) Alcala ([2]) Ventas ([2]) Cuatro Caminos ([6, 2]) \n" +
                "4: Sol conecta con: Alcala ([2]) Cuatro Caminos ([1, 2]) \n" +
                "5: Alcala conecta con: Manuel Becerra ([2]) Sol ([2]) \n" +
                "6: Ventas conecta con: Manuel Becerra ([2]) \n" +
                "7: Cuatro Caminos conecta con: Moncloa ([6]) Manuel Becerra ([2, 6]) Sol ([1, 2]) Chamartin ([1]) \n" +
                "8: Chamartin conecta con: Cuatro Caminos ([1]) \n" +
                "--> Grados de los vértices <--\n" +
                "Grado Planetario: 2\n" +
                "Grado Plaza Elíptica: 2\n" +
                "Grado Moncloa: 2\n" +
                "Grado Manuel Becerra: 4\n" +
                "Grado Sol: 2\n" +
                "Grado Alcala: 2\n" +
                "Grado Ventas: 1\n" +
                "Grado Cuatro Caminos: 4\n" +
                "Grado Chamartin: 1\n";


        System.out.println("---------------- OBTAINED --------------------");
        System.out.println(output.toString());
        System.out.println("---------------- EXPECTED --------------------");
        System.out.println(outputSolucion);
        System.out.println("------------------ CHECK ----------------------");
        System.out.println("-- Iniciando Assert True --");

        org.junit.Assert.assertEquals(
                "Falla: Ejercicio 2",
                outputSolucion.replace("\r\n", "\n").replace("\r", "\n"),
                output.toString().replace("\r\n", "\n").replace("\r", "\n")
        );
        System.out.println("-- Test Ejercicio 2 OK --");
        System.out.println("------ Test Ejercicio 2 ------->");

    }
}

