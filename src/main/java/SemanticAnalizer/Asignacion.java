package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public class Asignacion extends Sentencia {
    public Componente getHermanoDerecho() {
        return null;
    }

    @Override
    public LinkedList<HashMap> getTblSimbolosPadres() {
        return null;
    }

    public Componente getHijoMasIzq() {
        return null;
    }

    public HashMap<String, Nombre> getTblSimbolosLocales() {
        return null;
    }
}
