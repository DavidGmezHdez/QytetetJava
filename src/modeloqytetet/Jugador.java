
package modeloqytetet;
import java.util.ArrayList;

public class Jugador {

private boolean encarcelado = false;
private String nombre;
private int saldo= 7500;
private Sorpresa cartaLibertad;
private Casilla casillaActual;
private static ArrayList<TituloPropiedad>propiedades;
        


    public Jugador(String nombre) {
        this.nombre = nombre;
    }

/*        
boolean cancelarHipoteca(TituloPropiedad titulo){



}
boolean comprarTituloPropiedad(){



}
int cuantasCasasHotelesTengo(){



}

boolean deboPagarAlquiler(){



}
Sorpresa devolverCartaLibertad(){



}
boolean edificarCasa(TituloPropiedad titulo){



}
boolean edificarHotel(TituloPropiedad titulo){



}
*/
void eliminarDeMisPropiedades(TituloPropiedad titulo){

    throw new UnsupportedOperationException("Sin implementar");

}


/*
private boolean esDeMiPropiedad(TituloPropiedad titulo){



}
boolean estoyEnCalleLibre(){




}

*/
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
/*
int modificarSaldo(int cantidad){



}
int obtenerCapital(){



}
TituloPropiedad obtenerPropiedades(boolean hipotecada){
     TituloPropiedad[0..*]
             
}
*/


void pagarAlquiler(){

throw new UnsupportedOperationException("Sin implementar");

}
void pagarImpuesto(){

throw new UnsupportedOperationException("Sin implementar");

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

/*
boolean tengoCartaLibertad(){



}
*/

boolean tengoSaldo(int cantidad){
boolean tengo=false;
if (cantidad>0)
        {
        tengo=true;
        }
return tengo;
}

/*
boolean venderPropiedad(Casilla casilla){



} 

*/
    @Override
    public String toString() {
        return "Jugador{" + "encarcelado=" + encarcelado 
                + ", nombre=" + nombre + ", saldo=" + saldo + ", cartaLibertad=" + cartaLibertad 
                + ", casillaActual=" + casillaActual + '}';
    }
    
}
