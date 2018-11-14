package modeloqytetet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
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
    
if(estado!=EstadoJuego.ALGUNJUGADORENBANCARROTA)
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
            cartaActual=mazo.get(0);
            mazo.remove(0);
            setEstadoJuego(EstadoJuego.JA_CONSORPRESA);
            
        }
        }
     }
}

public void aplicarSorpresa(){
    setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        
        if(cartaActual.getSorpresa() == TipoSorpresa.SALIRCARCEL){
            jugadorActual.setCartaLibertad(cartaActual);
        }
        
        else{
            mazo.add(cartaActual);
            
            if(null != cartaActual.getSorpresa())switch (cartaActual.getSorpresa()) {
            case PAGARCOBRAR:
                jugadorActual.modificarSaldo(cartaActual.getValor());
                if(jugadorActual.getSaldo() < 0){
                    setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                }   break;
            case IRACASILLA:
                int valor = cartaActual.getValor();
                boolean casillaCarcel = tablero.esCasillaCarcel(valor);
                if(casillaCarcel){
                    encarcelarJugador();
                }
                
                else{
                    mover(valor);
                }
                break;
            case PORCASAHOTEL:
                int cantidad = cartaActual.getValor();
                int numeroTotal = jugadorActual.cuantasCasasHotelesTengo();
                jugadorActual.modificarSaldo(numeroTotal*cantidad);
                if(jugadorActual.getSaldo() < 0){
                    setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                }
                break;
            case PORJUGADOR:
                for(int i=0; i<MAX_JUGADORES-1; i++){
                    siguienteJugador();
                    
                    jugadorActual.modificarSaldo(cartaActual.getValor());
                    
                    if(jugadorActual.getSaldo() < 0){
                        setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                    }
                }
                break;
            default:
                break;
            }
}
}


public boolean cancelarHipoteca(int numeroCasilla){
    Casilla casilla=jugadorActual.getCasillaActual();
    TituloPropiedad titulo=casilla.getTitulo();
    boolean puedeCancelar=jugadorActual.cancelarHipoteca(titulo);
    setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
    return puedeCancelar;
}


public boolean comprarTituloPropiedad(){
    boolean comprado=jugadorActual.comprarTituloPropiedad();
    int costeCompra=jugadorActual.getCasillaActual().getCoste();
    if(costeCompra<jugadorActual.getSaldo()){
        TituloPropiedad titulo=jugadorActual.getCasillaActual().getTitulo();
        titulo.setPropietario(jugadorActual);
        jugadorActual.getCasillaActual().asignarPropietario(jugadorActual);
        jugadorActual.getPropiedades().add(titulo);
        jugadorActual.modificarSaldo(-costeCompra);
        comprado=true;
    }
    if(comprado)
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
    return comprado;
}


public boolean edificarCasa(int numeroCasilla){
    Casilla casilla=tablero.obtenerCasillaNumero(numeroCasilla);
    TituloPropiedad titulo=casilla.getTitulo();
    boolean edificada=jugadorActual.edificarCasa(titulo);
    int numCasas=titulo.getNumCasas();
    
    if(numCasas<4){
        int costeEdificarCasa=titulo.getPrecioEdificar();
        boolean tengoSaldo=jugadorActual.tengoSaldo(costeEdificarCasa);
        if (tengoSaldo){
            titulo.edificarCasa();
            numCasas++;
            jugadorActual.modificarSaldo(-costeEdificarCasa);
            edificada=true;            
        }  
    }
    
    if(edificada)
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
    
    return edificada;
}

public boolean edificarHotel(int numeroCasilla){
    boolean edificada=false;
    Casilla casilla=tablero.obtenerCasillaNumero(numeroCasilla);
    if(casilla.getTipo()==TipoCasilla.CALLE && casilla.getTitulo().getNumCasas()==4){
        TituloPropiedad titulo=casilla.getTitulo();
        edificada=jugadorActual.edificarHotel(titulo);
        if (edificada)
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
    }
    return edificada;
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
    Casilla casilla=tablero.obtenerCasillaNumero(numeroCasilla);
    TituloPropiedad titulo=casilla.getTitulo();
    jugadorActual.hipotecarPropiedad(titulo);
    setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
}


public void inicializarJuego(ArrayList<String> nombres){
    inicializarTablero();
    inicializarCartasSorpresa();
    inicializarJugadores(nombres);
    salidaJugadores();
}

private void inicializarJugadores(ArrayList<String> nombres){
    int num_jugadores=nombres.size();
    for (int i=0;i<nombres.size();i++)
      this.jugadores.add(new Jugador(nombres.get(i)));

}



public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
    if(metodo==MetodoSalirCarcel.TIRANDODADO){
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

    int valor = tirarDado();
    int casilla = tablero.obtenerCasillaFinal(jugadorActual.getCasillaActual(), valor).getNumeroCasilla();
    mover(casilla);
}

void mover(int numCasillaDestino){
            Casilla casillaInicial = jugadorActual.getCasillaActual();
        Casilla casillaFinal = tablero.obtenerCasillaNumero(numCasillaDestino);
        jugadorActual.setCasillaActual(casillaFinal);
        
        if(numCasillaDestino < casillaInicial.getNumeroCasilla()){
            jugadorActual.modificarSaldo(SALDO_SALIDA);
        }
        
        if(casillaFinal.soyEdificable()){
            actuarSiEnCasillaEdificable();
        }
        
        else{
            actuarSiEnCasillaNoEdificable();
        }
}


public Casilla obtenerCasillaJugadorActual(){
    return jugadorActual.getCasillaActual();
}


public ArrayList<Casilla> obtenerCasillasTablero(){
    return tablero.getCasillas();
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
    Collections.sort(jugadores);
}


public int obtenerSaldoJugadorActual(){

    return jugadorActual.getSaldo();
}


private void salidaJugadores(){
    for (int i=0;i<jugadores.size();i++)
    {
        this.jugadores.get(i).setCasillaActual(tablero.obtenerCasillaNumero(0));
    }
    Random jug_aleatorio=new Random();
    int jugador=jug_aleatorio.nextInt(jugadores.size());    
    jugadorActual=jugadores.get(jugador);
    
    setEstadoJuego(EstadoJuego.JA_PREPARADO);
    
}
private void setCartaActual(Sorpresa cartaActual){

    this.cartaActual=cartaActual;

}
public void siguienteJugador(){
    int numero=jugadores.indexOf(jugadorActual);
    jugadorActual=jugadores.get((numero)%4);
    if (jugadorActual.getEncarcelado())
       setEstadoJuego(EstadoJuego.JA_ENCARCELADOCONOPCIONDELIBERTAD);
        
    else
        setEstadoJuego(EstadoJuego.JA_PREPARADO);
               
}

int tirarDado(){
    return dado.tirar();

}
void venderPropiedad(int numeroCasilla){
    Casilla casilla=jugadorActual.getCasillaActual();
    jugadorActual.venderPropiedad(casilla);
    setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
    

}


public void setEstadoJuego(EstadoJuego estadojuego){
    estado=estadojuego;
   
}


public EstadoJuego getEstadoJuego()
{
    return estado;
}
}