package SemanticAnalizer;

public class Clase extends ComponenteConcreto implements Nombre {

    private String _nombre;

    public Clase(){
        _nombre = "";
    }

    public Clase(String nombre){
        _nombre = nombre;
    }

    @Override
    public String get_nombre() {
        return _nombre;
    }

    @Override
    public Tipo get_tipo() {
        return Tipo.NO_PRIMITIVO;
    }

    public void set_nombre(String nombre) {
        this._nombre = nombre;
    }

    @Override
    public String toString() {
        return "Clase{" +  _nombre + '}';
    }

    private void setPadreDeMisHijos(){
        for (Componente hijoActual = getHijoMasIzq(); hijoActual != null; hijoActual = hijoActual.getHermanoDerecho())
            hijoActual.setPadre(this);
    }

    @Override
    public boolean evaluarSemantica() throws SemanticError {
        setPadreDeMisHijos();

        //Agregamos a tablas de simbolos.
        agregarATablaSimbolos();

        //Evaluamos las demás componentes: Recorrido por anchura primero.
        if (this.getHermanoDerecho() != null)
            this.getHermanoDerecho().evaluarSemantica();
        if (this.getHijoMasIzq() != null)
            this.getHijoMasIzq().evaluarSemantica();
        return true;
    }

    private void agregarATablaSimbolos() throws SemanticError{
        if (Programa.getInstance().getTblSimbolosLocales().containsKey(this.get_nombre()) && this.getPadre().getTblSimbolosLocales().get(this.get_nombre()) instanceof Clase )
            System.out.println("ADVERTENCIA: Nombre de clase duplicada en contexto principal: " + this.get_nombre());

        //Agregamos entrada
        Programa.getInstance().getTblSimbolosLocales().put(this.get_nombre(),this);
    }

    @Override
    public String compilar() throws SemanticError {
        if (_hijoMasIzq != null){
            if (_hermanoDerecho != null) return _hijoMasIzq.compilar() + _hermanoDerecho.compilar();
            else return _hijoMasIzq.compilar();
        } else {
            if (_hermanoDerecho != null) return _hermanoDerecho.compilar();
            else return "";
        }
    }
}
