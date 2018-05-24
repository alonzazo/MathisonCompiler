package SemanticAnalizer;

import java.util.HashMap;

public abstract class Estructura implements Componente {

    public enum Tipo {
        SI,
        SINO,
        MIENTRAS,
        PARA
    }

    private Tipo _tipo;
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
    public HashMap<String, Nombre> getVariablesLocales() {
        return null;
    }
}
