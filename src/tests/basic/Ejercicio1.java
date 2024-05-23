/*
>> No modificar <<
Estos tests comprueban la resolución correcta de los ejercicios propuestos para los ejemplos del enunciado.
 */

package tests.basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import metro.Metro;
import metro.Parada;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Ejercicio1 {
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
    /*
    Descomentar @Test cuando se haya completado:
    - Declaración de paradas
    - setLineas
    - setNombre
    - getLineas
    - getNombre
    - getNumLineas
    - toString
     */
    @Test
    public void ejercicio1_testParada(){
        restoreStreams();
        Parada planetario = new Parada("Planetario", new int[] {6});
        Parada eliptica = new Parada("", new int[]{});
        eliptica.setNombre("Plaza Elíptica");
        eliptica.setLineas(new int[] {6});

        System.out.println("------------------ CHECK ----------------------");
        System.out.println("-- Iniciando Asserts --");
        System.out.println(String.format("Asserts %s ", planetario.toString()));
        org.junit.Assert.assertEquals(
                "Falla: nombre parada 1",
                "Planetario",
                planetario.getNombre()
        );
        org.junit.Assert.assertArrayEquals(
                "Falla array de líneas 1",
                new int[] {6},
                planetario.getLineas()
        );
        org.junit.Assert.assertEquals(
                "Falla: toString 1",
                "Parada{Planetario Líneas: [6], 1}",
                planetario.toString()
        );

        System.out.println(String.format("Asserts %s ", eliptica.toString()));

        org.junit.Assert.assertEquals(
                "Falla: nombre parada 2",
                "Plaza Elíptica",
                eliptica.getNombre()
        );
        org.junit.Assert.assertArrayEquals(
                "Falla array de líneas 2",
                new int[] {6},
                eliptica.getLineas()
        );

        org.junit.Assert.assertEquals(
                "Falla: toString 2",
                "Parada{Plaza Elíptica Líneas: [6], 1}",
                eliptica.toString()
        );

        System.out.println("-- Test Ejercicio 1 | Instanciar parada OK --");
        System.out.println("------ Test Ejercicio 1 | Instanciar parada  ------->");
    }

    /*
        Descomentar @Test cuando se haya completado el ejercicio 1 al completo.
        Comprueba el print completo
    */
    @Test
    public void ejercicio1_comunicarParadas(){
        /*
        Check de que se ha definido y que los parámetros de entrada son los adecuados
        para comunicarParadas
         */
        restoreStreams();
        System.out.println("--------- Ejercicio 1 ---------");
        System.out.println("-- Crear Paradas --");
        int[] lineas = new int[] {6};
        Parada planetario = new Parada("Planetario", lineas);
        System.out.println(planetario.toString());

        Parada eliptica = new Parada("Plaza Elíptica", new int[] {6});
        System.out.println(eliptica.toString());

        System.out.println("-- Crear red --");
        Parada[] paradas = new Parada[] {planetario, eliptica};
        Metro miRed = new Metro(paradas);
        System.out.println("-- Comunicar Paradas --");
        miRed.comunicarParadas(planetario,eliptica);
    }

    /*
    Descomentar @Test cuando se haya completado el ejercicio 1 al completo.
    Comprueba el print completo
     */
    @Test
    public void ejercicio1_salidaCompleta(){
        setUpStreams();
        output.reset();
        metro.Pruebas.ejercicio_1();
        restoreStreams();

        String outputSolucion = "--------- Ejercicio 1 ---------\n" +
                "-- Crear Paradas --\n" +
                "Parada{Planetario Líneas: [6], 1}\n" +
                "Parada{Plaza Elíptica Líneas: [6], 1}\n" +
                "-- Crear red --\n" +
                "-- Comunicar Paradas --\n" +
                "-- Información de la red de metro --\n" +
                "El grafo tiene una Matriz de 2 x 2\n" +
                "De un grafo No dirigido\n" +
                "    0  1 \n" +
                " 0  F  T \n" +
                " 1  T  F \n";


        System.out.println("---------------- OBTAINED --------------------");
        System.out.println(output.toString());
        System.out.println("---------------- EXPECTED --------------------");
        System.out.println(outputSolucion);
        System.out.println("------------------ CHECK ----------------------");
        System.out.println("-- Iniciando Assert True --");

        org.junit.Assert.assertEquals(
                "Falla: Ejercicio 1",
                outputSolucion.replace("\r\n", "\n").replace("\r", "\n"),
                output.toString().replace("\r\n", "\n").replace("\r", "\n")
        );
        System.out.println("-- Test Ejercicio 1 OK --");
        System.out.println("------ Test Ejercicio 1 ------->");

    }

}
