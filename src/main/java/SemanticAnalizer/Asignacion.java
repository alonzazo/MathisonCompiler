package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;

public class Asignacion extends Sentencia {

    private Object _valor;
    private String _nombre;

    public Asignacion(){

    }



    @Override
    public boolean evaluarSemantica() {
        return false;
    }
}
