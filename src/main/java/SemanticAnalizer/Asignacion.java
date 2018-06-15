package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public class Asignacion extends Sentencia {
    private String _nombre;
    private Object _valor;
    private ExpresionGenerico _expresion;

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

    public ExpresionGenerico get_expresion() {
        return _expresion;
    }

    public void set_expresion(ExpresionGenerico expresion) {
        this._expresion = expresion;
    }

    @Override
    public boolean evaluarSemantica() {
        return false;
    }

    @Override
    public String toString() {
        return "Asignacion{" +  _nombre + '}';
    }
}
