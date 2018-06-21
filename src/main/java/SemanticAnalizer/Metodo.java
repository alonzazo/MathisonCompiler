package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Metodo extends ComponenteConcreto implements Nombre
{
    private String _nombre;
    private Tipo _tipo;
    private List<Variable> _parametros;

    public Metodo(){
        _nombre = "";
        _tipo = Tipo.NUMERICO;
    }

    public Metodo( String nombre, Tipo tipo, Componente sentencias){
        _nombre = nombre;
        _tipo = tipo;
        _parametros = null;
        _hijoMasIzq = sentencias;
        System.out.println("Metodo " + nombre);
    }

    public Metodo( String nombre, Tipo tipo, List<Variable> params , Componente sentencias){
        _nombre = nombre;
        _tipo = tipo;
        _parametros = params;
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

}
