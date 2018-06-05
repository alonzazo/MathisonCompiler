package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Para extends Estructura {

    public Para(){
    }

    public Para(Componente sentencias){
        _hijoMasIzq = sentencias;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }

    @Override
    public String toString() {
        return "Para{}";
    }
}
