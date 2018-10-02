package modeloqytetet;
public class Casilla {

    private int numeroCasilla;
    private int precioCompra;
    private TipoCasilla tipo;
    private TituloPropiedad titulo;
    
    public void setNumeroCasilla(int numeroCasilla) {
        this.numeroCasilla = numeroCasilla;
    }

    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
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
        return "Casilla{" + "numeroCasilla=" + numeroCasilla + ", precioCompra=" 
                + precioCompra + ", tipo=" + tipo + ", titulo=" 
                + titulo + '}';
        else
            return "Casilla{" + "numeroCasilla=" + numeroCasilla + ", precioCompra=" 
                + precioCompra + ", tipo=" + tipo +'}';
    }

    public int getNumeroCasilla() {
        return numeroCasilla;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public TipoCasilla getTipo() {
        return tipo;
    }

    public TituloPropiedad getTitulo() {
        return titulo;
    }

    public Casilla(TipoCasilla tipo, int numeroCasilla) {
        this.numeroCasilla = numeroCasilla;
        
        if(tipo!=TipoCasilla.CALLE)
        {
            setTitulo(titulo);
            precioCompra=0;
        }
    }
    
    public Casilla(TipoCasilla tipo,int numeroCasilla , TituloPropiedad titulo) {
        this.numeroCasilla = numeroCasilla;
        if(tipo == TipoCasilla.CALLE){
            setTitulo(titulo);
            this.tipo = tipo;
            precioCompra = titulo.getPrecioCompra();
        }
    }

    
    
}
