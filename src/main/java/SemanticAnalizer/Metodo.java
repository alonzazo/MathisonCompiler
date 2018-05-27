package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Metodo implements Componente, Nombre {

    private String _nombre;
    private Tipo _tipo;

    @Override
    public Componente getHermanoDerecho() {
        return null;
    }

    @Override
    public Componente getHijoMasIzq() {
        return null;
    }

    @Override
    public HashMap<String, Nombre> getTblSimbolosLocales() {
        return null;
    }

    @Override
    public LinkedList<HashMap> getTblSimbolosPadres() {
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
