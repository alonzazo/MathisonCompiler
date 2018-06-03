package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Sentencia implements Componente {

    private Componente hijoMasIzq;
    private Componente hermanoDer;
    private Componente padre;

    public Sentencia(){

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
    public HashMap<String, Nombre> getTblSimbolosLocales() {
        return null;
    }

    public abstract boolean evaluarSemantica();

    @Override
    public Componente getPadre() {
        return null;
    }
}
