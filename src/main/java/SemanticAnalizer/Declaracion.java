package SemanticAnalizer;

import java_cup.runtime.Symbol;

import java.util.LinkedList;

public class Declaracion extends ComponenteConcreto {

    private boolean _arreglo;
    private String _nombre;
    private int _tamano;
    private LinkedList<Symbol> _expresionTamano;
    private Tipo _tipo;
    private String _nombreTipo;

    public Declaracion(String _nombre, Tipo _tipo) {
        this._nombre = _nombre;
        this._tipo = _tipo;
    }

    public Declaracion(String _nombre, Tipo _tipo, String _nombreTipo) {
        this._nombre = _nombre;
        this._tipo = _tipo;
        if (_tipo == Tipo.NO_PRIMITIVO)
            this._nombreTipo = _nombreTipo;
    }

    @Override
    public String toString() {
        return "Declaracion{" +
                "_arreglo=" + _arreglo +
                ", _nombre='" + _nombre + '\'' +
                ", _tamano=" + _tamano +
                ", _expresionTamano=" + _expresionTamano +
                ", _tipo=" + _tipo +
                '}';
    }

    public Declaracion(String _nombre, Tipo _tipo, boolean _arreglo) {
        this._nombre = _nombre;
        this._tipo = _tipo;
        this._arreglo = _arreglo;
    }

    public Declaracion(String _nombre, Tipo _tipo, boolean _arreglo, String _nombreTipo) {
        this._nombre = _nombre;
        this._tipo = _tipo;
        this._arreglo = _arreglo;
        if (_tipo == Tipo.NO_PRIMITIVO)
            this._nombreTipo = _nombreTipo;
    }

    public int get_tamano() {
        return _tamano;
    }

    public void set_tamano(int _tamano) {
        this._tamano = _tamano;
    }

    public LinkedList<Symbol> get_expresionTamano() {
        return _expresionTamano;
    }

    public void set_expresionTamano(LinkedList<Symbol> _expresionTamano) {
        this._expresionTamano = _expresionTamano;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public boolean is_arreglo() {
        return _arreglo;
    }

    public void set_arreglo(boolean _arreglo) {
        this._arreglo = _arreglo;
    }

    public Tipo get_tipo() {
        return _tipo;
    }

    public void set_tipo(Tipo _tipo) {
        this._tipo = _tipo;
    }
}
