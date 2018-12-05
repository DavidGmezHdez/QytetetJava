package modeloqytetet;
public class OtraCasilla extends Casilla {
    public OtraCasilla(TipoCasilla tipo, int numeroCasilla) {
        super(tipo, numeroCasilla,null);
    }
    
    @Override
    protected TipoCasilla getTipo(){
        return super.getTipo();
    }
    
    protected boolean soyEdificable(){
        return false;
    }
   
    @Override
    protected TituloPropiedad getTitulo(){
        return null;
    }   
}
