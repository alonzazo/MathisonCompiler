package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Metodo extends ComponenteConcreto implements Nombre
{
    private String _nombre;
    private Tipo _tipo;
    private String _nombreTipo;
    private List<Variable> _parametros;
    private boolean _arreglo;
    private boolean _tieneDevolver;


    public Metodo(){
        _nombre = "";
        _tipo = Tipo.NUMERICO;
        _arreglo = false;
    }

    public Metodo( String nombre, Tipo tipo, Componente sentencias){
        _nombre = nombre;
        _tipo = tipo;
        _parametros = null;
        _hijoMasIzq = sentencias;
        System.out.println("Metodo " + nombre);
        _arreglo = false;
    }

    public Metodo( String nombre, Componente sentencias){
        _nombre = nombre;
        _parametros = null;
        _hijoMasIzq = sentencias;
        System.out.println("Metodo " + nombre);
        _arreglo = false;
    }

    public Metodo( String nombre, ArregloPOJO tipo, Componente sentencias){
        _nombre = nombre;
        _tipo = tipo.get_tipo();
        _nombreTipo = tipo.get_nombreTipo();
        _arreglo = true;
        _parametros = null;
        _hijoMasIzq = sentencias;
        System.out.println("Metodo " + nombre);
    }

    public Metodo( String nombre, Tipo tipo, List<Variable> params , Componente sentencias){
        _nombre = nombre;
        _tipo = tipo;
        _parametros = params;
        _hijoMasIzq = sentencias;
        _arreglo = false;
        System.out.println("Metodo " + nombre);
    }

    public Metodo( String nombre, List<Variable> params , Componente sentencias){
        _nombre = nombre;
        _parametros = params;
        _hijoMasIzq = sentencias;
        _arreglo = false;
        System.out.println("Metodo " + nombre);
    }

    public Metodo( String nombre, ArregloPOJO tipo, List<Variable> params , Componente sentencias){
        _nombre = nombre;
        _tipo = tipo.get_tipo();
        _nombreTipo = tipo.get_nombreTipo();
        _parametros = params;
        _arreglo = true;
        _hijoMasIzq = sentencias;
        System.out.println("Metodo " + nombre);
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void set_tipo(Tipo _tipo) {
        this._tipo = _tipo;
    }

    public Metodo(Componente padre){
        _padre = padre;
    }

    @Override
    public String get_nombre() {
        return _nombre;
    }

    @Override
    public String toString() {
        return "Metodo{" +
                "_nombre='" + _nombre + '\'' +
                ", _tipo=" + _tipo +
                '}';
    }

    @Override
    public Tipo get_tipo() {
        return _tipo;
    }

    public List<Variable> getParametros() {
        return _parametros;
    }

    public String get_nombreTipo() {
        return _nombreTipo;
    }

    public void set_nombreTipo(String _nombreTipo) {
        this._nombreTipo = _nombreTipo;
    }

    public boolean is_arreglo() {
        return _arreglo;
    }

    public void set_arreglo(boolean _arreglo) {
        this._arreglo = _arreglo;
    }

    public boolean is_tieneDevolver() {
        return _tieneDevolver;
    }

    public void set_tieneDevolver(boolean _tieneDevolver) {
        this._tieneDevolver = _tieneDevolver;
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

        //Revisamos si se encontraron sentencias DEVOLVER en el método
        if (_tipo != null){
            if (!_tieneDevolver)
                throw new SemanticError("Se esperaba al menos una sentencia DEVOLVER en método " + _nombre +"()");
        }
        //NOTA: No revisamos el caso cuando son procedimientos porque en la clase DEVOLVER se hace la verificación
        return true;
    }

    private void agregarATablaSimbolos() throws SemanticError{
        //Manejamos las excepciones cuando los nombres ya existen.
        if (this.getPadre() != null && this.getPadre().getTblSimbolosLocales().containsKey(this.get_nombre()) && this.getPadre().getTblSimbolosLocales().get(this.get_nombre()) instanceof Metodo)
            System.out.println("ADVERTENCIA: Nombre de método duplicado en contexto local: " + this.get_nombre());
        if (Programa.getInstance().getTblSimbolosLocales().containsKey(this.get_nombre()) && this.getPadre().getTblSimbolosLocales().get(this.get_nombre()) instanceof Metodo )
            System.out.println("ADVERTENCIA: Nombre de método duplicado en contexto principal: " + this.get_nombre());

        //Agregamos entrada
        if (this.getPadre() == null)
            Programa.getInstance().getTblSimbolosLocales().put(this.get_nombre(),this);
        else
            this.getPadre().getTblSimbolosLocales().put(this.get_nombre(),this);

        //Agregamos los parámetros a la dabla de simbolos local.
        agregarParametrosATablaSimbolos();
    }

    private void agregarParametrosATablaSimbolos() {
        _tblSimbolosLocales = new HashMap<>();
        if(_parametros != null)
        {
            _parametros.forEach( variable ->  {
                    if (_tblSimbolosLocales.containsKey(variable.getNombre()))
                        System.out.println("ADVERTENCIA: Nombre de parámetro repetido: " + variable.getNombre() + " -> Parámetro sustituido");
                    _tblSimbolosLocales.put(variable.getNombre(),variable);
            });
        }
    }


}
