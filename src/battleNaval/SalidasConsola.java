/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleNaval;
import java.util.Scanner;
/**
 *
 * @author PC
 */
public class SalidasConsola {
    private Tablero tablero1;
    private Tablero tablero2;
    private Casilla[][] T1_casillas;
    private Casilla[][] T2_casillas;

    public SalidasConsola(Tablero tablero1, Tablero tablero2) {
        this.tablero1 = tablero1;
        this.tablero2 = tablero2;
        this.T1_casillas = tablero1.getCasillas();
        this.T2_casillas = tablero2.getCasillas();
    }

    /*
     * Metodo para imprimir los dos tableros en pantalla
     */
    public void mostrarTableros() {
        String salida = "";
        String[] t1 = stringTablero(1,false).split("\n");
        String[]  t2 = stringTablero(2,true).split("\n");

        // Divide linea por linea los tableros para tener ambos tableros juntos
        for(int i=0; i<t1.length; i++) {
            salida += t1[i] + "              #              " + t2[i]+"\n";
        }
        System.out.println(salida);
    }

    /*
     * Metodo solo para mostrar un tablero
     */
    public void mostrarTablero(int numTablero) {
        System.out.println(stringTablero(numTablero, false));
    }

    /*
     * Metodo que guarda el tablero en texto (este metodo no lo muetra)
     */
    public String stringTablero(int numTablero, boolean oculto) {
        Casilla[][] casillas;
        if(numTablero == 2) {
            casillas = T2_casillas;
        } else {
            casillas = T1_casillas;
        }
        String tablero = "";
        Barco barco;
        Casilla casilla;
        tablero += fila_x();
        for(int x=0; x<casillas.length; x++) {
            tablero += divisor();

            tablero += columna_y(x);

            for(int y=0; y<casillas[x].length; y++) {
                casilla = casillas[x][y];
                barco = casilla.getBarco();
                if(barco != null && !oculto && !casilla.getLeTiraron())
                    tablero += "|====";
                else if(barco != null && casilla.getLeTiraron())
                    tablero += "|=AA=";
                else if(casilla.getLeTiraron())
                    tablero += "| FF ";
                else
                    tablero += "|    ";
            }

            tablero += "|";
            tablero += "\n";
        }
        tablero += divisor();
        return tablero;
    }

    private String divisor() {
        String linea = "   ";
        for(int divisor=0; divisor<5*T1_casillas.length + 1; divisor++) {
            linea += "_";
        }
        return linea+"\n";
    }

    private String fila_x(){
        String fila = "   ";
        for(int i=0; i<T1_casillas.length; i++) {
            fila += "  "+Casilla.convertirALetra(i)+"  ";
        };
        return fila+" \n";
    }

    public String columna_y(int lineaActual) {
        String columna = (lineaActual+1)+"";
        if(lineaActual>7)
            columna += " ";
        else
            columna += "  ";
        return columna;
    }

    public void imprimir(String texto) {
        System.out.println(">>>>> "+texto);
    }

   /* public void limpiarPantalla() {
        for(int i=0; i<16 ; i++)
           // System.out.println();
    }*/

    public void imprimirGanador(String ganador) {
        System.out.println("____________________");
        System.out.println("| "+ganador+"|");
        System.out.println("____________________");
        //System.out.println("\n");
    }
}



    