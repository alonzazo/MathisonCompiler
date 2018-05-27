package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Sentencia implements Componente {

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
}
