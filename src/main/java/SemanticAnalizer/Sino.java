package SemanticAnalizer;

import java.util.LinkedList;
import java.util.List;

public class Sino extends Estructura {

    public Sino(){
        _sentencias = new LinkedList<Componente>();
    }

    public Sino(List<Componente> sentencias){
        _sentencias = sentencias;
    }

    @Override
    public boolean evaluarCondicion() throws SemanticError {
        return false;
    }
}