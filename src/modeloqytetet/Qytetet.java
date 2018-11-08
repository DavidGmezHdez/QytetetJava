package modeloqytetet;
import java.util.ArrayList;
public class Qytetet {
    private static final Qytetet instance = new Qytetet();
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
    private EstadoJuego estado;
    private static Dado dado = Dado.getDado();
    
    
    
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
    
    private static void inicializarCartasSorpresa(){
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
    boolean deboPagar = jugadorActual.deboPagarAlquiler();
    Casilla casilla= jugadorActual.getCasillaActual();
    boolean tengopropietario = casilla.tengoPropietario();
    
    if (deboPagar)
        jugadorActual.pagarAlquiler();
        if(jugadorActual.getSaldo()<0)
            setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
    
if(EstadoJuego!=EstadoJuego.ALGUNJUGADORENBANCARROTA)
    if(tengopropietario)
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
    else
        setEstadoJuego(EstadoJuego.JA_PUEDECOMPRAROGESTIONAR);
}

void actuarSiEnCasillaNoEdificable(){

    setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
    Casilla casillaActual=jugadorActual.getCasillaActual();
    
    if (casillaActual.getTipo()==TipoCasilla.IMPUESTO){
        jugadorActual.pagarImpuesto();
    }
    else{
        if (casillaActual.getTipo() == TipoCasilla.JUEZ){
        encarcelarJugador();
        }
        else{
        if (casillaActual.getTipo() == TipoCasilla.SORPRESA){
            mazo.remove(cartaActual);
            setEstadoJuego(EstadoJuego.JA_CONSORPRESA);
        }
        }
     }
}

public void aplicarSorpresa(){
    setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
    int valor=0;
    boolean casillaCarcel;
    int cantidad=0;
    int numeroTotal=0;
    
    
    if (cartaActual.getSorpresa()==TipoSorpresa.SALIRCARCEL){
        jugadorActual.setCartaLibertad(cartaActual);
        mazo.add(cartaActual);
    }
    if (cartaActual.getSorpresa() == TipoSorpresa.PAGARCOBRAR){
        jugadorActual.modificarSaldo(cartaActual.getValor());
        if (jugadorActual.getSaldo()<0)
            setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
    }
    else if(cartaActual.getSorpresa() == TipoSorpresa.IRACASILLA)
    {
            valor = cartaActual.getValor();
            casillaCarcel = tablero.esCasillaCarcel(valor);
    
        if (casillaCarcel){
            encarcelarJugador();
        }
        else{
            mover(valor);
        }
    }
    else if(cartaActual.getSorpresa() == TipoSorpresa.PORCASAHOTEL)
    {
        cantidad=cartaActual.getValor();
        numeroTotal=jugadorActual.cuantasCasasHotelesTengo();
        jugadorActual.modificarSaldo(cantidad*numeroTotal);
        if (jugadorActual.getSaldo()<0)
            setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
    }
    
    else if(cartaActual.getSorpresa() == TipoSorpresa.PORJUGADOR)
    {
        for(int i=0;i<jugadores.size();i++)
        {
            siguienteJugador();
            jugadorActual.modificarSaldo(cartaActual.getValor());
            if(jugadorActual.getSaldo()<0)
                setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
            
            jugadorActual.modificarSaldo(-cartaActual.getValor());
            if(jugadorActual.getSaldo()<0)
                setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
        }
    }
}


public boolean cancelarHipoteca(int numeroCasilla){

    
    
}


public boolean comprarTituloPropiedad(){
    boolean comprado=false;
    TituloPropiedad titulo=jugadorActual.getCasillaActual().asignarPropietario(jugadorActual);
    int costeCompra = jugadorActual.getCasillaActual().getCoste();
    if(costeCompra<jugadorActual.getSaldo())
        jugadorActual.getPropiedades().add(titulo);
        jugadorActual.modificarSaldo(-costeCompra);
    
        if(comprado==true)
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        
        return comprado;
}


public boolean edificarCasa(int numeroCasilla){
    boolean edificada=false;
    Casilla casilla=tablero.obtenerCasillaNumero(numeroCasilla);
    TituloPropiedad titulo=jugadorActual.getCasillaActual().getTitulo();
    int numCasas=titulo.getNumCasas();
    edificarCasa(numCasas);
    if(numCasas<4){
        int costeEdificarCasa=titulo.getPrecioEdificar();
        boolean tengoSaldo=jugadorActual.tengoSaldo(costeEdificarCasa);
        if(tengoSaldo){
            numCasas++;
            titulo.edificarCasa();
            jugadorActual.modificarSaldo(-costeEdificarCasa);
            edificada=true;
        } 
    }
    
    if(edificada==true)
        setEstadoJuego(EstadoJuego.JA_PUEDECOMPRAROGESTIONAR);
    
    return edificada;
}

public boolean edificarHotel(int numeroCasilla){



}



boolean jugadorActualEnCalleLibre(){
    boolean resultado = false;
    if (jugadorActual.getCasillaActual().soyEdificable()==true && jugadorActual.getCasillaActual().tengoPropietario()==false)
        resultado = true;
return resultado;
}
boolean jugadorActualEncarcelado(){
    return jugadorActual.getEncarcelado();
}


private void encarcelarJugador(){
    Casilla casillaCarcel;
    Sorpresa carta;
    
    if (!jugadorActual.tengoCartaLibertad()){
        casillaCarcel = tablero.getCarcel();
        jugadorActual.irACarcel(casillaCarcel);
        jugadorActual.setCasillaActual(casillaCarcel);
        jugadorActual.setEncarcelado(true);
        setEstadoJuego(EstadoJuego.JA_ENCARCELADO);
    }
    else
    {
    carta=jugadorActual.devolverCartaLibertad();
    mazo.set(MAX_JUGADORES, carta);
    setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
    }
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


public int getValorDado(){

    return dado.getValor();

}

public void hipotecarPropiedad(int numeroCasilla){

    throw new UnsupportedOperationException("Sin implementar");

}


public void inicializarJuego(ArrayList<String> nombres){

    inicializarTablero();
    inicializarCartasSorpresa();
    inicializarJugadores(nombres);

}

private void inicializarJugadores(ArrayList<String> nombres){
    int num_jugadores=nombres.size();
    for (int i=0;i<nombres.size();i++)
      this.jugadores.add(new Jugador(nombres.get(i)));

}



public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
    if(metodo==MetodoSalirCarcel.TIRANDODAO){
        int resultado=tirarDado();
        if(resultado>=5)
            jugadorActual.setEncarcelado(false);  
    }
    else if(metodo==MetodoSalirCarcel.PAGANDOLIBERTAD){
        int cantidad=PRECIO_LIBERTAD;
        jugadorActual.pagarLibertad(cantidad);
        boolean tengoSaldo=jugadorActual.tengoSaldo(cantidad);
        if(tengoSaldo){
            jugadorActual.setEncarcelado(false);
            jugadorActual.modificarSaldo(-cantidad);
        }
    }
    
    boolean encarcelado=jugadorActual.getEncarcelado();
    
    if(encarcelado)
        setEstadoJuego(EstadoJuego.JA_ENCARCELADO);
    else
        setEstadoJuego(EstadoJuego.JA_PREPARADO);

    return encarcelado;
}


public void jugar(){

    tirarDado();
    int casilla = tablero.obtenerCasillaFinal(jugadorActual.getCasillaActual(), dado.getValor()).getNumeroCasilla();
    mover(casilla);
}

void mover(int numCasillaDestino){

    throw new UnsupportedOperationException("Sin implementar");

}


public Casilla obtenerCasillaJugadorActual(){



}


public Casilla obtenerCasillasTablero(){
    return 

            }

public ArrayList<Integer> obtenerPropiedadesJugador() {
    ArrayList<Integer> casillas=new ArrayList<>();
    String nombre;
    for (int i=0;i<jugadorActual.getPropiedades().size();i++)
    {
        nombre=jugadorActual.getPropiedades().get(i).getNombre();
        casillas.add(tablero.getCasillas().indexOf(nombre));
    }
    
    return casillas;
   
    
}


public ArrayList <Integer> obtenerPropiedadesJugadorSegunEstadoHipoteca(boolean estadoHipoteca){
    ArrayList<Integer> casillas = new ArrayList<>();
    String nombre;
    
    for (int i=0;i<jugadorActual.getPropiedades().size();i++)
    {
        if(jugadorActual.getPropiedades().get(i).getHipotecada()==estadoHipoteca)
        {
        nombre=jugadorActual.getPropiedades().get(i).getNombre();
        casillas.add(tablero.getCasillas().indexOf(nombre));
        }
    
    }
    return casillas;
}


public void obtenerRanking(){

    throw new UnsupportedOperationException("Sin implementar");

}


public int obtenerSaldoJugadorActual(){

    return jugadorActual.getSaldo();
}


private void salidaJugadores(){

    
    for (int i=0;i<jugadores.size();i++)
    {
        jugadores.get(i).setCasillaActual(tablero.obtenerCasillaNumero(0));
    }
    
    int numero = (int) (Math.random() * 4);
    
    jugadorActual=jugadores.get(numero);
    
}
private void setCartaActual(Sorpresa cartaActual){

    this.cartaActual=cartaActual;

}
public void siguienteJugador(){
    int numero=jugadores.indexOf(jugadorActual);
    jugadorActual=jugadores.get((numero+1)%4);
    if (jugadorActual.getEncarcelado())
       setEstadoJuego(EstadoJuego.JA_ENCARCELADOCONOPCIONDELIBERTAD);
        
    else
        setEstadoJuego(EstadoJuego.JA_PREPARADO);
               
}

int tirarDado(){
    return dado.tirar();

}
public boolean venderPropiedad(int numeroCasilla){



}


public void setEstadoJuego(EstadoJuego estadojuego){
    estado=estadojuego;
   
}


public EstadoJuego getEstadoJuego()
{
    return estado;
}







}