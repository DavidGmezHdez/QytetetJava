
package modeloqytetet;

public class Jugador {
private boolean encarcelado = false;
private String nombre;
private int saldo= 7500;
        
        
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
void eliminarDeMisPropiedades(TituloPropiedad titulo){



}
private boolean esDeMiPropiedad(TituloPropiedad titulo){



}
boolean estoyEnCalleLibre(){




}
Sopresa getCartaLibertad(){



}
Casilla 2..MAX_JUGADORES getCasillaActual(){



}
boolean getEncarcelado(){



}
String getNombre(){



}
TituloPropiedad getPropiedades(){
    TituloPropiedad[0..*]
            
            }            
public int getSaldo(){

    return saldo;

}

boolean hipotecarPropiedad(TituloPropiedad titulo){



}
void irACarcel(Casilla casilla){



}
int modificarSaldo(int cantidad){



}
int obtenerCapital(){



}
TituloPropiedad obtenerPropiedades(boolean hipotecada){
     TituloPropiedad[0..*]
             
             }             
void pagarAlquiler(){



}
void pagarImpuesto(){



}
void pagarLibertad(int cantidad){



}
void setCartaLibertad(Sorpresa carta){



}
void setCasillaActual(Casilla casilla){



}
void setEncarcelado(boolean encarcelado=false){



}
boolean tengoCartaLibertad(){



}
boolean tengoSaldo(int cantidad){
boolean tengo=false;
if (cantidad>0)
        tengo=true;

return tengo;
}
boolean venderPropiedad(Casilla casilla){



}    
    
}
