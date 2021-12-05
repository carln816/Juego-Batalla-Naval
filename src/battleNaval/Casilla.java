/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleNaval;
/**
 *
 * @author PC
 */
public class Casilla {
    private Barco barco;
    private char posicion_x;
    private int posicion_y;
    private boolean leTiraron;
    public Casilla(char posicion_x, int posicion_y) {
        this.posicion_x = posicion_x;
        this.posicion_y = convertirALetra(posicion_y);
        barco = null;
        leTiraron = false;
    }
    public Casilla(int posicion_x, int posicion_y) {
        this.posicion_x = convertirALetra(posicion_x);
        this.posicion_y = posicion_y;
        barco = null;
        leTiraron = false;
    }
    /*
     * Convierte el numero dado a una letra
     * Se basa en la tabla ASCII
     */
    public static char convertirALetra(int numero) {
        return (char)(numero+65);
    }
    /*
     * Convierte la letra dado a un numero
     * en caso de que la letra dada sea incorrecta retorna un -1
     * Se basa en la tabla ASCII
     */
    public static int convertirANumero(char letra) {
        if(letra >= 65 && letra <= 90)
            return letra-65;
        else if(letra >= 97 && letra <= 122)
            return letra-97;
        else
            return -1;
    }
    public Barco getBarco() {
        return barco;
    }
    public void setBarco(Barco barco) {
        this.barco = barco;
    }
    public char getPosicion_x() {
        return posicion_x;
    }
    public int getPosicion_y() {
        return posicion_y;
    }
    public boolean getLeTiraron() {
        return leTiraron;
    }
    public void setLeTiraron() {
        leTiraron = true;
    }
    /*
     * Cambia la variable leTiraron a true
     * y en caso que tenga un barco asigando, entonces se le resta las partes restantes uno a ese barco
     */
    public void tirar() {
        this.leTiraron = true;
        if(barco != null) {
            barco.setPartesRestantes(barco.getPartesRestantes() - 1);
        }
    }
}