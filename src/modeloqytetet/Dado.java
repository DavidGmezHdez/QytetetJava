
package modeloqytetet;
import java.util.Random;
public class Dado {

    private int valor;
    private static final Dado instance=new Dado();
   
    private Dado(){
    valor=0;
    }
   
    
    int tirar(){
    
        Random r = new Random();
        int valorDado = r.nextInt(6)+1;
        valor=valorDado;
        return valorDado;
    }
public int getValor(){

    return valor;

}
public static Dado getDado() {
    return instance;
}

    @Override
    public String toString() {
        return "Dado{" + "valor=" + valor + '}';
    }


}
