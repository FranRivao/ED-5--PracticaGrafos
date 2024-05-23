package tests.basic;

import metro.Metro;
import metro.Parada;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Ejercicio3 {

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
    public void obraEntreParadas(){

        Parada eliptica     = new Parada("Eliptica", new int[]{1, 6, 10});
        Parada chueca       = new Parada("Chueca", new int[] {1,10});
        Parada chamartin    = new Parada("Chamartin", new int[] {10});
        Parada planetario   = new Parada("Planetario", new int[]{6});
        Parada[] paradas    = new Parada[]{planetario, eliptica, chamartin, chueca};
        Metro miRed         = new Metro(paradas);

        miRed.comunicarParadas(planetario, eliptica);
        miRed.comunicarParadas(chamartin, eliptica);
        miRed.comunicarParadas(chueca, eliptica);
        miRed.comunicarParadas(chueca, chamartin);

        miRed.obrasEntreParadas(eliptica,chueca);

        setUpStreams();
        miRed.mostrarAmpliado();
        restoreStreams();
        String solucionEsperada="El grafo tiene una Matriz de 4 x 4\n" +
                "De un grafo dirigido\n" +
                "    0  1  2  3 \n" +
                " 0  F  T  F  F \n" +
                " 1  T  F  T  F \n" +
                " 2  F  T  F  T \n" +
                " 3  F  T  T  F \n";
        org.junit.Assert.assertEquals(
                "Falla: Ejercicio 3 | Obras entre paradas",
                solucionEsperada.replace("\r\n", "\n").replace("\r", "\n"),
                output.toString().replace("\r\n", "\n").replace("\r", "\n")
        );



        System.out.println("-- Test Ejercicio 3 | Obras entre paradas OK --");
        System.out.println("------ Test Ejercicio 3 | Obras entre paradas ------->");

    }

    public void ejercicio3_existeCamino(){
        Parada eliptica     = new Parada("Eliptica", new int[]{1, 6, 10});
        Parada chueca       = new Parada("Chueca", new int[] {1,10});
        Parada chamartin    = new Parada("Chamartin", new int[] {10});
        Parada planetario   = new Parada("Planetario", new int[]{6});
        Parada[] paradas    = new Parada[]{planetario, eliptica, chamartin, chueca};
        Metro miRed         = new Metro(paradas);

        miRed.comunicarParadas(planetario, eliptica);
        miRed.comunicarParadas(chamartin, eliptica);
        miRed.comunicarParadas(chueca, eliptica);
        miRed.comunicarParadas(chueca, chamartin);



        boolean solucionEsperada=true;
        boolean solucion = miRed.existeCamino(planetario, chamartin);
        org.junit.Assert.assertEquals(
                "Falla: Ejercicio 3 | Obras entre paradas",
                solucionEsperada,
                solucion
        );
        miRed.obrasEntreParadas(eliptica,chueca);
        solucion = miRed.existeCamino(planetario, chamartin);
        solucionEsperada = false;
        org.junit.Assert.assertEquals(
                "Falla: Ejercicio 3 | Obras entre paradas",
                solucionEsperada,
                solucion
        );


    }

    @org.junit.Test
    public void ejercicio3_salidaCompleta(){
        setUpStreams();
        output.reset();
        metro.Pruebas.ejercicio_3();
        restoreStreams();

        String outputSolucion = "--------- Ejercicio 3 ---------\n" +
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
                "--> Obras desde Manuel Becerra hacia Cuatro Caminos <--\n" +
                "El grafo tiene una Matriz de 9 x 9\n" +
                "De un grafo dirigido\n" +
                "    0  1  2  3  4  5  6  7  8 \n" +
                " 0  F  T  F  T  F  F  F  F  F \n" +
                " 1  T  F  T  F  F  F  F  F  F \n" +
                " 2  F  T  F  F  F  F  F  T  F \n" +
                " 3  T  F  F  F  F  T  T  F  F \n" +
                " 4  F  F  F  F  F  T  F  T  F \n" +
                " 5  F  F  F  T  T  F  F  F  F \n" +
                " 6  F  F  F  T  F  F  F  F  F \n" +
                " 7  F  F  T  T  T  F  F  F  T \n" +
                " 8  F  F  F  F  F  F  F  T  F \n" +
                "¿Se puede ir de Chamartin a Moncloa? true\n" +
                "¿Se puede ir de Plaza Eliptica a Moncloa? true\n" +
                "--> Nuevas obras\n" +
                "El grafo tiene una Matriz de 9 x 9\n" +
                "De un grafo dirigido\n" +
                "    0  1  2  3  4  5  6  7  8 \n" +
                " 0  F  T  F  T  F  F  F  F  F \n" +
                " 1  T  F  F  F  F  F  F  F  F \n" +
                " 2  F  T  F  F  F  F  F  T  F \n" +
                " 3  T  F  F  F  F  F  T  F  F \n" +
                " 4  F  F  F  F  F  T  F  T  F \n" +
                " 5  F  F  F  T  T  F  F  F  F \n" +
                " 6  F  F  F  T  F  F  F  F  F \n" +
                " 7  F  F  T  T  T  F  F  F  T \n" +
                " 8  F  F  F  F  F  F  F  T  F \n" +
                "¿Se puede ir de Chamartin a Moncloa? true\n" +
                "¿Se puede ir de Plaza Eliptica a Moncloa? false\n" +
                "¿Se pueden ver en Moncloa? false\n";

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
