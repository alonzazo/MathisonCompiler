package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public class Asignacion extends Sentencia {

    private Object _valor;
    private String _nombre;

    public Asignacion(){
        System.out.println("Asignacion");
    }

    public Asignacion(String nombre,Object valor){
        _valor = valor;
        _nombre = nombre;
        System.out.println("Asignacion " + nombre);
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

    @Override
    public boolean evaluarSemantica() {
        return false;
    }

    @Override
    public String toString() {
        return "Asignacion{" +  _nombre + '}';
    }
}
