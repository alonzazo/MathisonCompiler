package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public class Asignacion extends Sentencia {

    private Object _valor;
    private String _nombre;

    public Asignacion(){
        _valor = new Object();
        _nombre = "";
    }

    @Override
    public String toString() {
        return "Asignacion{" +
                "_valor=" + _valor +
                ", _nombre='" + _nombre + '\'' +
                '}';
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public Asignacion(String nombre, Object valor){
        _valor = valor;
        _nombre = nombre;
    }

    @Override
    public boolean evaluarSemantica() {
        return false;
    }
}
