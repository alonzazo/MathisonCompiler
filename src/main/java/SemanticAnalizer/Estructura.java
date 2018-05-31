package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Estructura implements Componente {

    public enum TipoEstructura {
        SI,
        SINO,
        MIENTRAS,
        PARA
    }

    private TipoEstructura _tipoEstructura;
    private String _condicion;

    @Override
    public Componente getHermanoDerecho() {
        return null;
    }

    //Evalúa semánticamente la condición de la estructura
    public abstract boolean evaluarCondicion() throws SemanticError;

    @Override
    public Componente getHijoMasIzq() {
        return null;
    }

    @Override
    public HashMap<String, Nombre> getTblSimbolosLocales() {
        return null;
    }

    @Override
    public Componente getPadre() {
        return null;
    }
}
