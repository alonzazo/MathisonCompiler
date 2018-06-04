package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Metodo extends ComponenteConcreto implements Nombre {

    private String _nombre;
    private Tipo _tipo;
    private List<Variable> _parametros;
    private List<Componente> _sentencias;

    public Metodo(){
        _nombre = "";
        _tipo = Tipo.NUMERICO;
    }

    public Metodo( String nombre, Tipo tipo, List<Componente> sentencias){
        _nombre = nombre;
        _tipo = tipo;
        _parametros = null;
        _sentencias = sentencias;
    }

    public Metodo( String nombre, Tipo tipo, List<Variable> params , List<Componente> sentencias){
        _nombre = nombre;
        _tipo = tipo;
        _parametros = params;
        _sentencias = sentencias;
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
        return null;
    }

    @Override
    public Tipo get_tipo() {
        return null;
    }

    public List<Variable> getParametros() {
        return new LinkedList<Variable>();
    }

    @Override
    public String toString() {
        return "Metodo{" +
                "_nombre='" + _nombre + '\'' +
                '}';
    }
}
