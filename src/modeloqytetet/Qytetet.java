package modeloqytetet;
import java.util.ArrayList;
public class Qytetet {

    private static ArrayList<Sorpresa> mazo = new ArrayList<>();
    private static Tablero tablero=null;
    public static int MAX_JUGADORES = 4;
    static int NUM_SORPRESAS = 10;
    private static int NUM_CASILLAS = 20;
    static int PRECIO_LIBERTAD = 200;
    static int SALDO_SALIDA = 1000;
    private Sorpresa cartaActual;
    private Jugador jugadorActual;
    private static ArrayList<Jugador>jugadores=new ArrayList<>();
    
    
    
    
    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    public static int getMAX_JUGADORES() {
        return MAX_JUGADORES;
    }

    public static int getNUM_SORPRESAS() {
        return NUM_SORPRESAS;
    }

    public static int getNUM_CASILLAS() {
        return NUM_CASILLAS;
    }

    public static int getPRECIO_LIBERTAD() {
        return PRECIO_LIBERTAD;
    }

    public static int getSALDO_SALIDA() {
        return SALDO_SALIDA;
    }

    private static void inicializarTablero()
    {    
        tablero=new Tablero();
   
    }
    
    public static ArrayList getMazo()
    {
        return mazo;   
    }
    
    public static void inicializarCartasSorpresa(){
            inicializarTablero();
            mazo.add(new Sorpresa ("Te hemos pillado con las manos en los sobres, lo sentimos, ¡debes ir a la carcel!", 
                    tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));
            
            mazo.add(new Sorpresa ("Pides un Uber que te lleva la casilla mitad del tablero",
                    10, TipoSorpresa.IRACASILLA));
            
            mazo.add(new Sorpresa ("Alquilas una bici amarilla que te lleva a la casilla 5, luego la tiras al río",
                    5, TipoSorpresa.IRACASILLA));
            
            mazo.add(new Sorpresa ("Pides a la gente que te de dinero para comprar un regalo en común, "
                    + "pero acabas quedándotelo tu para ir a Pedro",
                    200, TipoSorpresa.PORJUGADOR));
            
            mazo.add(new Sorpresa ("Dijiste que invitarías a chupitos pero no lo hiciste, pagas 50 euros a cada uno",
                    50, TipoSorpresa.PORJUGADOR));
            
            mazo.add(new Sorpresa ("Recibes un sobre con la letra B escrita, recibes 500 euros",
                    500, TipoSorpresa.PAGARCOBRAR));
            
            mazo.add(new Sorpresa ("Te vas a la ruleta, crees ganar pero el ruso de al lado te hace la jugada, pierdes 200 euros",
                    200, TipoSorpresa.PAGARCOBRAR));
           
            mazo.add(new Sorpresa ("Gracias a la burbuja del alquiler, la gente compra más casas "
                    + "y hay más turistas en hoteles, ganas 300 euros.",
                    300, TipoSorpresa.PORCASAHOTEL));
           
            mazo.add(new Sorpresa ("Mala suerte, Hacienda te ha pillado saltándote la declaración de bienes, debes 500 euros",
                    500, TipoSorpresa.PORCASAHOTEL));
           
            mazo.add(new Sorpresa ("Un afiliado de tu partido intercede. Sales de la cárcel",
                    0, TipoSorpresa.SALIRCARCEL));
    }
    

void actuarSiEnCasillaEdificable(){

throw new UnsupportedOperationException("Sin implementar");
    
}

void actuarSiEnCasillaNoEdificable(){

    throw new UnsupportedOperationException("Sin implementar");

}

public void aplicarSorpresa(){

    throw new UnsupportedOperationException("Sin implementar");

}

/*
public boolean cancelarHipoteca(int numeroCasilla){

    
    
}


public boolean comprarTituloPropiedad(){



}


public boolean edificarCasa(int numeroCasilla){



}

public boolean edificarHotel(int numeroCasilla){



}

*/

private void encarcelarJugador(){

  throw new UnsupportedOperationException("Sin implementar");

}


public Sorpresa getCartaActual(){

    return cartaActual;

}


Dado getDado(){

    return Dado.getDado();

}

Jugador getJugadorActual(){

    return jugadorActual;

}

ArrayList<Jugador> getJugadores(){
        return jugadores;   
}
/*
Sorpresa getMazo(){
    Sorpresa[0..*]
            
}

public int getValorDado(){



}
*/
public void hipotecarPropiedad(int numeroCasilla){

    throw new UnsupportedOperationException("Sin implementar");

}


public void inicializarJuego(ArrayList<String> nombres){

    inicializarTablero();
    inicializarCartasSorpresa();
    inicializarJugadores(nombres);

}

private void inicializarJugadores(ArrayList<String> nombres){
    Jugador jugador = null;
    for (int i=2;i<MAX_JUGADORES;i++)
    jugador=new Jugador(nombres.get(i));
    jugadores.add(jugador);

}


/*
public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){



}
*/

public void jugar(){

    throw new UnsupportedOperationException("Sin implementar");

}

void mover(int numCasillaDestino){

    throw new UnsupportedOperationException("Sin implementar");

}
/*

public Casilla obtenerCasillaJugadorActual(){



}


public Casilla obtenerCasillasTablero(){
    return Casillas[NUM_CASILLAS]

            }

public int obtenerPropiedadesJugador() {
    
    int[0..*];
    
}


public int obtenerPropiedadesJugadorSegunEstadoHipoteca(boolean estadoHipoteca){
    
    int[0..*]

}
*/

public void obtenerRanking(){

    throw new UnsupportedOperationException("Sin implementar");

}

/*
public int obtenerSaldoJugadorActual(){


}
*/

private void salidaJugadores(){

        throw new UnsupportedOperationException("Sin implementar");

}
private void setCartaActual(Sorpresa cartaActual){

    this.cartaActual=cartaActual;

}
public void siguienteJugador(){

    throw new UnsupportedOperationException("Sin implementar");

}
/*
int tirarDado(){


}
public boolean venderPropiedad(int numeroCasilla){



}
*/
}