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

    TipoCasilla getTipo() {
        return tipo;
    }

    TituloPropiedad getTitulo() {
        return titulo;
    }

    public Casilla(TipoCasilla tipo, int numeroCasilla) {
        setNumeroCasilla(numeroCasilla);
        
        if(tipo!=TipoCasilla.CALLE)
        {
            setTipo(tipo);
            setTitulo(titulo);
            Coste=0;
        }
    }
    
    public Casilla(TipoCasilla tipo,int numeroCasilla , TituloPropiedad titulo) {
        setNumeroCasilla(numeroCasilla);
        if(tipo == TipoCasilla.CALLE){
            setTitulo(titulo);
            this.tipo = tipo;
            Coste = titulo.getPrecioCompra();
        }
    }
/*
    TituloPropiedad signarPropietario(Jugador jugador){
    
    
    }

    int pagarAlquiler(){
    
    
    
    }
    boolean propietarioEncarcelado(){
    
    
    
    }
    boolean soyEdificable(){
    
    
    
    }
    boolean tengoPropietario(){
    
    
    }
    
*/    
    
    
    
    
}
