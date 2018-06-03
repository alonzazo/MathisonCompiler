package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Metodo implements Componente, Nombre {

    private String _nombre;
    private Tipo _tipo;
    private Componente _hijoMasIzq;
    private Componente _hermanoDer;
    private Componente _padre;

    public Metodo(){

    }

    public Metodo(Componente padre){
        _padre = padre;
    }

    @Override
    public Componente getPadre() {
        return _padre;
    }

    @Override
    public Componente getHermanoDerecho() {
        return _hermanoDer;
    }

    @Override
    public Componente getHijoMasIzq() {
        return _hijoMasIzq;
    }

    @Override
    public HashMap<String, Nombre> getTblSimbolosLocales() {
        return null;
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
}
