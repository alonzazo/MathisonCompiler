package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Metodo implements Componente, Nombre {

    private String _nombre;
    private Componente.Tipo _tipo;

    @Override
    public Componente getHermanoDerecho() {
        return null;
    }

    @Override
    public Componente getHijoMasIzq() {
        return null;
    }

    @Override
    public HashMap<String, Nombre> getVariablesLocales() {
        return null;
    }

    @Override
    public String get_nombre() {
        return null;
    }

    @Override
    public Componente.Tipo get_tipo() {
        return null;
    }

    public List<Variable> getParametros() {
        return new LinkedList<Variable>();
    }
}
