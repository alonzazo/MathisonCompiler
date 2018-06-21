package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public class Asignacion extends Sentencia {
    private String _nombre;
    private Object _valor;
    private Expresion _expresion;
    private Tipo _tipo;

    public Tipo getTipo() {
        return _tipo;
    }

    public void setTipo(Tipo _tipo) {
        this._tipo = _tipo;
    }

    public Asignacion(){ }

    public Asignacion(String nombre)
    {
        _nombre = nombre;
    }

    public Object get_valor() {
        return _valor;
    }

    public void set_valor(Object _valor) {
        this._valor = _valor;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public Expresion get_expresion() {
        return _expresion;
    }

    public void set_expresion(Expresion expresion) {
        this._expresion = expresion;
    }

    @Override
    public boolean evaluarSemantica() throws SemanticError{
        Expresion e = _expresion instanceof Operacion ? ((Operacion) _expresion).get_primeraHoja() : _expresion;
        return tipoDatosCorrecto(e,_tipo,this);
    }

    @Override
    public String toString() {
        return "Asignacion{" +  _nombre + '}';
    }


}
