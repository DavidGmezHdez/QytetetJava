package modeloqytetet;
public class Calle extends Casilla{
    
    public Calle(TipoCasilla tipo, int numeroCasilla, TituloPropiedad titulo) {
        super(tipo, numeroCasilla, titulo);
    }
    
    public void asignarPropietario(Jugador jugador){
        if (!super.getTitulo().tengoPropietario())
            super.getTitulo().setPropietario(jugador);
    }
    
    @Override
    protected TituloPropiedad getTitulo(){
        return super.getTitulo();
    }
    
    @Override
    protected TipoCasilla getTipo(){
        return super.getTipo();
    }
    
    public int pagarAlquiler(){
         return (int)(super.getTitulo().calcularImporteAlquiler());
    }
    
    private void setTitulo(TituloPropiedad titulo){
        
    }
    
    protected boolean soyEdificable(){
        return true;
    }
   
    
}
