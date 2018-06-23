package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Metodo extends ComponenteConcreto implements Nombre
{
    private String _nombre;
    private Tipo _tipo;
    private String _nombreTipo;
    private List<Variable> _parametros;
    private boolean _arreglo;

    public Metodo(){
        _nombre = "";
        _tipo = Tipo.NUMERICO;
        _arreglo = false;
    }

    public Metodo( String nombre, Tipo tipo, Componente sentencias){
        _nombre = nombre;
        _tipo = tipo;
        _parametros = null;
        _hijoMasIzq = sentencias;
        System.out.println("Metodo " + nombre);
        _arreglo = false;
    }

    public Metodo( String nombre, Componente sentencias){
        _nombre = nombre;
        _parametros = null;
        _hijoMasIzq = sentencias;
        System.out.println("Metodo " + nombre);
        _arreglo = false;
    }

    public Metodo( String nombre, ArregloPOJO tipo, Componente sentencias){
        _nombre = nombre;
        _tipo = tipo.get_tipo();
        _nombreTipo = tipo.get_nombreTipo();
        _arreglo = true;
        _parametros = null;
        _hijoMasIzq = sentencias;
        System.out.println("Metodo " + nombre);
    }

    public Metodo( String nombre, Tipo tipo, List<Variable> params , Componente sentencias){
        _nombre = nombre;
        _tipo = tipo;
        _parametros = params;
        _hijoMasIzq = sentencias;
        _arreglo = false;
        System.out.println("Metodo " + nombre);
    }

    public Metodo( String nombre, List<Variable> params , Componente sentencias){
        _nombre = nombre;
        _parametros = params;
        _hijoMasIzq = sentencias;
        _arreglo = false;
        System.out.println("Metodo " + nombre);
    }

    public Metodo( String nombre, ArregloPOJO tipo, List<Variable> params , Componente sentencias){
        _nombre = nombre;
        _tipo = tipo.get_tipo();
        _nombreTipo = tipo.get_nombreTipo();
        _parametros = params;
        _arreglo = true;
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

    public String get_nombreTipo() {
        return _nombreTipo;
    }

    public void set_nombreTipo(String _nombreTipo) {
        this._nombreTipo = _nombreTipo;
    }

    public boolean is_arreglo() {
        return _arreglo;
    }

    public void set_arreglo(boolean _arreglo) {
        this._arreglo = _arreglo;
    }
}
