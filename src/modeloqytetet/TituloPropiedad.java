
package modeloqytetet;

public class TituloPropiedad {
    private String Nombre;
    private int precioCompra;
    private int alquilerBase;
    private float factorRevalorizacion;
    private int hipotecaBase;
    private int precioEdificar;
    private boolean hipotecada;
    private int numHoteles=0;
    private int numCasas=0;
    private Jugador propietario;
    
    
    public TituloPropiedad(String nombre,int precioC,int alquilerB,float factorR,int hipotecaB,int precioE)
    {
        Nombre=nombre;
        precioCompra=precioC;
        alquilerBase=alquilerB;
        factorRevalorizacion=factorR;
        hipotecaBase=hipotecaB;
        precioEdificar=precioE;
        hipotecada=false;
        numHoteles=0;
        numCasas=0;
                       
    }

    @Override
    public String toString() {
        return "TituloPropiedad{" + "Nombre=" + Nombre + ", precioCompra=" + precioCompra + ", alquilerBase=" + alquilerBase 
                + ", factorRevalorizacion=" + factorRevalorizacion + ", hipotecaBase=" + hipotecaBase 
                + ", precioEdificar=" + precioEdificar + ", hipotecada=" + hipotecada 
                + ", numHoteles=" + numHoteles + ", numCasas=" + numCasas + '}';
    }

    void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }

    String getNombre() {
        return Nombre;
    }

    boolean getHipotecada() {
        return hipotecada;
    }

    int getPrecioCompra() {
        return precioCompra;
    }

    int getAlquilerBase() {
        return alquilerBase;
    }

    float getFactorRevalorizacion() {
        return factorRevalorizacion;
    }

    int getHipotecaBase() {
        return hipotecaBase;
    }

    int getPrecioEdificar() {
        return precioEdificar;
    }

    int getNumHoteles() {
        return numHoteles;
    }

   int getNumCasas() {
        return numCasas;
    }
 
    int calcularCosteCancelar(){
        int costeCancelar=0;
        costeCancelar= (int) (calcularCosteHipotecar()+calcularCosteHipotecar()*0.1);
        return costeCancelar;
    }
    int calcularCosteHipotecar(){
    int costeHipoteca=0;
    costeHipoteca = (int) (hipotecaBase+numCasas*0.5*hipotecaBase+numHoteles*hipotecaBase);
    return costeHipoteca;
    }
    double calcularImporteAlquiler(){
        double costeAlquiler=0;
            
        costeAlquiler = alquilerBase + numHoteles*0.5 + numCasas*2;
        
        propietario.modificarSaldo(costeAlquiler);
        
        return costeAlquiler;
    
    }
    int calcularPrecioVenta(){
        int costeVenta=0;
        costeVenta=(int) (precioCompra+(numCasas+numHoteles)*precioEdificar*factorRevalorizacion);
        return costeVenta;
    }
    
    
    void cancelarHipoteca(){
    
    throw new UnsupportedOperationException("Sin implementar");
    
    }
    void cobrarAlquiler(int coste){
    
    throw new UnsupportedOperationException("Sin implementar");
    
    }
    void edificarCasa(){
    
        numCasas++;
    
    }
    void edificarHotel(){
    
        numHoteles++;
    
    }

    int hipotecar(){
        int costeHipoteca=calcularCosteHipotecar();
        setHipotecada(true);
        
        return costeHipoteca;
    
    
    }
//    int pagarAlquiler(){
//    
//    
//    
//    }
    boolean propietarioEncarcelado(){
    boolean resultado=false;
    if (propietario.getEncarcelado())
        resultado=true;
    
    return resultado;
    
    
    }

    void setPropietario(Jugador propietario){
    
        this.propietario=propietario;
    
    }
    

    boolean tengoPropietario(){
    return propietario != null;
    }

    Jugador getPropietario(){
       return propietario;
    }
    
    

}
