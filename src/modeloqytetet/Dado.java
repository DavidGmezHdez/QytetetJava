
package modeloqytetet;
public class Dado {

    private int valor;
    private static final Dado dado=new Dado();
    
    private Dado(){
    valor=0;
    }
    
    int tirar(){
    
    
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
