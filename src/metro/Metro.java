/*
    Nombre y apellidos:
    Grupo:
 */
package metro;

import java.util.Arrays;
import java.util.LinkedList;

public class Metro {
    /**************
    * EJERCICIO 1 *
    * *************/
    /*TODO (1.3)
    Definir e instanciar a valor predeterminado los atributos propusestos
    - redMetro: Grafo que contiene las paradas de la red de metro seleccionadas 
    - parada: vector de vértices del grafo
    - numParadas: número de paradas/vértices en el grafo (paradas.length)
    * */
    //...
    private GrafoMA redMetro;
    private Parada[] paradas;
    private int numParadas;

    //TODO (1.3) Constructor.
    public Metro(Parada[] paradas){
        this.paradas = paradas;
        this.numParadas = paradas.length;
        this.redMetro = new GrafoMA(numParadas, false);
    }

    /*
    TODO (1.3)
    Debe devolver
     * -1 si la parada no está (Vendrá bien despues)
     * la posición en paradas[] en caso contrario.
     */
    public int indiceParada(Parada parada){
        boolean encontrada = false;
        int contador = 0;
        while (contador < numParadas && !encontrada) {
            if (paradas[contador] == parada) {
                encontrada = true;
            } else contador++;
        }

        return encontrada ? contador : -1;
    }
    /*
    TODO (1.3)
    Usar:
    - GrafoMA.insertarArista
    Cuidado, las paradas podrían venir como null. En ese caso, no hacer nada con el grafo.
    Si las paradas no están en el grafo, tampoco lo modificamos.
    No imprimimos mensaje de error, símplemente lo dejamos quieto.
     */
    public void comunicarParadas(Parada origen, Parada destino) {
        int o = indiceParada(origen), d = indiceParada(destino);
        if (origen != null && destino != null && o != -1 && d != -1) {
            redMetro.insertarArista(o, d);
        }
    }

    //TODO (1.3) Modificar para que devuelva el atributo asociado
    public int getNumParadas(){return numParadas;}
    //TODO (1.3) Usar GrafoMA.mostrarAmpliado
    public void mostrarAmpliado(){
        redMetro.mostrarampliado();
    }
    /************
     * EJERCICIO 2 *
    * *************/
    //TODO (2.4)
    public LinkedList<Parada> conexiones(Parada parada){
        int indice = indiceParada(parada);

        LinkedList<Parada> conexiones = new LinkedList<>();
        if (indice != -1) {
            for (int i = 0; i < numParadas; i++) {
                if (redMetro.existeArista(indice, i)) {
                    conexiones.add(paradas[i]);
                }
            }
            return conexiones;
        }
        return null;
    }
    //TODO (2.4)
    public LinkedList<LinkedList <Parada>> getConexiones(){
        LinkedList<LinkedList <Parada>> c = new LinkedList<>();
        for (int i = 0; i < numParadas; i++) {
            c.add(conexiones(paradas[i]));
        }
        return c;
    }
    //TODO (2.5)
    public void mostrarConexiones(){
        LinkedList<LinkedList <Parada>> conexiones = getConexiones();
        for (int i = 0; i < numParadas; i++) {
            System.out.print(i + ": " + paradas[i].getNombre() + " conecta con: ");
            for (int f = 0; f < conexiones.get(i).size(); f++) {
                System.out.print(conexiones.get(i).get(f).getNombre() + " ");
            }
            System.out.println();
        }
    }
    //TODO(2.6)
    /*
    //Descomentar y completar si es de utilidad
    public static LinkedList<Integer> lineasComunes(int[] lineas1, int[] lineas2){return new LinkedList<>();}
     */
    //TODO(2.6)
    public LinkedList<Integer> lineasComunes(Parada parada1, Parada parada2){
        LinkedList<Integer> comunes = new LinkedList<>();
        int [] p1 =  parada1.getLineas(), p2 =  parada2.getLineas();

        for (int i = 0; i < parada1.getNumLineas(); i++) {
            for (int f = 0; f < parada2.getNumLineas(); f++) {
                if (p1[i] == p2[f]) {
                    comunes.add(p1[i]);
                }
            }
        }

        return comunes;
    }
    public void mostrarConexionesLineas(){
        LinkedList<LinkedList <Parada>> conexiones = getConexiones();
        for (int i = 0; i < numParadas; i++) {
            System.out.print(i + ": " + paradas[i].getNombre() + " conecta con: ");
            for (int f = 0; f < conexiones.get(i).size(); f++) {
                System.out.print(conexiones.get(i).get(f).getNombre() + "(" + Arrays.toString(lineasComunes(paradas[i], conexiones.get(i).get(f)).toArray()) + ") ");
            }
            System.out.println();
        }
    }
    //TODO(2.8)
    public int[] getGrados() {
        int [] grados = new int[numParadas];
        for (int i = 0; i < numParadas; i++) {
            grados[i] = (redMetro.gradoEntrada(i) + redMetro.gradoSalida(i))/2;
            System.out.println("Grado " + paradas[i].getNombre() + ": " + grados[i]);
        }
        return grados;
    }
    //TODO(3.2)
    private void hacerDirigido() {
        if (!redMetro.getDirigido()) {
            GrafoMA nuevo = new GrafoMA(numParadas, true);
            for (int i = 0; i < numParadas; i++) {
                for (int f = 0; f < numParadas; f++) {
                    if (i != f && !nuevo.existeArista(i,f) && redMetro.existeArista(i,f)) {
                        nuevo.insertarArista(i,f);
                    }
                }
            }
            redMetro = nuevo;
        }
    }
    //TODO(3.2)
    public void obrasEntreParadas(Parada origen, Parada destino){
        hacerDirigido();
        int o = indiceParada(origen), d = indiceParada(destino);
        if (o != -1 && d != -1) {
            redMetro.eliminarArista(o, d);
        }

    }
    //TODO(3.3)
    public boolean existeCamino(Parada p1, Parada p2){
        boolean [] visitados = new boolean[numParadas];
        for (int i = 0; i < visitados.length; i++) {
            visitados[i] = false;
        }

        return  existeCaminoRec(indiceParada(p1), indiceParada(p2), visitados);
    }

    private boolean existeCaminoRec(int o, int d, boolean [] visitados) {
        visitados[o] = true;

        for (int i = 0; i < numParadas; i++) {
            if (redMetro.existeArista(o, i) && !visitados[i]) {
                if (i == d) {
                    return true;
                } else {
                    return existeCaminoRec(i, d, visitados);
                }
            }
        }

        return false;
    }
}

