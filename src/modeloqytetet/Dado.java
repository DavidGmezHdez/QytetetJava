
package modeloqytetet;
import java.util.Random;
public class Dado {

    private int valor;
    private static final Dado dado=new Dado();
    
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
    return dado;
}

    @Override
    public String toString() {
        return "Dado{" + "valor=" + valor + '}';
    }


}
