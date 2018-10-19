
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

//    
//boolean cancelarHipoteca(TituloPropiedad titulo){
//
//
//
//}
//boolean comprarTituloPropiedad(){
//
//
//
//}
int cuantasCasasHotelesTengo(){
    int resultado = 0;
    for (int i=0;i<propiedades.size();i++)
    {
       resultado = resultado + propiedades.get(i).getNumHoteles() + propiedades.get(i).getNumCasas();

    }
    return resultado;
}
//
//boolean deboPagarAlquiler(){
//
//
//
//}
Sorpresa devolverCartaLibertad(){
    Sorpresa inter = new Sorpresa (cartaLibertad.getTexto(),cartaLibertad.getValor(),cartaLibertad.getSorpresa());
    cartaLibertad=null;
    return inter;
}
//
//boolean edificarCasa(TituloPropiedad titulo){
//
//
//
//}
//boolean edificarHotel(TituloPropiedad titulo){
//
//
//
//}

void eliminarDeMisPropiedades(TituloPropiedad titulo){

    throw new UnsupportedOperationException("Sin implementar");

}



private boolean esDeMiPropiedad(TituloPropiedad titulo){
    boolean resultado=false;
    for (int i=0;i<propiedades.size();i++)
    {
        if (propiedades.get(i)==titulo)
            resultado=true;
    }
    return resultado;
}
//
//boolean estoyEnCalleLibre(){
//
//
//
//
//}


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

/*
boolean hipotecarPropiedad(TituloPropiedad titulo){



}
*/
void irACarcel(Casilla casilla){

throw new UnsupportedOperationException("Sin implementar");

}

int modificarSaldo(int cantidad){
    if (cantidad>=0)
        saldo+=cantidad;
    else
        saldo-=cantidad;
    
    return saldo;
}
int obtenerCapital(){

    int resultado=saldo;
    for (int i=0;i<propiedades.size();i++){
    resultado=resultado + propiedades.get(i).getPrecioCompra()+
            propiedades.get(i).getPrecioEdificar()*(propiedades.get(i).getNumCasas()+propiedades.get(i).getNumCasas());
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

throw new UnsupportedOperationException("Sin implementar");

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

/*
boolean venderPropiedad(Casilla casilla){



} 

*/


    @Override
    public String toString() {
        int capital = obtenerCapital();
        return "Jugador{" + "encarcelado=" + encarcelado 
                + ", nombre=" + nombre + ", saldo=" + saldo +",saldo=" + capital + ", cartaLibertad=" + cartaLibertad 
                + ", casillaActual=" + casillaActual + '}';
    }
    
    @Override
    public int compareTo(Object otroJugador) {
        int otroCapital= ((Jugador) otroJugador).obtenerCapital();
        
        return otroCapital-obtenerCapital();
}
    
}
