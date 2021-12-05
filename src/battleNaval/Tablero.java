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
public class Tablero {
    private Casilla[][] casillas;
    private Barco[] barcos;
    private int filas;
    private int columnas;
    private boolean acertado; // true = ha acertado el tiro (que se encuentra barco en la casilla) / false = no ha acertado
    private int barcosAcertados;

    /*
     * Constructor que recibe el numero de filas y columnas para crear el tablero
     */
    public Tablero(int filas, int columnas) {
        // Se asignan valores e inicializa los necesarios
        this.filas = filas;
        this.columnas = columnas;
        this.barcosAcertados = 0;
        // Se crea el arreglo de casillas con el numero de filas y columnas dado
        casillas = new Casilla[filas][columnas];
        generarCasillas();
        generarBarcos();
        posicionAleatoria();
    }

    /*
     * Metodo que genera las casillas
     */
    public void generarCasillas() {
        // Recorre el arreglo de las casillas
        for(int x=0; x<casillas.length; x++) {
            for(int y=0; y<casillas[x].length; y++) {
                // Inicializa el objeto pasando las coordenadas de donde se encuentra
                casillas[x][y] = new Casilla(x,y);
            }
        }
    }

    /*
     * 
     * Asigan el arreglo de barcos con los objetos de Barco
     */
    public void generarBarcos() {
        barcos = new Barco[3];
        /*barcos[9] = new Barco(this,4);
        barcos[8] = new Barco(this,3);
        barcos[7] = new Barco(this,3);
        barcos[6] = new Barco(this,2);
        barcos[5] = new Barco(this,2);
        barcos[4] = new Barco(this,2);*/
       // barcos[3] = new Barco(this,1);
        barcos[2] = new Barco(this,3);
        barcos[1] = new Barco(this,2);
        barcos[0] = new Barco(this,2);
    }

    /*
     * Generara los barcos creado en una posicion aleatoria del tablero
     */
    public void posicionAleatoria() {
        for(int i=0; i<barcos.length; i++) {
            this.posicionAleatoriaBarco(barcos[i]);
        }
    }

    /*
     * Este metodo se encarga de ajustar la posicion del barco en el tablero
     */
    private void posicionAleatoriaBarco(Barco barco) {
        // Se llama metodo para generar numero aleatorio hasta maximo el numero de columnas-1 porque cuenta el 0
        // Despues el numero se convierte a una letra y se asigan a x
        char x = Casilla.convertirALetra(generarNumeroAleatorio(columnas-1));
        // Llama al metodo para generar numero aleatorio con el maximo de filas mas 1 para que no de cero
        int y = generarNumeroAleatorio(filas-1) + 1;

        // Genera numero aleatorio, maximo hasta el 1 (genera 0 o 1)
        int posicion = generarNumeroAleatorio(1);

        // Si el numero aleatorio es 1
        if(posicion == 1) {
            // Entonces cambia la orientacion
            barco.cambiaOrientacion();
        }
        // Si no se logra posicionar en X y Y dado
        if(!barco.posicionar(x, y)) {
            // Entonces vuelve a intentarlo llamandose a si­ mismo con el mismo barco
            posicionAleatoriaBarco(barco);
        }
    }

    /*
     * Metodo que solo genera un numero aleatorio que recibe como parametro el maximo de donde sera aleatorio
     */
    public int generarNumeroAleatorio(int numeroMaximoAleatorio) {
        return ((int)(Math.random() * numeroMaximoAleatorio));
    }

    /*
     * Este metodo se encarga de checar si se ha tirado ya la casilla
     * y si no entonces llama al metodo de la casilla "tirar()"
     * retorna true si no le ha tirado a la casilla
     * retorna false si ya le ha tirado
     */
    public boolean hacerTiro(char x, int y) {
        // Variable de la casilla actual
        Casilla casilla = casillas[y-1][Casilla.convertirANumero(x)];
        // si no le han tirado
        if(!casilla.getLeTiraron()) {
            // entonces tira a dicha casilla
            casilla.tirar();
            // asigan que no se ha acertado
            this.acertado = false;
            // pero si la casilla tiene un barco
            if(casilla.getBarco() != null) {
                // entonces si ha acertado
                this.acertado = true;
                // y aque­ checa si al barco le quedan partes restantes
                // en caso de que que sean 0
                if (casilla.getBarco().getPartesRestantes() == 0) {
                    // llama al metodo de marcas casillas vecinas
                    // pasando el barco de esta casilla
                    //marcarCasillasVecinas(casilla.getBarco());
                    // y se suma un barco mas a barcosAcertados
                    barcosAcertados++;
                }
            }
            return true;
        }
        return false;
    }

/* Falta implementarlo bien
    public boolean checarVecinos(int posicion_x, int posicion_y, Barco barcoDado) {
        int x_max = 3, x_actual = 0, y_actual = 0;
        Barco barco;
        boolean valor = false;
        for(int i=0; i<9; i++) {
            try {
                barco = casillas[posicion_y - 1 + y_actual][posicion_x - 1 + x_actual].getBarco();
                if(barco == null || !barco.equals(barcoDado)) {
                    valor = true;
                } else
                    return false;
            } catch(ArrayIndexOutOfBoundsException e) {}
            x_actual++;
            if (x_actual >= x_max) {
                x_actual = 0;
                y_actual++;
            }
        }
        return valor;
    }*/

    /*
     * Este metodo se encarga de marcar las casillas vecinas al barco como acertadas (Para que ya no se pueda tirar ahi)
     */
   /* public void marcarCasillasVecinas(Barco barco) {
        // Se inicializan variables y se asignan valores
        // x_max se inica en 3 porque es lo minimo que puede tener
        int x_max = 3, x_actual = 0, y_actual = 0;
        // Si la orientacion del barco es en horizontal
        if(barco.getOrientacion()) {
            // entonces la variable x_max cambiara al tamaaño del barco + 1
            x_max = barco.getTamanio()+2;
        }

        // Se hace un ciclo for para verificar cada casilla
        // El limite sera-a el tamaño del barco + 2 y eso multiplicado por 3
        // Con esa pequeña formula se puede checar las casillas pegadas del barco (contando sus casillas donde se encuentra)
        for(int i=0; i<(barco.getTamanio()+2)*3; i++) {
            // Se hace un try-catch para saltar errores porque puede que el barco este pegado a la orilla y eso genere error
            // porque no tendra menos casillas al arrededor
            try {
                // Se cambia la casilla en donde se encuentra actualmente a que le han tirado
                // Para ello se llama al metodo de dicha casilla llamado "setLeTiraron"
                casillas[barco.getPosicion_y() - 1 + y_actual][barco.getPosicion_x() - 1 + x_actual].setLeTiraron();
                // Se cacha el error pero no se hace nada, solo se pasa sin cambiar lo anterior
            } catch(ArrayIndexOutOfBoundsException e) {}

            // Se le suma a la variable x_actual para continuar con la siguiente casilla
            x_actual++;
            // este condiciona se usa para cambiar en el eje Y (o sea al final de la fila y continuar despues la de abajo)
            if (x_actual >= x_max) {
                x_actual = 0;
                y_actual++;
            }
        }
    }*/

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public boolean getAcertado() {
        return acertado;
    }

    public int getTotalBarcos() {
        return barcos.length;
    }

    public int getBarcosAcertados() {
        return barcosAcertados;
    }
}


    

