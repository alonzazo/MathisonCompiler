package SemanticAnalizer;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class Estructura extends ComponenteConcreto {

    public enum TipoEstructura {
        SI,
        SINO,
        MIENTRAS,
        PARA
    }

    protected TipoEstructura _tipoEstructura;
    protected Object _condicion;

    public Estructura(){

    }

    //Evalúa semánticamente la condición de la estructura
    public abstract boolean evaluarCondicion() throws SemanticError;


}
