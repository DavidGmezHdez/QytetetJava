package modeloqytetet;
public class Especulador extends Jugador {
    private int fianza;

    
    protected Especulador(Jugador jugador,int fianza){
        super(jugador);
        this.fianza=fianza;
    }
    
    
    protected void pagarImpuesto(){
        super.modificarSaldo(super.getCasillaActual().getCoste()/2);
    }
 
    protected Especulador convertirme(int fianza){
        return this;
    }
    
    protected boolean deboIrACarcel(){
        return super.deboIrCarcel() && !pagarFianza();
    }
    
    private boolean pagarFianza(){
        return super.getSaldo()>fianza; 
    }

    @Override
    public String toString() {
        return super.toString()+"Especulador{" + "fianza=" + fianza + '}';
    }
    
    protected boolean puedoEdificarCasa(TituloPropiedad titulo){
    boolean hayEspacio=titulo.getNumCasas()<8;
    boolean tengoSaldo=false;
    int costeEdificarCasa=0;    
    if(hayEspacio){
        costeEdificarCasa=titulo.getPrecioEdificar();
        tengoSaldo=super.tengoSaldo(costeEdificarCasa);
    }
    
    if(hayEspacio && tengoSaldo){
        titulo.edificarCasa();
        super.modificarSaldo(-costeEdificarCasa);
    }
    
    boolean edificada=hayEspacio && tengoSaldo;
    
    return edificada;    
    }
    
    protected boolean puedoEdificarHotel(TituloPropiedad titulo){
    boolean hayEspacio=titulo.getNumHoteles()<8;
    boolean tengoSaldo=false;
    int costeEdificarHotel=0;
    int numHoteles=titulo.getNumHoteles();
    if(hayEspacio){
        costeEdificarHotel=titulo.getPrecioEdificar();
        tengoSaldo=super.tengoSaldo(costeEdificarHotel);
    }
    if(hayEspacio && tengoSaldo){
            titulo.edificarHotel();
            super.modificarSaldo(-costeEdificarHotel);
        }
    
    boolean edificada=hayEspacio && tengoSaldo;
    
    return edificada;
    
    }
    
}
