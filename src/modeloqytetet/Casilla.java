package modeloqytetet;
public class Casilla {

    private int numCasilla=0;
    private int Coste;
    private TipoCasilla tipo;
    private TituloPropiedad titulo;
    
    public void setNumeroCasilla(int numeroCasilla) {
        this.numCasilla = numeroCasilla;
    }

    public void setTipo(TipoCasilla tipo) {
        this.tipo = tipo;
    }

    private void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
    }
    
    private void setCoste(int coste){
        Coste=coste;
    }

    @Override
    public String toString() {
        
        if (tipo == TipoCasilla.CALLE)
        return "Casilla{" + "numeroCasilla=" + numCasilla + ", precioCompra=" 
                + Coste + ", tipo=" + tipo + ", titulo=" 
                + titulo + '}';
        else
            return "Casilla{" + "numeroCasilla=" + numCasilla + ", precioCompra=" 
                + Coste + ", tipo=" + tipo +'}';
    }

    int getNumeroCasilla() {
        return numCasilla;
    }

    int getCoste() {
        return Coste;
    }

    protected TipoCasilla getTipo() {
        return tipo;
    }

    protected TituloPropiedad getTitulo() {
        return titulo;
    }
    
    public Casilla(TipoCasilla tipo,int numeroCasilla , TituloPropiedad titulo) {
        setNumeroCasilla(numeroCasilla);
        if(tipo == TipoCasilla.CALLE){
            setTitulo(titulo);
            this.tipo = tipo;
            Coste = titulo.getPrecioCompra();
        }
        else
        {
            this.tipo=tipo;
            this.titulo=null;
        }
    }
    
    boolean propietarioEncarcelado(){
    
        return titulo.propietarioEncarcelado();
    
    }
    boolean tengoPropietario(){
        boolean resultado = false;
    if (tipo == TipoCasilla.CALLE)
        resultado = true;
    
    return resultado;
    }
}
