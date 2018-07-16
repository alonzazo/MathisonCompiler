package SemanticAnalizer;

import GeneradorCodigo.Descriptor;
import SyntacticalAnalizer.sym;
import java_cup.runtime.Symbol;

public class ExpresionGenerico extends ComponenteConcreto implements Expresion {
    protected String _nombre;
    protected Tipo _tipo;
    protected Symbol _symbol;

    public void setTipo(Tipo _tipo) {
        this._tipo = _tipo;
    }

    public ExpresionGenerico(String nombre, Tipo tipo, Symbol symbol) {
        this._nombre = nombre;
        this._tipo = tipo;
        _symbol = symbol;
    }
    public ExpresionGenerico(Tipo tipo, Symbol symbol) {
        this._tipo = tipo;
        _symbol = symbol;
    }

    public ExpresionGenerico(String nombre, Tipo tipo) {
        this._nombre = nombre;
        this._tipo = tipo;
    }

    public ExpresionGenerico(String nombre) {
        this._nombre = nombre;
        this._tipo = null;

    }

    public ExpresionGenerico(Tipo tipo) {
        this._nombre = null;
        this._tipo = tipo;
    }

    public ExpresionGenerico() {
        this._nombre = null;
        this._tipo = null;
    }

    public Symbol get_symbol() {
        return _symbol;
    }

    public void set_symbol(Symbol _symbol) {
        this._symbol = _symbol;
    }

    @Override
    public String getNombre() {
        return _nombre;
    }

    @Override
    public Tipo getTipo() {
        return _tipo;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    @Override
    public Tipo evaluarTipo() throws SemanticError{
        return getTipo();
    }

    @Override
    public boolean evaluarSemantica() throws SemanticError {
        return true;
    }

    public String getEtiqueta() {
        String result = "";
        String idReferencia;
        if (!(this instanceof Variable)){
            if (_tipo == Tipo.CADENA){
                //Creamos el id de referencia
                idReferencia = "cad_generica" + Programa.getInstance().getNumCadenasGenericas();

                //Lo agregamos al heap
                Programa.getInstance().getSectionData().put(idReferencia, new Descriptor(idReferencia,".asciiz", _symbol.value.toString()) );
                Programa.getInstance().setNumCadenasGenericas(Programa.getInstance().getNumCadenasGenericas() + 1);
                result = idReferencia;
            } else if(_tipo == Tipo.BOOLEANO){
                if (sym.terminalNames[_symbol.sym] == "VERDADERO")
                    result = "1";
                else
                    result = "0";
            } else
                result = String.valueOf(((Double)_symbol.value).intValue());
        } else {
            result = getNombre();
        }
        return result;
    }

    @Override
    public String compilar() throws SemanticError {
        String result = "";
        if (_tipo == Tipo.NUMERICO || _tipo == Tipo.BOOLEANO){
            result = "\tli\t\t$v0, " + this.getEtiqueta()+"\t#Expresion generica " + _tipo + "\n";
        } else {
            result = "\tla\t\t$v0, " + this.getEtiqueta()+"\t#Expresion generica " + _tipo + "\n";
        }
        return result;
    }
}
