
package modeloqytetet;
import java.util.ArrayList;

public class Jugador implements Comparable {

private boolean encarcelado = false;
private String nombre;
private int saldo= 7500;
private Sorpresa cartaLibertad;
private Casilla casillaActual;
private static ArrayList<TituloPropiedad>propiedades=new ArrayList <>();
        


    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    
boolean cancelarHipoteca(TituloPropiedad titulo){
    boolean puedeCancelar=false;
    int costeCancelar=titulo.calcularCosteCancelar();
    if(saldo>costeCancelar){
        titulo.cancelarHipoteca();
        puedeCancelar=true;
    }
    return puedeCancelar;
}
boolean comprarTituloPropiedad(){
    boolean comprado = false;
    int costeCompra = casillaActual.getCoste();
        if(costeCompra<saldo){
            comprado = true;
            TituloPropiedad titulo = casillaActual.asignarPropietario(this);
            titulo.setPropietario(this);
            propiedades.add(titulo);
            this.modificarSaldo(-costeCompra);
        }
        
return comprado;


}
int cuantasCasasHotelesTengo(){
    int resultado = 0;
    for (int i=0;i<propiedades.size();i++)
    {
       resultado = resultado + propiedades.get(i).getNumHoteles() + propiedades.get(i).getNumCasas();

    }
    return resultado;
}

boolean deboPagarAlquiler(){

    
    boolean esDeMiPropiedad= esDeMiPropiedad(casillaActual.getTitulo());
    boolean tienePropietario=!esDeMiPropiedad && casillaActual.tengoPropietario();
    boolean encarcelado=!esDeMiPropiedad && tienePropietario && casillaActual.propietarioEncarcelado();
    boolean estaHipotecada=!esDeMiPropiedad && tienePropietario && casillaActual.getTitulo().getHipotecada();
    boolean deboPagar= !esDeMiPropiedad && tienePropietario && !encarcelado && !estaHipotecada;
        
    return deboPagar;
}
Sorpresa devolverCartaLibertad(){
    Sorpresa inter = new Sorpresa (cartaLibertad.getTexto(),cartaLibertad.getValor(),cartaLibertad.getSorpresa());
    cartaLibertad=null;
    return inter;
}

boolean edificarCasa(TituloPropiedad titulo){
    boolean hayEspacio=titulo.getNumCasas()<4;
    boolean tengoSaldo=false;
    int costeEdificarCasa=0;    
    if(hayEspacio){
        costeEdificarCasa=titulo.getPrecioEdificar();
        tengoSaldo=tengoSaldo(costeEdificarCasa);
    }
    
    if(hayEspacio && tengoSaldo){
        casillaActual.getTitulo().edificarCasa();
        modificarSaldo(-costeEdificarCasa);
    }
    
    boolean edificada=hayEspacio && tengoSaldo;
    
    return edificada;
}
boolean edificarHotel(TituloPropiedad titulo){
    boolean edificada=false;
    int numHoteles=titulo.getNumHoteles();
    if(numHoteles<4){
        int costeEdificarHotel=titulo.getPrecioEdificar();
        boolean tengoSaldo=tengoSaldo(costeEdificarHotel);
        if(tengoSaldo){
            titulo.edificarHotel();
            modificarSaldo(-costeEdificarHotel);
            edificada=true;
        }
    }
    return edificada;
}

void eliminarDeMisPropiedades(TituloPropiedad titulo){
    propiedades.remove(titulo);
    titulo.setPropietario(null);
}



private boolean esDeMiPropiedad(TituloPropiedad titulo){
    boolean resultado=false;
    if (propiedades.contains(titulo))
            resultado=true;
    
    return resultado;
}

boolean estoyEnCalleLibre(){
    boolean calleLibre=false;
    
    if(casillaActual.getTitulo().getPropietario()!=null)
        calleLibre=true;

    return calleLibre;
}


Sorpresa getCartaLibertad(){

    return cartaLibertad;  

}
Casilla getCasillaActual(){

    return casillaActual;

}
boolean getEncarcelado(){

    return encarcelado;

}
String getNombre(){

    return nombre;

}

ArrayList<TituloPropiedad> getPropiedades(){
    return propiedades;
}            

public int getSaldo(){

    return saldo;

}


void hipotecarPropiedad(TituloPropiedad titulo){
    int coste=titulo.hipotecar();
    modificarSaldo(coste);

}

void irACarcel(Casilla casilla){
    if (casilla.getTipo()==TipoCasilla.CARCEL)
    {
    casillaActual=casilla;
    }
}

double modificarSaldo(double cantidad){
    saldo+=cantidad;
    
    return saldo;
}
int obtenerCapital(){

    int resultado=saldo;
    for (int i=0;i<propiedades.size();i++){
    resultado=resultado + propiedades.get(i).getPrecioCompra()+
            (propiedades.get(i).getPrecioEdificar()*propiedades.get(i).getNumHoteles())
            +(propiedades.get(i).getPrecioEdificar()*propiedades.get(i).getNumCasas());
            if (propiedades.get(i).getHipotecada())
                resultado-=propiedades.get(i).getHipotecaBase();
            }
    
    return resultado;
    
}

ArrayList <TituloPropiedad> obtenerPropiedades(boolean hipotecada){
    ArrayList<TituloPropiedad>propiedadeshipotecadas=new ArrayList <>();
    for (int i=0;i<propiedades.size();i++)
    {
        if (propiedades.get(i).getHipotecada()==hipotecada)
            propiedadeshipotecadas.add(propiedades.get(i));
    }
    return propiedadeshipotecadas;
}



void pagarAlquiler(){

    double costeAlquiler=casillaActual.getTitulo().calcularImporteAlquiler();
    casillaActual.getTitulo().getPropietario().modificarSaldo(costeAlquiler);
    modificarSaldo(-costeAlquiler);

}
void pagarImpuesto(){
    
    saldo=saldo-casillaActual.getCoste();

}
void pagarLibertad(int cantidad){

throw new UnsupportedOperationException("Sin implementar");

}
void setCartaLibertad(Sorpresa carta){

    if (carta.getSorpresa()==TipoSorpresa.SALIRCARCEL)
           cartaLibertad=carta;
    else
        System.out.println("Error:Carta no es de tipo SALIRCARCEL");    

}
void setCasillaActual(Casilla casilla){

    casillaActual=casilla;

}
void setEncarcelado(boolean encarcelado){

   if(encarcelado)
       this.encarcelado=encarcelado;
   else
       this.encarcelado=false;

}


boolean tengoCartaLibertad(){
    boolean resultado=false;
    if (cartaLibertad!=null)
        resultado=true;
    
    return resultado;
}

boolean tengoSaldo(int cantidad){
boolean tengo=false;
if (saldo>cantidad)
        tengo=true;
return tengo;
}


void venderPropiedad(Casilla casilla){
    TituloPropiedad titulo=casilla.getTitulo();
    eliminarDeMisPropiedades(titulo);
    int precioVenta=titulo.calcularPrecioVenta();
    modificarSaldo(precioVenta);
} 


    @Override
    public String toString() {
        int capital = obtenerCapital();
        return "Jugador{" + "encarcelado=" + encarcelado 
                + ", nombre=" + nombre + ", saldo=" + saldo +",capital=" + capital + ", cartaLibertad=" + cartaLibertad 
                + ", casillaActual=" + casillaActual + '}';
    }
    
    @Override
    public int compareTo(Object otroJugador) {
        int otroCapital= ((Jugador) otroJugador).obtenerCapital();
        
        return otroCapital-obtenerCapital();
}
    
}
