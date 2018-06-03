package SemanticAnalizer;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class Estructura extends ComponenteConcreto {

    public enum TipoEstructura {
        SI,
        SINO,
        MIENTRAS,
        PARA
    }

    private TipoEstructura _tipoEstructura;
    private String _condicion;

    public Estructura(){

    }

    //Evalúa semánticamente la condición de la estructura
    public abstract boolean evaluarCondicion() throws SemanticError;


}
