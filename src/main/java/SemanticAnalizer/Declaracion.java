package SemanticAnalizer;

import java_cup.runtime.Symbol;

import java.util.LinkedList;

public class Declaracion extends ComponenteConcreto {

    private boolean _arreglo;
    private String _nombre;
    private int _tamano;
    private Expresion  _expresionTamano;
    private Tipo _tipo;
    private String _nombreTipo;

    public Declaracion(String _nombre, Tipo _tipo) {
        this._nombre = _nombre;
        this._tipo = _tipo;
        _expresionTamano = null;
        this._arreglo = false;
    }

    public Declaracion(String _nombre, Tipo _tipo, String _nombreTipo) {
        this._nombre = _nombre;
        this._tipo = _tipo;
        if (_tipo == Tipo.NO_PRIMITIVO)
            this._nombreTipo = _nombreTipo;
        _expresionTamano = null;
        this._arreglo = false;
    }

    public Declaracion(String _nombre, Tipo _tipo, String nombreTipo, Expresion expresionTamano) {
        this._nombre = _nombre;
        this._tipo = _tipo;
        if (_tipo == Tipo.NO_PRIMITIVO)
            _nombreTipo = nombreTipo;
        _expresionTamano = expresionTamano;
        _arreglo = true;
    }

    public Declaracion(String _nombre, Tipo _tipo, boolean _arreglo) {
        this._nombre = _nombre;
        this._tipo = _tipo;
        this._arreglo = _arreglo;
    }

    public Declaracion(String _nombre, ArregloPOJO _tipo, boolean _arreglo) {
        this._nombre = _nombre;
        this._tipo = _tipo.get_tipo();
        this._expresionTamano = _tipo.get_expresionTamano();
        this._nombreTipo = _tipo.get_nombreTipo();
        this._arreglo = _arreglo;
    }

    public Declaracion(String _nombre, Tipo _tipo, boolean _arreglo, String _nombreTipo) {
        this._nombre = _nombre;
        this._tipo = _tipo;
        this._arreglo = _arreglo;
        if (_tipo == Tipo.NO_PRIMITIVO)
            this._nombreTipo = _nombreTipo;
    }


    public Declaracion(String _nombre, Tipo _tipo, Expresion expresionTamano, String _nombreTipo) {
        this._nombre = _nombre;
        this._tipo = _tipo;
        _expresionTamano = expresionTamano;
        this._arreglo = false;
        if (_tipo == Tipo.NO_PRIMITIVO)
            this._nombreTipo = _nombreTipo;
    }

    public Declaracion(String _nombre, Tipo _tipo, Expresion expresionTamano) {
        this._nombre = _nombre;
        this._tipo = _tipo;
        _expresionTamano = expresionTamano;
        this._arreglo = false;
        if (_tipo == Tipo.NO_PRIMITIVO)
            this._nombreTipo = _nombreTipo;
    }


    public int get_tamano() {
        return _tamano;
    }

    public void set_tamano(int _tamano) {
        this._tamano = _tamano;
    }

    public Expresion get_expresionTamano() {
        return _expresionTamano;
    }

    public void set_expresionTamano(Expresion _expresionTamano) {
        this._expresionTamano = _expresionTamano;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public boolean is_arreglo() {
        return _arreglo;
    }

    public void set_arreglo(boolean _arreglo) {
        this._arreglo = _arreglo;
    }

    public Tipo get_tipo() {
        return _tipo;
    }

    public void set_tipo(Tipo _tipo) {
        this._tipo = _tipo;
    }

    public boolean evaluarIndice() throws SemanticError {
        if (is_arreglo() && _expresionTamano != null){
            setPadreExpresiones();
            return _expresionTamano.evaluarTipo() == Tipo.NUMERICO;
        }
        else
            return true;

    }

    private void setPadreExpresiones(){
        if (_expresionTamano instanceof Operacion)
            for (Componente i = ((Operacion) _expresionTamano).get_primeraHoja(); i != null; i = i.getHermanoDerecho())
                i.setPadre(this);
        else
            _expresionTamano.setPadre(this);
    }

    @Override
    public String toString() {
        return "Declaracion{" +
                "_arreglo=" + _arreglo +
                ", _nombre='" + _nombre + '\'' +
                ", _tamano=" + _tamano +
                ", _expresionTamano=" + _expresionTamano +
                ", _tipo=" + _tipo +
                '}';
    }

    @Override
    public boolean evaluarSemantica() throws SemanticError {
        //Agregamos a tablas de simbolos.
        agregarATablaSimbolos();

        //Evaluamos la expresion del indice
        try {
            evaluarIndice();
        }
        catch (SemanticError e){
            throw new SemanticError("Indice inválido en declaración: " + get_nombre() + "\n" + e.getMessage());
        }

        //Evaluamos las demás componentes: Recorrido por anchura primero.
        if (this.getHermanoDerecho() != null)
            this.getHermanoDerecho().evaluarSemantica();
        if (this.getHijoMasIzq() != null)
            this.getHijoMasIzq().evaluarSemantica();
        return true;
    }

    private void agregarATablaSimbolos() throws SemanticError{
        //Manejamos las excepciones cuando los nombres ya existen.
        if (this.getPadre().getTblSimbolosLocales().containsKey(this.get_nombre()) && this.getPadre().getTblSimbolosLocales().get(this.get_nombre()) instanceof Variable)
            System.out.println("ADVERTENCIA: Declaración repetida en contexto local: " + this.get_nombre() + "-> Declaracion sustituida");

        this.getPadre().getTblSimbolosLocales().put(this.get_nombre(),new Variable(get_nombre(), get_tipo(), is_arreglo()));
    }
}
