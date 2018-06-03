package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public class Clase implements Componente, Nombre {

    private Componente _hijoMasIzq;
    private Componente _hermanoDer;
    private Componente _padre;
    private String identificador;

    public Clase(){

    }

    @Override
    public Componente getHermanoDerecho() {
        return null;
    }

    @Override
    public Componente getHijoMasIzq() {
        return null;
    }

    @Override
    public Componente getPadre() {
        return null;
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
}
