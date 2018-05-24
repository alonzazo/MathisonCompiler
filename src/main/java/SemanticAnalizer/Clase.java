package SemanticAnalizer;

import java.util.HashMap;

public class Clase implements Componente, Nombre {
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
    public Tipo get_tipo() {
        return null;
    }
}
