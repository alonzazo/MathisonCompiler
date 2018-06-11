package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LlamadaMetodo extends Sentencia implements Expresion {

    private List<Variable> _parametros;
    private String _nombre;

    public LlamadaMetodo(String nombre){
        _nombre = nombre;
    }

    public LlamadaMetodo(List params, String nombre){
        _parametros = params;
        _nombre = nombre;
    }

    @Override
    public String getNombre() {
        return _nombre;
    }

    @Override
    public boolean evaluarSemantica() {
        return false;
    }

    @Override
    public String toString() {
        return "LlamadaMetodo{" +  _nombre + '}';
    }
}
