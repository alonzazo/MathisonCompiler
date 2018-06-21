package SemanticAnalizer;

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
}
