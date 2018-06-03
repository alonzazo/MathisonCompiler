package SemanticAnalizer;

import java.util.EmptyStackException;
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
    private Componente _hijoMasIzq;
    private Componente _hermanoDer;
    private Componente _padre;

    public Estructura(){

    }

    @Override
    public Componente getHermanoDerecho() {
        return _hermanoDer;
    }

    //Evalúa semánticamente la condición de la estructura
    public abstract boolean evaluarCondicion() throws SemanticError;

    @Override
    public Componente getHijoMasIzq() {
        return _hijoMasIzq;
    }

    @Override
    public HashMap<String, Nombre> getTblSimbolosLocales() {
        return null;
    }

    @Override
    public Componente getPadre() {
        return _padre;
    }
}
