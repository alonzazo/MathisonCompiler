package SemanticAnalizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Para extends Estructura {

    public Para(){
        _sentencias = new LinkedList<Componente>();
    }

    public Para(List<Componente> sentencias){
        _sentencias = sentencias;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }
}
